package com.github.liufarui.ThreadDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class DemoThreadDemoCallable implements Callable<Integer> {

    private volatile static int count = 0;

    public Integer call() {
        System.out.println("我是callable，我执行了一次");
        return count++;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(5, 10,
                100, MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
        Future<Integer> future = tpe.submit(new DemoThreadDemoCallable());

        List<FutureTask<Integer>> taskList = new ArrayList<FutureTask<Integer>>();

        for (int i = 0; i < 3; i++) {
            FutureTask<Integer> futureTask = new FutureTask<Integer>(new DemoThreadDemoCallable());
            taskList.add(futureTask);
        }

        for (FutureTask<Integer> task : taskList) {
            Thread.sleep(1000);
            new Thread(task).start();
        }

        // 一般这个时候是做一些别的事情
        Thread.sleep(1000);
        for (FutureTask<Integer> task : taskList) {
            // get方法会一直阻塞
            System.out.printf("我是第%s次执行的！\n", task.get());
        }
    }
}
