package com.github.liufarui.demo.Log4JT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liufarui
 * @Description:
 * @date 2021/3/14 11:55 下午
 */
public class Log4JT {
    // slf4j日志记录器
    private static final Logger logger = LoggerFactory.getLogger(Log4JT.class);
    // slf4j日志记录器
    // private static final Logger logger = LoggerFactory.getLogger(Log4JT.class.getName());

    public static void main(String[] args) {
        logger.info("11{},22{}", "aa", "bb");
        try {
            int x = 1 / 0;
        } catch (Exception e) {
            logger.error("11{},22{}", "aa", "bb", e);
        }

    }
}
