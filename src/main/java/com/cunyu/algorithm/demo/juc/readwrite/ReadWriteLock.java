package com.cunyu.algorithm.demo.juc.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private volatile Map<String,Object> map = new HashMap<>();
    //放数据
    public void put(String key, Object value) throws InterruptedException {
        readWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"正在写操作"+key);
            TimeUnit.MICROSECONDS.sleep(300);
            map.put(key,value);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            readWriteLock.writeLock().unlock();
        }

        System.out.println(Thread.currentThread().getName()+"写完了"+key);
    }
    //取数据
    public Object get(String key){
        readWriteLock.readLock().lock();
        Object result = null;
        try{

            System.out.println(Thread.currentThread().getName()+"正在读取操作"+key);
            TimeUnit.MICROSECONDS.sleep(300);
            result = map.get(key);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }


        System.out.println(Thread.currentThread().getName()+"取完"+key);
        return result;
    }
}
public class ReadWriteLock {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <=5; i++) {
            final int num = i;
            new Thread(()->{
                try {
                    myCache.put(num+"",num+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
        for (int i = 1; i <=5; i++) {
            final int num = i;
            new Thread(()->{
                    myCache.get(num+"");
            },String.valueOf(i)).start();
        }
    }
}
