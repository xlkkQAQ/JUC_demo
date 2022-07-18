package com.xlkk.copyOnWrite;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程的定制化执行
 * @author xlkk
 * @date 2022/7/16 0016 14:37
 */

public class ThreadDemo03 {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                try {
                    shareResource.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    shareResource.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 1; i <= 15; i++) {
                try {
                    shareResource.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();


    }


}
/**
 * 创建资源类
 */
class ShareResource{
    //定义标志位
    int flag = 1;
    private Lock lock = new ReentrantLock();
    //创建三个Condition
    private Condition c1= lock.newCondition();
    private Condition c2= lock.newCondition();
    private Condition c3= lock.newCondition();

    //打印5次
    public void print5(int loop) throws InterruptedException {
        lock.lock();
        try {
            while(flag!=1){
                c1.await();
            }
            for(int i = 1;i <= 5 ;i++){
                System.out.println(Thread.currentThread().getName()+" :: " +i+"：轮数："+loop);
            }
            flag = 2;
            //通知线程2
            c2.signal();
        } finally {
            lock.unlock();
        }
    }
    //打印10次
    public void print10(int loop) throws InterruptedException {
        lock.lock();
        try {
            while(flag!=2){
                c2.await();
            }
            for(int i = 1;i <= 10 ;i++){
                System.out.println(Thread.currentThread().getName()+" :: " +i+"：轮数："+loop);
            }
            flag = 3;
            //通知线程3
            c3.signal();
        } finally {
            lock.unlock();
        }
    }
    //打印15次
    public void print15(int loop) throws InterruptedException {
        lock.lock();
        try {
            while(flag!=3){
                c3.await();
            }
            for(int i = 1;i <= 15 ;i++){
                System.out.println(Thread.currentThread().getName()+" :: " +i+"：轮数："+loop);
            }
            flag = 1;
            //通知线程1
            c1.signal();
        } finally {
            lock.unlock();
        }
    }

}
