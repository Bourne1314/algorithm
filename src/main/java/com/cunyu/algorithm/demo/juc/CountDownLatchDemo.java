package com.cunyu.algorithm.demo.juc;


import java.util.concurrent.CountDownLatch;

//演示CountDownLatch操作
public class CountDownLatchDemo {
    //6个同学陆续离开教室后，才可以锁门
    public static void main(String[] args) throws InterruptedException {
        //创建CountDownLatch对象，设置初始值
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <=6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"号同学离开了教室。");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        //等待
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"班长锁门走人了。");
    }
}
