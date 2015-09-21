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

    private Map<Class<?>, Object> maps = new HashMap<>();

    public <T> void put(T data) {
        maps.put(data.getClass(), data);
    }

    public <T> T get(Class classType) {
        T value = (T)maps.get(classType);
        maps.remove(value);
        return value;
    }
}

