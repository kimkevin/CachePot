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

  private Map<Class<?>, Object> dataMap;
  private Map<Integer, Object> dataListMap;

  public <T> void push(T data) {
    if (dataMap == null) {
      dataMap = new HashMap<>();
    }

    dataMap.put(data.getClass(), data);
  }

  public <T> T pop(Class classType) {
    T value = (T) dataMap.get(classType);
    dataMap.remove(classType);
    return value;
  }

  public <T> void push(int position, T data) {
    if (dataListMap == null) {
      dataListMap = new HashMap<>();
    }

    dataListMap.put(position, data);
  }

  public <T> T pop(int position) {
    T value = (T) dataListMap.get(position);
    dataListMap.remove(position);
    return value;
  }
}

