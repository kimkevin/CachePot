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
    private Map<Integer, Object> listMap = new HashMap<>();

    public <T> void push(T data) {
        maps.put(data.getClass(), data);
    }

    public <T> T pop(Class classType) {
        T value = (T)maps.get(classType);
        maps.remove(classType);
        return value;
    }

    public <T> void push(int position, T data) {
        listMap.put(position, data);
    }

    public <T> T pop(int position) {
        T value = (T)listMap.get(position);
        listMap.remove(position);
        return value;
    }
}

