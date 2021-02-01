package com.github.liufarui.springboot.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author liufarui
 * @Description:
 * @date 2021/2/1 9:45 下午
 */
@Data
public class ValidationModel {
    @NotNull
    Integer id;

    @NotBlank
    String name;

    @Min(0)
    int age;
}
