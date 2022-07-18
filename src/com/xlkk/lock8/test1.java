package com.xlkk.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁问题就是关于锁的八个问题
 * 1.标准情况下，两个线程先打印哪个(先打电话还是先发短信)
 * ---打电话
 * ---发短信
 * 2.打电话方法延迟4s时，谁先打印
 * ---打电话
 * ---发短信
 * 原因：上面两个之所以是先打电话是因为，用的是同一部手机，且方法上都有sychronized关键字，
 * 此时第一个方法执行的时候，synchronized就是上锁，那么另一个方法只能等待，所以是按照顺序执行的
 *
 * 3.新增普通方法hello(),先打电话还是先hello？
 * ---hello
 * ---打电话
 * 原因：这个是因为hello没有上锁，它是一个普通方法，所以它在call()方法sleep的时候直接先执行完了
 *
 * 4.两部手机 先打电话还是先发短信
 * ---发短信
 * ---打电话
 * 原因：两部手机，synchronized锁的是一个对象，两部手机有两个对象，它们没有共用一把锁，
 * 所以是先发短信
 *
 * 5.两个静态同步方法，1部手机，先打电话还是先发短信
 * ---打电话
 * ---发短信
 *
 * 6.两个静态同步方法，两部手机，先打电话还是先发短信
 * ---打电话
 * ---发短信
 * 原因：有static，那么上锁的对象就是Class对象（字节码对象），所以还是按照上锁的顺序来执行
 * （实际上，如果是静态方法的话，执行就是Phone.xx(),所以这样就和第一种类似了）
 *
 * 7.一个静态方法，一个普通方法，一部手机，谁先打印
 * ---发短信
 * ---打电话
 * 8.一个静态方法，一个普通方法，两部手机，谁先打印
 * ---发短信
 * ---打电话
 * 原因：同理，静态方法是Phone.xx(),而普通方法是phone.xx(),synchronized锁的不是同一个对象，
 * 所以，就按照没有上锁的情况处理。
 *
 */
public class test1 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        Phone phone = new Phone();
        new Thread(()->{
            try {
                phone.call();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"A").start();

        TimeUnit.SECONDS.sleep(1 );
//        new Thread(()->{
//            phone.hello();
//        },"Hello").start();
        new Thread(()->{
            phone.sendMsg();
        },"B").start();

    }
}

class Phone {
    public  synchronized void call() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("---打电话");
    }
    public static synchronized void sendMsg(){
        System.out.println("---发短信");
    }

    public void hello(){
        System.out.println("---hello");
    }
}
