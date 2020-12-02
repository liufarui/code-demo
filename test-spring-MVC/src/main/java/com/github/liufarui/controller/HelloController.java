package com.github.liufarui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lfr
 * @date 2020/11/30 下午3:53
 */
@Controller
@RequestMapping(value = "hello")
public class HelloController {
    @RequestMapping(method= RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {

        //创建ModelAndView对象
        ModelAndView mav = new ModelAndView();
        //向模型对象中添加数据
        mav.addObject("msg","我是酷酷的猿！");
        //设置逻辑视图名
        mav.setViewName("/WEB-INF/jsp/hello.jsp");
        //返回ModelAndView对象
        return mav;
    }

    @RequestMapping(value = "/hello", method= RequestMethod.GET)
    public String hello(HttpServletRequest request, HttpServletResponse response) {
        return "Hello, World";
    }
}
