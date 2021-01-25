package com.github.liufarui.LockDemo;

import java.util.ArrayList;
import java.util.List;

public class TestSynchronized {
    private static int count = 2;

    public static synchronized void increment() {
        for (int i = 0; i < 1000000; i++) {
            count++;
        }
        System.out.println(count);
        System.out.println(System.currentTimeMillis());
    }

    public static void main(String[] args) {
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threadList.add(new Thread(TestSynchronized::increment));
        }
        for (Thread thread : threadList) {
            thread.start();
        }
    }
}
