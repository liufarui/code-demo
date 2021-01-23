package com.github.liufarui.Other;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

/**
 * @author liufarui
 * @Description:
 * @date 2021/1/22 下午11:47
 */
public class DemoVarHandle {

    int x = 9;
    private static VarHandle handle;

    public static void main(String[] args) {
        handle = MethodHandles.lookup().findVarHandle(DemoVarHandle.class, "x", int.class);
    }
}
