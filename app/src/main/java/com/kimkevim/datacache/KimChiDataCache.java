package com.kimkevim.datacache;

import java.util.HashMap;
import java.util.Map;

public class KimchiDataCache {
    private static KimchiDataCache instance;

    public static KimchiDataCache getInstance() {
        if (instance == null) {
            synchronized (KimchiDataCache.class) {
                if (instance == null) {
                    instance = new KimchiDataCache();
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
        maps.remove(classType);
        return value;
    }
}

