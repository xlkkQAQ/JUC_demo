package com.xlkk.rwLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁demo
 */
public class ReadWriteLockDemo01 {
    public static void main(String[] args) {
//        MyCacheLock myCache = new MyCacheLock();
        MyCache cache = new MyCache();
        for (int i = 1; i < 5; i++) {
            final String temp = String.valueOf(i);
            new Thread(()->{
                cache.put(temp,temp);
            },String.valueOf(i)).start();
        }
        for (int i = 1; i < 5; i++) {
            final String temp = String.valueOf(i);
            new Thread(()->{
                cache.get(temp);
            },String.valueOf(i)).start();
        }
    }
}

class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();

    /**
     * 写数据
     * @param key
     * @param value
     */
    public void put(String key,Object value){
        System.out.println(Thread.currentThread().getName()+"开始写入"+key);
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"写入完毕");
    }
    /**
     * 读数据
     * @param key
     */
    public void get(String key){
        System.out.println(Thread.currentThread().getName()+"开始读取"+key);
        map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取完毕");
    }
}
