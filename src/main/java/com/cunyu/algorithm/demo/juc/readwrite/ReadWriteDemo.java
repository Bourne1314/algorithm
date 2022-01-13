package com.cunyu.algorithm.demo.juc.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;
//锁降级
public class ReadWriteDemo {
    public static void main(String[] args) {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

        //加写锁
        writeLock.lock();
        System.out.println("_____Write");
//        writeLock.unlock();
        //加读锁
        readLock.lock();
        System.out.println("_____read");
        //解读锁
        writeLock.unlock();
        //解写锁
        readLock.unlock();
    }
}
