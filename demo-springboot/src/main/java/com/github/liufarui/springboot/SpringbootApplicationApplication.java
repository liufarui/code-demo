package com.github.liufarui.springboot;

import com.github.liufarui.springboot.config.ConfigBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @author liufarui
 */
@SpringBootApplication
public class SpringbootApplicationApplication implements CommandLineRunner {

    @Resource
    ConfigBean configBean;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplicationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(configBean.toString());
    }
}
