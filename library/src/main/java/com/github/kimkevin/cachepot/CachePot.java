package com.github.kimkevin.cachepot;

import java.util.HashMap;
import java.util.Map;

public class CachePot {
  private static volatile CachePot instance;

  public static CachePot getInstance() {
    if (instance == null) {
      synchronized (CachePot.class) {
        if (instance == null) {
          instance = new CachePot();
        }
      }
    }
    return instance;
  }

  private final Map<Object, Object> pot = new HashMap<>();

  public void push(Object data) {
    pushObject(data.getClass(), data);
  }

  public <T> T pop(Class classType) {
    return popObject(classType);
  }

  public void push(long id, Object value) {
    pushObject(id, value);
  }

  public <T> T pop(long id) {
    return popObject(id);
  }

  public void push(String key, Object value) {
    pushObject(key, value);
  }

  public <T> T pop(String key) {
    return popObject(key);
  }

  public void push(String tag, int id, Object data) {
    Object o = pot.get(tag);

    if (!(o instanceof Map)) {
      o = new HashMap<>();
      pushObject(tag, o);
    }
    Map<Integer, Object> groupPot = (Map) o;

    groupPot.put(id, data);
  }

  public <T> T pop(String tag, int id) {
    Object o = pot.get(tag);
    if (!(o instanceof Map)) {
      return null;
    }

    Map groupPot = (Map) o;

    try {
      return (T) groupPot.remove(id);
    } finally {
      if (groupPot.isEmpty()) {
        pop(tag);
      }
    }
  }

  public int size() {
    return pot.size();
  }

  public int size(String tag) {
    Object o = pot.get(tag);
    if (!(o instanceof Map)) {
      return 0;
    }
    return ((Map) o).size();
  }

  public void clear() {
    pot.clear();
  }

  public void clear(String tag) {
    Object o = pot.get(tag);
    if (!(o instanceof Map)) {
      return;
    }
    ((Map) o).clear();
    pop(tag);
  }

  private void pushObject(Object key, Object value) {
    pot.put(key, value);
  }

  private <T> T popObject(Object key) {
    return (T) pot.remove(key);
  }

  @Override
  public String toString() {
    return "CachePot{" +
        "pot=" + pot +
        '}';
  }
}
