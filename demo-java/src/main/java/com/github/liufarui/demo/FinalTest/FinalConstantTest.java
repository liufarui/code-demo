package com.github.liufarui.demo.FinalTest;

import org.junit.Test;

/**
 * @Package: com.github.liufarui.demo.FinalTest
 * @ClassName: FinalConstantTest
 * @Description:
 * @Author: liufarui
 * @CreateDate: 2021/4/19
 * @Copyright: Copyright (c)2021 JDL.CN All Right Reserved
 * @Since: JDK 1.8
 * @Version: V1.0
 */
public class FinalConstantTest {
    @Test
    public void test1() {
        String a = "123456";

        // True
        // 在第一次赋值的时候，字符串会加载到常量池里面
        System.out.println(a == "123456");
    }

    @Test
    public void test2() {
        String a = "123";
        String b = "456";
        String c = a + b;

        // False
        // c的值由于其不确定性，并没有被加载到常量池。
        System.out.println(c == "123456");
    }

    @Test
    public void test3() {
        String a = "123";
        String b = "456";
        String c = a + b;
        c.intern();    //在运行时加载到常量池

        // True
        // 在运行的时候加载到常量池
        System.out.println(c == "123456");
    }

    @Test
    public void test4() {
        final String a = "123";
        final String b = "456";
        String c = a + b;

        // True
        // 由于a和b是final的，所以我们认为c是确定的，在编译的时候就直接将"123456"赋值给了c，其实在运行的时候，c就是一个标准常量
        System.out.println(c == "123456");
    }

    @Test
    public void test5() {
        final String a = "456";
        String c = "123" + a + "789";

        // True
        // 同上，final是会做特殊处理的
        System.out.println(c == "123456789");
    }
}
