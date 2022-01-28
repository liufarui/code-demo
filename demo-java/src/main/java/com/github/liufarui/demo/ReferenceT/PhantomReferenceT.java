package com.github.liufarui.demo.ReferenceT;

import org.junit.Test;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * @author liufarui
 * @Description:
 * @date 2021/3/3 12:11 上午
 */
public class PhantomReferenceT {
    // 虚引用
    // 主要是为了管理堆外内存
    // 主要是给写虚拟机「JVM」的人用的「写JVM的人」
    // -Xms20M -Xmx20M
    // 弱引用想拿里面的值还是可以拿到的，但是虚引用永远拿不到
    // 就是给你一个通知，垃圾回收的通知
    // DirectByteBuffer是可以从JVM直接指向堆外内存的，所以是无法直接回收的
    // 虚引用指向DirectByteBuffer，检测到虚引用被回收的时候，通过QUEUE去清理堆外内存
    // Java里面做堆外内存的访问，可以用Unsafe「allocateMemory可以直接分配内存」

    private static final List<Object> LIST = new LinkedList<>();
    private static final ReferenceQueue<M> QUEUE = new ReferenceQueue<>();

    @Test
    public void test() throws IOException {
        PhantomReference<M> phantomReference = new PhantomReference<>(new M(), QUEUE);

        new Thread(() -> {
            while (true) {
                LIST.add(new byte[1024 * 1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                System.out.println(phantomReference.get());
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Reference<? extends M> poll = QUEUE.poll();
                if (poll != null) {
                    System.out.println("------虚引用对象被JVM回收了------ " + poll);
                }
            }
        }).start();

        System.in.read();
    }
}
