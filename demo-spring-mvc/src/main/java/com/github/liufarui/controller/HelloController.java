package com.github.liufarui.controller;

import com.github.liufarui.model.BeanTT;
import com.github.liufarui.model.ModelT;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lfr
 * @date 2020/11/30 下午3:53
 */
@Controller
@RequestMapping(value = "/hello")
@CrossOrigin(origins = "*")
public class HelloController {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        return new BeanTT().toString();
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public String hello(HttpServletRequest request, HttpServletResponse response) {
        return "Hello, World";
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String post(@ModelAttribute ModelT t) {
        return "Hello World" + t.getAaa();
    }
}
