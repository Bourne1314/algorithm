package com.cunyu.algorithm.demo.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * list集合线程不安全
 */
public class ThreadDemo4 {
    public static void main(String[] args) {
        //创建ArryList集合不安全
//        List<String> list = new ArrayList<>();
        //Vector的add加了syc关键字所以线程安全，jdk1.0比较古老
//        List<String> list = new Vector<>();C
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
//        List<String> list = new CopyOnWriteArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            new Thread(()->{
//                //向集合中添加内容
//                list.add(UUID.randomUUID().toString().substring(0,8));
//                //从集合获取内容
//                System.out.println(list);
//            },String.valueOf(i)).start();
//        }
        //演示HashSet

//        Set<String> set = new HashSet<>();
//        for (int i = 0; i < 30; i++) {
//            new Thread(()->{
//                //向集合中添加内容
//                set.add(UUID.randomUUID().toString().substring(0,8));
//                //从集合获取内容
//                System.out.println(set);
//            },String.valueOf(i)).start();
//        }
//        Set<String> set = new CopyOnWriteArraySet<>();
//        for (int i = 0; i < 30; i++) {
//            new Thread(()->{
//                //向集合中添加内容
//                set.add(UUID.randomUUID().toString().substring(0,8));
//                //从集合获取内容
//                System.out.println(set);
//            },String.valueOf(i)).start();
//        }
//        Map<String,String> map = new HashMap<>();
        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            String key = String.valueOf(i);
            new Thread(()->{
                //向集合中添加内容
                map.put(key,UUID.randomUUID().toString().substring(0,8));
                //从集合获取内容
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
