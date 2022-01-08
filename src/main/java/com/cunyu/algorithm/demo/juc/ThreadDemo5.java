package com.cunyu.algorithm.demo.juc;

import java.util.concurrent.TimeUnit;

class Phone{
    public static synchronized void sendSMS() throws Exception{
        TimeUnit.SECONDS.sleep(4);
        System.out.println("-----sendSMS");
    }
    public synchronized void sendEmail() throws Exception{
        System.out.println("-----sendEmail");
    }
    public  void getHello(){
        System.out.println("-----getHello");
    }
}

public class ThreadDemo5 {

    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{
            try{
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AA").start();
        Thread.sleep(100);
        new Thread(()->{
            try{
//                phone.sendEmail();
                phone2.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"BB").start();

//        new Thread(()->{
//            try{
//                phone.getHello();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        },"CC").start();

    }
}
