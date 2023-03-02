package com.github.liufarui;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liufarui
 * @date ${DATE} ${TIME}
 */
public class Main {
    private int capacity = 100;

    private AtomicInteger water = new AtomicInteger(0);

    private int outRate = 10;

    private long receivedTime;


    @Test
    public void test() {
        Thread leakageT = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    leakage();
                }
            }
        });

        Thread injectionT1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    injection(5);
                }
            }
        });

        Thread injectionT2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    injection(8);
                }
            }
        });

        Thread injectionT3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    injection(15);
                }
            }
        });

        Thread printT = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    print();
                }
            }
        });

        leakageT.start();
        injectionT1.start();
        injectionT2.start();
        injectionT3.start();
        printT.start();

        try {
            Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void injection(int value) {
        try {
            if (water.get() + value < 100) {
                water.weakCompareAndSet(water.get(), water.get() + value);
                System.out.println("injection : " + value + "!");
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void leakage() {
        try {
            water.weakCompareAndSet(water.get(), water.get() - 10);
            System.out.println("Leakage 10!");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void print() {
        try {
            System.out.println("water: " + water);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
