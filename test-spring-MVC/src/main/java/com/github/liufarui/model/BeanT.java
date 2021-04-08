package com.github.liufarui.model;

import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author liufarui
 * @Description:
 * @date 2021/4/8 7:11 下午
 */
public class BeanT {
    private String a;
    private String b;
    private String c;

    public String getA() {
        return "a12";
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return "b34";
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return "555";
    }

    // public BeanT() {
    //
    // }

    // public BeanT(BeanT2 t2) {
    //     BeanUtils.copyProperties(t2, this);
    // }

    @Override
    public String toString() {
        return "BeanT{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                '}';
    }
}
