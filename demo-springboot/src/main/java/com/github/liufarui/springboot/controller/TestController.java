package com.github.liufarui.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liufarui
 * @Description:
 * @date 2021/2/1 8:48 下午
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping
    public String test() {
        return "Test";
    }

    @GetMapping("hello")
    public String hello() {
        return "Hello World";
    }
}
