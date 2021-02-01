package com.github.liufarui.LockDemo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author liufarui
 * @Description:
 * @date 2021/1/31 2:59 下午
 */
public class TestReadWriteLock {
    ReadWriteLock lock = new ReentrantReadWriteLock(true);
    List<Thread> threadList = new ArrayList<>();

    @Test
    public void test() throws InterruptedException {
//        for (int i = 0; i < 5; i++) {
//            Thread t = createReadT(Integer.toString(i));
//            threadList.add(t);
//        }
//        for (int i = 6; i < 9; i++) {
//            Thread t = createWriteT(Integer.toString(i));
//            threadList.add(t);
//        }
//        for (int i = 9; i < 12; i++) {
//            Thread t = createReadT(Integer.toString(i));
//            threadList.add(t);
//        }
        for (int i = 0; i < 100; i = i + 2) {
            Thread t1 = createWriteT(Integer.toString(i));
            threadList.add(t1);
            Thread t2 = createReadT(Integer.toString(i + 1));
            threadList.add(t2);
        }
        for (Thread thread : threadList) {
            thread.start();
        }
        Thread.sleep(1000000);
    }

    private Thread createReadT(String value) {
        return new Thread(() -> {
            try {
                lock.readLock().lock();
                System.out.printf("%s开始读！\n", value);
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
            } finally {
                System.out.printf("%s已读完！\n", value);
                lock.readLock().unlock();
            }
        });
    }

    private Thread createWriteT(String value) {
        return new Thread(() -> {
            try {
                lock.writeLock().lock();
                System.out.printf("%s开始写！\n", value);
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
            } finally {
                System.out.printf("%s已写完！\n", value);
                lock.writeLock().unlock();
            }
        });
    }
}
