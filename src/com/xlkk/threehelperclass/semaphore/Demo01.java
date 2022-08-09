package com.xlkk.threehelperclass.semaphore;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author xlkk
 * @date 2022/8/9 0009 22:51
 * @Description:
 * 信号量：Semaphore
 * 场景：
 * 抢车位，6辆汽车，三个停车位
 */
public class Demo01 {
    public static void main(String[] args) {
        //车位限制：3
        Semaphore semaphore = new Semaphore(3);
        //模拟6辆汽车
        for (int i = 1; i <= 6 ; i++) {
            new Thread(()->{
                try {
                    //抢占车位
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"号车抢到了1个车位");
                    //设置停车时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName()+"号车离开车位");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    //释放车位
                    semaphore.release();
                }
            },i+"").start();
        }
    }
}
