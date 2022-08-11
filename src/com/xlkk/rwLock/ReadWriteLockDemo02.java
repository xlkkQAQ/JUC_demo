package com.xlkk.rwLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xlkk
 * @date 2022/8/11 0011 21:25
 * @Description: 读写锁demo
 */
public class ReadWriteLockDemo02 {
    public static void main(String[] args) {
        MyCache2 myCache2 = new MyCache2();
        for (int i = 0; i < 6; i++) {
            String temp = String.valueOf(i);
            new Thread(()->{
                myCache2.put(temp,temp);
            },i+"").start();
        }
        for (int i = 0; i < 6; i++) {
            String temp = String.valueOf(i);
            new Thread(()->{
                myCache2.get(temp);
            },i+"").start();
        }
    }
}

/**
 * 使用ReadWriteLock后的自定义缓存
 */
class MyCache2{
    private volatile Map<String,Object> map = new HashMap<>();
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key,Object val){
        //加写锁
        rwLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+"正在写"+key);
        map.put(key, val);
        System.out.println(Thread.currentThread().getName()+"写完了"+key);
        rwLock.writeLock().unlock();
    }
    public Object get(String key){
        //加读锁
        rwLock.readLock().lock();
        System.out.println(Thread.currentThread().getName()+"正在读取"+key);
        Object val = map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取完了"+key);
        rwLock.readLock().unlock();
        return val;
    }
}
