package com.xlkk.threehelperclass.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author xlkk
 * @date 2022/8/9 0009 22:35
 * @Description:
 *  场景：
 *  当前班级有6个同学，当6个同学陆续离开之后班长才可以锁门
 */
public class Demo01 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"号同学离开教室");

                //计数-1
                countDownLatch.countDown();
                },i+"").start();
        }
        //等待同学离开
        countDownLatch.await();
        System.out.println("班长可以锁门了");
    }
}
