package com.xlkk.SaleTicket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock接口，实现
 */
public class SaleTicketDemo02 {
    public static void main(String[] args) {
        Ticket02 ticket = new Ticket02();
        //jdk1.8之后  使用lambda表达式 (方法参数)->{代码}
        //线程A
        new Thread(()->{
            for(int i= 1;i<=30;i++){
                ticket.sale();
            }
        },"A").start();
        //线程B
        new Thread(()->{
            for(int i= 1;i<=30;i++){
                ticket.sale();
            }
        },"B").start();
        //线程C
        new Thread(()->{
            for(int i= 1;i<=30;i++){
                ticket.sale();
            }
        },"C").start();
    }

}
    class Ticket02{
    private int num = 30;

    Lock lock = new ReentrantLock(true);

    //买票
    public  void sale(){
        //加锁
        lock.lock();
        //为什么要用try catch呢？是防止出现异常，到时候锁就没有释放，会出现死锁
        try{
            //业务代码
            if(num>0){
                System.out.println(Thread.currentThread().getName()+"卖出了第"+num--+"张票，剩余"+num+"张票");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            //解锁：
            lock.unlock();
        }

    }

}
