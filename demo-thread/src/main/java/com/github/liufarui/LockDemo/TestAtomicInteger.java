package com.github.liufarui.LockDemo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @author liufarui
 * @Description:
 * @date 2021/1/30 6:50 下午
 */
public class TestAtomicInteger {
    AtomicInteger atomicIntegerNum = new AtomicInteger(0);
    int num = 0;
    @Test
    public void test() throws InterruptedException {
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    atomicIntegerNum.incrementAndGet();
                    num++;
                }
            });
            threadList.add(t);
        }
        for (Thread thread : threadList) {
            thread.start();
        }
        Thread.sleep(8);
        System.out.println(atomicIntegerNum);
        System.out.println(num);
    }
}
