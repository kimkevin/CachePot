package com.github.kimkevin.cachepot;

import java.util.HashMap;
import java.util.Map;

public class CachePot {
  private static CachePot instance;

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

  private Map<Object, Object> cacheMap;

  public void push(Object data) {
    if (cacheMap == null) {
      cacheMap = new HashMap<>();
    }

    cacheMap.put(data.getClass(), data);
  }

  public <T> T pop(Class classType) {
    T value;
    try {
      value = (T) cacheMap.get(classType);
      cacheMap.remove(classType);
    } catch (NullPointerException exception) {
      return null;
    }

    return value;
  }

  public void push(long id, Object data) {
    if (cacheMap == null) {
      cacheMap = new HashMap<>();
    }

    cacheMap.put(id, data);
  }

  public <T> T pop(long id) {
    T value;
    try {
      value = (T) cacheMap.get(id);
      cacheMap.remove(id);
    } catch (NullPointerException exception) {
      return null;
    }

    return value;
  }

  public void push(String TAG, int id, Object data) {
    if (cacheMap == null) {
      cacheMap = new HashMap<>();
    }

    Map<Integer, Object> valueMap;
    if (cacheMap.containsKey(TAG) && cacheMap.get(TAG) instanceof HashMap) {
      valueMap = (HashMap<Integer, Object>) cacheMap.get(TAG);
    } else {
      valueMap = new HashMap<>();
    }

    valueMap.put(id, data);
    cacheMap.put(TAG, valueMap);
  }

  public <T> T pop(String TAG, int id) {
     T value;

    try {
      value = (T) ((HashMap<Integer, Object>) cacheMap.get(TAG)).get(id);
      cacheMap.remove(id);
    } catch (NullPointerException exception) {
      return null;
    }
    return value;
  }

  public int size() {
    try {
      return cacheMap.size();
    } catch (NullPointerException exception) {
      return 0;
    }
  }

  public int size(String TAG) {
    try {
      return ((HashMap) cacheMap.get(TAG)).size();
    } catch (ClassCastException | NullPointerException exception) {
      return 0;
    }
  }

  public void clear() {
    if (cacheMap != null) {
      cacheMap.clear();
    }
  }

  public void clear(String TAG) {
    if (cacheMap != null && cacheMap.get(TAG) != null) {
      ((HashMap) cacheMap.get(TAG)).clear();
    }
  }
}
