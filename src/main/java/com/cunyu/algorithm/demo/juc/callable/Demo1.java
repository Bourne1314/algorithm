package com.cunyu.algorithm.demo.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

//比较两个接口
//实现Runnable接口
class MyThread1 implements  Runnable{
    @Override
    public void run(){

    }
}
class MyThread2 implements Callable{
    @Override
    public Integer call() throws Exception{
        System.out.println(Thread.currentThread().getName()+"come in callable!");
        return 200;
    }
}
//实现Callable接口
public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Runnable接口创建线程
        new Thread(new MyThread1(),"AA").start();
        //Callable接口创建线程，报错
//        new Thread(new MyThread1(),"BB").start();

        //FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());

        //Lam表达式
        FutureTask<Integer> futureTask1 = new FutureTask<>(()->{
            System.out.println(Thread.currentThread().getName()+"come in callable!");
            return 1024;
        });
        //创建

        new Thread(futureTask1,"lucy").start();
        new Thread(futureTask,"mary").start();
//        while(!futureTask1.isDone()){
//            System.out.println("wait.........");
//        }
        //调用FutureTask的get方法
        System.out.println(futureTask1.get());
        System.out.println(futureTask.get());
        System.out.println(Thread.currentThread().getName()+" come over!");
        /**
         * 1、老师上课，口渴了，去买瓶水不合适，讲课线程继续。
         *  但开启线程，找同学帮老师买水，把水买回来，直接get水喝。
         * 2、4个同学，1同学 1+2+。。。+5，2同学 10+11+。。。+50   3同学60+61+62，4同学100+200
         *      第二个同学计算量比较大，需要花更多时间，
         *      FutureTask单开启线程给2同学计算，先汇总 1 3 4，最后等2同学计算完成，统一汇总
         * 3、考试，做会做的题目，最后看不会做的题目
         *
         * 汇总一次
         */

    }
}
