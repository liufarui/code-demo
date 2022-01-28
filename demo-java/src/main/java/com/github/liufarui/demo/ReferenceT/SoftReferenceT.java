package com.github.liufarui.demo.ReferenceT;

import org.junit.Test;

import java.lang.ref.SoftReference;

/**
 * @author liufarui
 * @Description:
 * @date 2021/3/2 11:39 下午
 */
public class SoftReferenceT {

    // 主要用于缓存

    @Test
    public void test() {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024 * 1024 * 10]);

        System.out.println(m.get());
        // 这个时候不会被回收
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());

        // -Xms20M -Xmx20M
        // 设置堆内存为20M
        // 这种情况下如果heap装不下，则进行垃圾回收
        byte[] b = new byte[1024 * 1024 * 15];
        System.out.println(m.get());
    }
}
