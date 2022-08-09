package com.xlkk.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author xlkk
 * @date 2022/8/9 0009 22:27
 * @Description:
 */
public class Demo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask1 = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + "come in callable");
            return 1024;
        });
        new Thread(futureTask1, "lk").start();

        while(!futureTask1.isDone()){
            System.out.println("wait......");
        }
        System.out.println(futureTask1.get());
        System.out.println("over......");
    }

}

