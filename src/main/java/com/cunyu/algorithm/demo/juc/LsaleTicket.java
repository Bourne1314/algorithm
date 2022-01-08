package com.cunyu.algorithm.demo.juc;


import java.util.concurrent.locks.ReentrantLock;

class Ticket1{
    //票数
    private int number =  30;
    private final ReentrantLock lock = new ReentrantLock(true);
    public  void sale(){
        lock.lock();
        try{
            if(number>0){
                System.out.println(Thread.currentThread().getName()+" :卖出："+(number--)+"剩下:"+number);
            }
        }
        finally {
            lock.unlock();
        }
    }

}

public class LsaleTicket {
    public static void main(String[] args) {
        Ticket1 ticket = new Ticket1();

        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        },"CC").start();
    }
}
