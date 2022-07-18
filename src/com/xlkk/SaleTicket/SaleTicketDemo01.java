package com.xlkk.SaleTicket;

public class SaleTicketDemo01 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        //jdk1.8之后  使用lambda表达式 (方法参数)->{代码}
        //线程A
        new Thread(()->{
            for(int i= 1;i<90;i++){
                ticket.sale();
            }
        },"A").start();
        //线程B
        new Thread(()->{
            for(int i= 1;i<90;i++){
                ticket.sale();
            }
        },"B").start();
        //线程C
        new Thread(()->{
            for(int i= 1;i<90;i++){
                ticket.sale();
            }
        },"C").start();
    }

}
class Ticket{
    private int num = 80;

    //买票
    public synchronized void sale(){
        if(num>0){
            System.out.println(Thread.currentThread().getName()+"卖出了第"+num--+"张票，剩余"+num+"张票");
        }
    }

}
