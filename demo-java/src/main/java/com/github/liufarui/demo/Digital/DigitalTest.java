package com.github.liufarui.demo.Digital;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Package: com.github.liufarui.demo.Digital
 * @ClassName: DigitalTest
 * @Description:
 * @Author: liufarui
 * @CreateDate: 2021/4/26
 * @Copyright: Copyright (c)2021 JDL.CN All Right Reserved
 * @Since: JDK 1.8
 * @Version: V1.0
 */
public class DigitalTest {
    @Test
    public void test() {
        // System.out.println(isNumeric("asd234"));
        // System.out.println(isNumeric("234"));
        // System.out.println(isNumeric(""));
        // System.out.println(isNumeric("00000"));

        Map<String, String> map = new HashMap<>();
        print(map);
        System.out.println(Byte.parseByte("5"));
        System.out.println(~0);
        System.out.println(~-1);
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    private void print(Map<String, String> map) {
        String asd = map.get("asd");
        if (!(null == asd)
                && "123".equals(asd)) {
            System.out.println(map.get("asd"));
        }
        if (null != asd
                && "123".equals(asd)) {
            System.out.println(map.get("asd"));
        }
    }
}
