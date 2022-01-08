package com.cunyu.algorithm.demo.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SyncLockDemo {
    public synchronized void add(){
        add();
    }
    public static void main(String[] args) {
        //演示Lock可重入锁
        Lock lock = new ReentrantLock(true);
        //创建线程
        new Thread(()->{
            try{
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"外层");
                try{
                    lock.lock();
                    System.out.println(Thread.currentThread().getName()+"内层");
                }finally {
//                    lock.unlock();
                }
            }finally {
                lock.unlock();
            }
        },"t1").start();
        new Thread(()->{
            lock.lock();
            System.out.println("aaa");
            lock.unlock();
        },"AA").start();
//        new SyncLockDemo().add();
//        Object o = new Object();
//        new Thread(()->{
//            synchronized (o){
//                System.out.println(Thread.currentThread().getName()+"外层");
//                synchronized (o){
//                    System.out.println(Thread.currentThread().getName()+"中层");
//                    synchronized (o){
//                        System.out.println(Thread.currentThread().getName()+"内层");
//                    }
//                }
//            }
//        },"t1").start();
    }
}
