package com.github.liufarui.LockDemo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author liufarui
 * @Description:
 * @date 2021/1/30 10:04 下午
 */
public class TestCountDownLatch {
    CountDownLatch latch = new CountDownLatch(5);
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
                latch.countDown();
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s吃完饭走了！\n", value);
        });
    }
}
