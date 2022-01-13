package com.cunyu.algorithm.demo.pool;

import javafx.concurrent.Task;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

class MyTask extends RecursiveTask<Integer>{

    private static  final Integer VALUE = 10;
    private int begin;
    private int end;
    private int result;

    public MyTask(int begin,int end){

        this.begin = begin;
        this.end = end;

    }

    //拆分和合并过程
    @Override
    protected Integer compute(){
        //判断相加两个数值是否大于10
        if((end-begin)<=VALUE){
            for (int i = begin; i <= end; i++) {
                result = result+i;
            }
        }else{
            //获取中间值
            int middle = (begin+end)/2;
            //拆分左边
            MyTask task01 = new MyTask(begin,middle);
            //拆分右边
            MyTask task02 = new MyTask(middle+1,end);
            //调用方法拆分
            task01.fork();
            task02.fork();
            //合并结果
            result = task01.join()+task02.join();
        }
        return null;
    }
}

public class ForkJoinDemo {
    public static void main(String[] args) {
        //创建MyTask对象
        MyTask myTask = new MyTask(0,100);
        //创建分支合并池对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> frokJoinTask = forkJoinPool.submit(myTask);
        //获取最终合并之后结果
        forkJoinPool.get().var

    }
}
