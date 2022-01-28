package com.github.liufarui.demo.ReferenceT;

import org.junit.Test;

import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * @author liufarui
 * @Description:
 * @date 2021/3/2 11:51 下午
 */
public class WeakReferenceT {

    // 只要遇到垃圾回收，就会回收
    // 一般用在容器

    @Test
    public void test() throws InterruptedException, IOException {
        WeakReference<M> m = new WeakReference<>(new M());

        System.out.println(m.get());
        System.gc();

        Thread.sleep(500);

        System.out.println(m.get());

        ThreadLocal<M> tl = new ThreadLocal<>();
        // set内部其实是添加进了map里面
        // map里面的value是tl，key是一个弱引用
        // 如果key不是一个弱引用，则就算tl=null，key依旧指向tl对象本身，tl一直不会被释放
        tl.set(new M());

        // 每个ThreadLocal都可以set多个
        tl.set(new M());

        // 如果不用了，一定要remove掉
        // 要不然key为null的那条记录一直存在，value一直不会被释放，依旧会有内存泄露的问题
        tl.remove();

        // System.in.read();
    }
}

