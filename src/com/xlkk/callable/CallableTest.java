package com.xlkk.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        FutureTask<String> stringFutureTask1 = new FutureTask<>(myThread);
        new Thread(stringFutureTask1,"A").start();
        new Thread(stringFutureTask1,"B").start();
    }


}
class MyThread implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("call()方法被调用");
        return "fy";
    }
}