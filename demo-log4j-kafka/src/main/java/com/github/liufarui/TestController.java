package com.github.liufarui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger logger = LoggerFactory.getLogger("JDLTransactionEvenLogger");

    @GetMapping
    public String test() {
        String aaa = "{\"name\": \"asd\", \"timestamp\": " + System.currentTimeMillis() + "}";
        logger.info(aaa);
        System.out.println(aaa);
        return "Test";
    }

    @GetMapping("hello")
    public String hello() {
        return "Hello World";
    }
}
