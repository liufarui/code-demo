package com.github.liufarui.demo.System;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @Package: com.github.liufarui.demo.System
 * @ClassName: ForkJoinT
 * @Description: 并行流
 * @Author: liufarui
 * @CreateDate: 2021/5/23 11:28 下午
 * @Copyright: Copyright (c)2021 JDL.CN All Right Reserved
 * @Since: JDK 1.8
 * @Version: V1.0
 */
public class ForkJoinT extends RecursiveTask<Long> {

    private long[] numbers;
    private int start;
    private int end;

    public ForkJoinT(long[] numbers) {
        this.numbers = numbers;
        this.start = 0;
        this.end = numbers.length;
    }

    public ForkJoinT(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length < 20000) {  //小于20000个就不在进行拆分
            return sum();
        }
        ForkJoinT leftTask = new ForkJoinT(numbers, start, start + length / 2); //进行任务拆分
        ForkJoinT rightTask = new ForkJoinT(numbers, start + (length / 2), end); //进行任务拆分
        leftTask.fork(); //把该子任务交友ForkJoinPoll线程池去执行
        rightTask.fork(); //把该子任务交友ForkJoinPoll线程池去执行
        return leftTask.join() + rightTask.join(); //把子任务的结果相加
    }


    private long sum() {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }


    public static void main(String[] args) {
        Long result1 = LongStream.rangeClosed(1, 100000000)
                .reduce(0, Long::sum);
        System.out.println("result：" + result1);

        long[] numbers = LongStream.rangeClosed(0, 100000000).toArray();

        Long result2 = new ForkJoinPool().invoke(new ForkJoinT(numbers));
        System.out.println("result：" + result2);
    }
}
