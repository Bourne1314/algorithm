package com.cunyu.algorithm.demo.juc;

import java.util.concurrent.locks.ReentrantLock;

class Ticket{
    //票数
    private int number =  30;
    public synchronized void sale(){
        if(number>0) {
            System.out.println(Thread.currentThread().getName() + " :卖出：" + (number--) + "剩下:" + number);
        }

    }

}

public class soldTicket {

    public static void main(String[] args) {


        Ticket ticket = new Ticket();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i< 40;i++){
                    ticket.sale();
                }
            }
        },"AA"
        ).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i< 40;i++){
                    ticket.sale();
                }
            }
        },"BB"
        ).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i< 40;i++){
                    ticket.sale();
                }
            }
        },"CC"
        ).start();
    }


}
