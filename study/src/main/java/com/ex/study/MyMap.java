package com.ex.study;

import java.util.IdentityHashMap;
import java.util.Map;

public class MyMap {
    public static void main(String[] args) {
        Map map = new IdentityHashMap<>();
        map.put(1, "Hello");
        map.putIfAbsent(1, "World");
        print(map.get(1));
        print(map.size());

        map.put(1024, "A");
        map.putIfAbsent(1024, "B");
        print(map.get(1024));
        print(map.size());

    }

    private static void print(Object object) {
        System.out.print(object + " ");
    }
}
