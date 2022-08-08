package com.xlkk.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author xlkk
 * @date 2022/8/8 0008 21:30
 * @Description:
 */
public class Demo01 {
    public static void main(String[] args) {
        new Thread(new MyThrea01(),"AA");

        FutureTask futureTask = new FutureTask<>(new MyThread02());
        new Thread(futureTask,"BB");
    }




}

class MyThrea01 implements Runnable {
    @Override
    public void run() {
        System.out.println("run()方法被调用");
    }
}

class MyThread02 implements Callable {
    @Override
    public Integer call() throws Exception {
        return 200;
    }
}
