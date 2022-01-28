package com.github.liufarui.demo.ReferenceT;

/**
 * @author liufarui
 * @Description:
 * @date 2021/3/2 11:36 下午
 */
public class M {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("M finalize");
    }
}
