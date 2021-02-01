package com.github.liufarui.springboot.controller;

import com.github.liufarui.springboot.model.ValidationModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/get")
    public void get(@NotBlank(message="examId不能为空") String xxx) {
        System.out.println("!!!!!!!!!!!!");
    }

    @PostMapping("/post")
    public void post(@Valid @RequestBody ValidationModel model) {
        System.out.println("!!!!!!!!!!!!");
    }
}
