package com.cunyu.algorithm.demo.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource{

    //标志位
    private int flag = 1;
    //创建lock锁
    private Lock lock = new ReentrantLock();

    Condition c1 = lock.newCondition();

    Condition c2 = lock.newCondition();

    Condition c3 = lock.newCondition();

    //打印5次，参数第几轮
    public void print5( int loop) throws InterruptedException {
        lock.lock();
        try{
            while(flag!=1){
                c1.await();
            }
            for (int i = 0; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()+"::"+i+"：轮数:"+loop);
            }
            flag=2;
            c2.signal();
        } finally {
            lock.unlock();
        }
    }
    //打印10次，参数第几轮
    public void print10( int loop) throws InterruptedException {
        lock.lock();
        try{
            while(flag!=2){
                c2.await();
            }
            for (int i = 0; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+"::"+i+"：轮数:"+loop);
            }
            flag=3;
            c3.signal();
        } finally {
            lock.unlock();
        }
    }
    //打印5次，参数第几轮
    public void print15( int loop) throws InterruptedException {
        lock.lock();
        try{
            while(flag!=3){
                c3.await();
            }
            for (int i = 0; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"::"+i+"：轮数:"+loop);
            }
            flag=1;
            c1.signal();
        } finally {
            lock.unlock();
        }
    }


}

public class ThreadDemo3 {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        int loop = 10;
        new Thread(()->{
            for (int i = 0; i <= 10; i++) {
                try{
                    shareResource.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 0; i <= 10; i++) {
                try{
                    shareResource.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 0; i <= 10; i++) {
                try{
                    shareResource.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();
    }
}
