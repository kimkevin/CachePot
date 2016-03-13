package com.kimkevim.datacache;

import java.util.HashMap;
import java.util.Map;

public class DataCache {
    private static DataCache instance;

    public static DataCache getInstance() {
        if (instance == null) {
            synchronized (DataCache.class) {
                if (instance == null) {
                    instance = new DataCache();
                }
            }
        }
        return instance;
    }

    private Map<Class<?>, Object> dataMap = new HashMap<>();
    private Map<Integer, Object> dataListMap = new HashMap<>();

    public <T> void push(T data) {
        dataMap.put(data.getClass(), data);
    }

    public <T> T pop(Class classType) {
        T value = (T) dataMap.get(classType);
        dataMap.remove(classType);
        return value;
    }

    public <T> void push(int position, T data) {
        dataListMap.put(position, data);
    }

    public <T> T pop(int position) {
        T value = (T) dataListMap.get(position);
        dataListMap.remove(position);
        return value;
    }
}

