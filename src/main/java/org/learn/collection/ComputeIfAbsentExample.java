package org.learn.collection;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ComputeIfAbsentExample {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("abc", 1);
        map.computeIfAbsent("abc", k-> length1((String) k));
        map.computeIfAbsent("bcd",  k-> length1((String) k));
        System.out.println(map);
        System.out.println(map.get("abc"));
    }

    private static Object length1(String k) {
        return k.length();
    }

}
