package com.xlkk.prodAndcust;


/**
 * 线程之间的通信问题：生产者和消费者问题      等待唤醒，通知唤醒
 * A线程和B线程操作同一个变量   设初始num=0
 * A num+1
 * B num-1
 */
public class test1 {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()->{
            for(int i = 0;i<10;i++)
            {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for(int i = 0; i < 10;i++){
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for(int i = 0; i < 10;i++){
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for(int i = 0; i < 10;i++){
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();

    }
}

/**
 * 口诀：（判断）等待，业务 通知
 * 使用synchronized进行处理
 * 当num为0时就等待，
 */
class Data{
    private int num = 0;
    //+1
    public synchronized void increment() throws InterruptedException {
        while(num !=0){
            //等待
            this.wait();
        }
        num++;

        System.out.println(Thread.currentThread().getName() + "=>"+num);
        //通知其他线程，+1完毕
        this.notifyAll();
    }
    //-1
    public synchronized void decrement() throws InterruptedException {
        while(num == 0){
            //等待
            this.wait();
        }
        num--;
        //通知其他线程，-1完毕
        System.out.println(Thread.currentThread().getName() + "=>"+num);
        this.notifyAll();
    }

}
