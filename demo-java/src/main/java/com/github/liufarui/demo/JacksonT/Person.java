package com.github.liufarui.demo.JacksonT;

import lombok.Data;

/**
 * @author liufarui
 * @Description:
 * @date 2021/2/19 5:01 下午
 */
@Data
public class Person {
    String name;
    Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
