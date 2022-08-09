package com.xlkk.threehelperclass.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author xlkk
 * @date 2022/8/9 0009 22:45
 * @Description:
 * 循环栅栏：CyclicBarrier
 * 场景：
 *  集齐七颗龙珠---召唤神龙
 */
public class Demo01 {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(7,()->{
            System.out.println("集齐七颗龙珠召唤神龙");
        });
        //开始收集龙珠
        for (int i = 1; i <= 7; i++) {
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"号龙珠被收集了");
                    //进入等待
                    barrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            },i+"").start();

        }
    }
}
