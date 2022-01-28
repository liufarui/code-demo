package com.github.liufarui.demo.System;

/**
 * @Package: com.github.liufarui.demo.System
 * @ClassName: GCT
 * @Description: 代码验证手动触发 Full GC
 * @Url: https://www.jianshu.com/p/6070c70cc69f
 * @Author: liufarui
 * @CreateDate: 2021/5/23 11:04 下午
 * @Copyright: Copyright (c)2021 JDL.CN All Right Reserved
 * @Since: JDK 1.8
 * @Version: V1.0
 */
public class GCT {
    private int _10MB = 10 * 1024 * 1024;
    private byte[] memory = new byte[8 * _10MB];


    /**
     * 加上参数 -Xms1024m -Xmx1024m -Xmn512m -XX:+PrintGCDetails
     *
     * @param args
     */
    public static void main(String[] args) {
        method01();
        System.out.println("返回main方法");
        System.gc();
        System.out.println("第二次GC完成");
    }

    public static void method01() {
        GCT t = new GCT();
        System.gc();
        // 或者下面，两者等价
        // Runtime.getRuntime().gc();
        // java.lang.management.MemoryMXBean.gc();
        System.out.println("第一次GC完成");
    }
}
