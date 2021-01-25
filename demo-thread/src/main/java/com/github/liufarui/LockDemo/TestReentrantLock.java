package com.github.liufarui.LockDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liufarui
 * @Description:
 * @date 2021/1/25 11:56 下午
 */
public class TestReentrantLock {
    private static int count = 2;
    private static Lock lock = new ReentrantLock();

    public static void increment() {
        lock.lock();
        try {
            for (int i = 0; i < 1000000; i++) {
                count++;
            }
            System.out.println(count);
            System.out.println(System.currentTimeMillis());
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threadList.add(new Thread(TestReentrantLock::increment));
        }
        for (Thread thread : threadList) {
            thread.start();
        }
    }
}
