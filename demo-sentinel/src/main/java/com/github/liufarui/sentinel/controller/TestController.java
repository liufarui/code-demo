package com.github.liufarui.sentinel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liufarui
 * @date 2023/5/31 23:31
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
