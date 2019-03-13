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

  private Map<Object, Object> pot;

  public CachePot() {
    pot = new HashMap<>();
  }

  public void push(Object data) {
    pot.put(data.getClass(), data);
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

  public void push(String TAG, int id, Object data) {
    Map<Integer, Object> groupPot;

    if (pot.containsKey(TAG) && pot.get(TAG) instanceof Map) {
      groupPot = (Map) pot.get(TAG);
    } else {
      groupPot = new HashMap<>();
    }

    groupPot.put(id, data);
    pushObject(TAG, groupPot);
  }

  public <T> T pop(String TAG, int id) {
    T value = null;
    Map groupPot;
    if (pot.get(TAG) instanceof Map) {
      groupPot = (Map) pot.get(TAG);

      value = (T) groupPot.get(id);
      if (value == null) {
        return null;
      }

      groupPot.remove(id);

      if (groupPot.isEmpty()) {
        pop(TAG);
      }
    }

    return value;
  }

  public int size() {
    try {
      return pot.size();
    } catch (NullPointerException exception) {
      return 0;
    }
  }

  public int size(String TAG) {
    try {
      if (pot.get(TAG) != null && pot.get(TAG) instanceof Map) {
        return ((Map) pot.get(TAG)).size();
      }
    } catch (ClassCastException | NullPointerException exception) {
      return 0;
    }
    return 0;
  }

  public void clear() {
    pot.clear();
  }

  public void clear(String TAG) {
    if (pot.get(TAG) != null && pot.get(TAG) instanceof Map) {
      Map groupPot = (Map) pot.get(TAG);

      groupPot.clear();

      if (groupPot.isEmpty()) {
        pop(TAG);
      }
    }
  }

  private void pushObject(Object key, Object value) {
    pot.put(key, value);
  }

  private <T> T popObject(Object key) {
    T value = (T) pot.get(key);

    if (value == null) {
      return null;
    }

    pot.remove(key);
    return value;
  }

  @Override
  public String toString() {
    return "CachePot{" +
        "pot=" + pot +
        '}';
  }
}
