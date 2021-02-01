package com.github.liufarui.springboot.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @author liufarui
 * @Description:
 * @date 2021/2/1 6:20 下午
 */
@RestController
@RequestMapping("/ValidationController")
@Validated
public class ValidationController {

    @GetMapping("/bbb")
    public void bbb(@NotBlank(message="examId不能为空") String xxx) {
        System.out.println("asdada");;
    }
}
