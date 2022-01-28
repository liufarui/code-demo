package com.github.liufarui.demo.ReferenceT;

import org.junit.Test;

import java.io.IOException;

/**
 * @author liufarui
 * @Description:
 * @date 2021/3/2 11:36 下午
 */
public class NormalReferenceT {
    @Test
    public void test() throws IOException {
        M m = new M();
        // 手动使其无引用指向
        m = null;
        // 手动触发垃圾回收
        System.gc();

        // 阻塞当前线程，因为垃圾回收是跑在其他线程里面的
        System.in.read();
    }
}
