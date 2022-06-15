package com.github.liufarui.springboot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author liufarui
 * @date 2022/6/14 22:13
 */
@Component
@Data
public class ConfigBean {
    @Value("${server.port}")
    private String port;

    @Value("${person.name}")
    private String name;

    @Value("${person.age}")
    private Integer age;

    @Value("${person.sex}")
    private String sex;

    @Value("${flow.name}")
    private String flowName;

    @Value("${mysql.username}")
    private String username;
}
