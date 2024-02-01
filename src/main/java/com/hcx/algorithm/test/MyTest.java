package com.hcx.algorithm.test;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Title: MyTest.java
 * @Package com.hcx.algorithm.test
 * @Description: (用一句话描述该文件做什么)
 * @Author: hongcaixia
 * @Date: 2024/1/30 10:26
 * @Version V1.0
 */
public class MyTest {
    public static void main(String[] args) {
        Map<Integer,String> cmap = new ConcurrentHashMap<>();
        cmap.put(1,"1");
        cmap.put(2,"2");
        cmap.put(3,"3");
        Iterator<Map.Entry<Integer, String>> citerator = cmap.entrySet().iterator();
        while (citerator.hasNext()){
            Map.Entry<Integer, String> next = citerator.next();
            Integer key = next.getKey();
            System.out.println(next.getValue());
            if(key == 2){
                cmap.put(4,"4");
            }
        }
        System.out.println("------");
        Iterator<Map.Entry<Integer, String>> citerator1 = cmap.entrySet().iterator();
        while (citerator1.hasNext()){
            Map.Entry<Integer, String> next = citerator1.next();
            System.out.println(next.getKey());
            System.out.println(next.getValue());
        }

        System.out.println("========");

        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList(new Integer[] {1,7,9,11});
        Iterator itr = list.iterator( );
        while (itr.hasNext( )) {
            Integer i = (Integer) itr.next();
            System.out.println(i);
            if (i == 7) {
                list.add(15); // 在fail-safe模式下，这里不会被打印
            }
        }
        System.out.println("~~~~~~~~~");


        Map<Integer,String> map = new Hashtable<>();
        map.put(1,"1");
        map.put(2,"2");
        map.put(3,"3");
        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, String> next = iterator.next();
            next.getKey();
            next.getValue();

        }
        Iterator<Map.Entry<Integer, String>> iterator1 = map.entrySet().iterator();
        while (iterator1.hasNext()){
            Map.Entry<Integer, String> next = iterator1.next();
            System.out.println(next.getKey());
            System.out.println(next.getValue());
        }
    }
}
