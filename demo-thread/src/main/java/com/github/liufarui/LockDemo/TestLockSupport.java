package com.github.liufarui.LockDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class TestLockSupport {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i == 5) {
                    // 满足条件即停止阻塞
                    LockSupport.park();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();

        // 如果是放在这个位置，先于park之前调用，park不生效。
        // 但是仅仅是第一个park不生效，第二个依旧会停住。
        // LockSupport.unpark(t);

        TimeUnit.SECONDS.sleep(8);
        System.out.println("After 8 seconds!");
        // 线程继续执行
        LockSupport.unpark(t);
    }
}
