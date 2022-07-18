package com.xlkk.copyOnWrite;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 关于集合的线程安全问题——List集合线程不安全的问题
 * @author xlkk
 * @date 2022/7/16 0016 15:03
 */
public class ThreadDemo04 {
    public static void main(String[] args) {
//        ArrayList<String> list = new ArrayList<>();
//        List<String> list = new Vector<>();

//        List<String> list = Collections.synchronizedList(new ArrayList<String>());
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }


}
