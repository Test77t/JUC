package com.atguigu.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/*
*   集合类线程不安全
* */
public class SetNoSafe {
    public static void main(String[] args) {
        Map<String, String>  map = new ConcurrentHashMap<>(); /*HashMap<>();*/
        for (int i = 1; i <= 31; i++)
        {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    private static void SetNotSafe() {
        Set<String> set = new CopyOnWriteArraySet<>(); /*Collections.synchronizedSet(new HashSet<>());*/ /*new HashSet<>();*/

        for (int i = 1; i <= 31; i++)
        {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void ListNotSafe() {
        List<String> list = new CopyOnWriteArrayList<>(); /*Collections.synchronizedList(new ArrayList<>());*/ /* new Vector<>();*/ /*new ArrayList<>();*/

        for (int i = 1; i <= 31; i++)
        {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
