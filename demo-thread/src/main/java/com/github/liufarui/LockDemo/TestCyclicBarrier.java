package com.github.liufarui.LockDemo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author liufarui
 * @Description:
 * @date 2021/1/30 11:18 下午
 */
public class TestCyclicBarrier {
    CyclicBarrier barrier = new CyclicBarrier(5);
    List<Thread> threadList = new ArrayList<>();

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Thread t = createT(Integer.toString(i));
            threadList.add(t);
        }
        for (Thread thread : threadList) {
            thread.start();
        }
        Thread.sleep(2000);
    }

    private Thread createT(String value) {
        return new Thread(() -> {
            System.out.printf("%s来吃饭了！\n", value);
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.printf("%s吃完饭走了！\n", value);
        });
    }
}
