package com.xlkk.rwLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class ReadWriteLock {
    public static void main(String[] args) {
        MyCacheLock myCache = new MyCacheLock();

        for (int i = 1; i < 5; i++) {
            final String temp = String.valueOf(i);
            new Thread(()->{
                myCache.put(temp,temp);
            },String.valueOf(i)).start();
//            new Thread(()->{
//                myCache.get(temp);
//            },String.valueOf(i)).start();
        }

        for (int i = 1; i < 5; i++) {
            final String temp = String.valueOf(i);
            new Thread(()->{
                myCache.get(temp);
            },String.valueOf(i)).start();
        }
    }
}
/**
 * 自定义缓存
 *
 */
class MyCacheLock{
    private volatile Map<String,Object> map = new HashMap<String,Object>();
     ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    //常见读写锁对象：更加细粒度地控制
    ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
    ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    //存：写：写入的时候，只希望只有一个线程
    public void put(String key,Object value){
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始写入"+key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入完毕");
        }catch (Exception e){
              e.printStackTrace();
        }finally {
            writeLock.unlock();
        }
    }

    //取：读
    public void get(String key){
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始读取"+key);
            map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            readLock.unlock();
        }
    }



}

class MyCache{
    private volatile Map<String,Object> map = new HashMap<String,Object>();

    //存：写
    public void put(String key,Object value){
        System.out.println(Thread.currentThread().getName()+"开始写入"+key);
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"写入完毕");
    }

    //取：读
    public void get(String key){
        System.out.println(Thread.currentThread().getName()+"开始读取"+key);
        map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取完毕");
    }



}
