package com.cunyu.algorithm.demo.juc;

import javax.sound.midi.Soundbank;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

class Numberope{
    public int number = 0;

//    private final ReentrantLock lock = new ReentrantLock();
    public synchronized void incr() throws InterruptedException {
        if(number!=0){//判断number值是否是0，如果不是0，则等待
            this.wait();
        }
        //如果number值是0，如果是0，则+1操作

        number++;
        System.out.println(Thread.currentThread().getName()+":"+number);

        this.notifyAll();

    }

    public synchronized  void deincr() throws InterruptedException{
        if(number!=1){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+":"+number);
        this.notifyAll();
    }

}

public class plusminus {
    public static void main(String[] args) {

        Numberope numberope = new Numberope();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    numberope.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"AA").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    numberope.deincr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"BB").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    numberope.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"CC").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    numberope.deincr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"DD").start();
    }
}
