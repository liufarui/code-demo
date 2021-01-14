package com.github.liufarui.ThreadDemo;

import java.util.concurrent.*;

public class DemoThreadDemoCallablePool {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<Integer> future = threadPool.submit(new DemoThreadDemoCallable());
        try {
            System.out.println(future.get());
        } catch (Exception e) {
            System.err.print(e.getMessage());
        } finally {
            threadPool.shutdown();
        }
    }
}
