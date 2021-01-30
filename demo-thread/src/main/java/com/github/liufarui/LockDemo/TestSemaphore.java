package com.github.liufarui.LockDemo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;

/**
 * @author liufarui
 * @Description:
 * @date 2021/1/30 11:38 下午
 */
public class TestSemaphore {
    Semaphore semaphore = new Semaphore(2);
    List<Thread> threadList = new ArrayList<>();

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 6; i++) {
            Thread t = createT(Integer.toString(i));
            threadList.add(t);
        }
        for (Thread thread : threadList) {
            thread.start();
        }
        int time = 0;
        while (true) {
            System.out.printf("时间已经过去了%s秒\n", time);
            time++;
            Thread.sleep(1000);
        }
    }

    private Thread createT(String value) {
        return new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.printf("%s执行了！\n", value);
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.release();
        });
    }
}
