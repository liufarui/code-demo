package com.github.liufarui.model;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import static java.time.Instant.ofEpochMilli;
import static java.time.ZoneOffset.UTC;
import static java.time.ZonedDateTime.ofInstant;
import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

/**
 * @author liufarui
 * @Description:
 * @date 2021/2/5 10:38 下午
 */
public class test {

    private static final DateTimeFormatter GMT_FORMATTER = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z");
//    private static final DateTimeFormatter GMT_FORMATTER = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");

//    public static void main(String[] args) {
//        System.out.println(GMT(1612536062859L));
//    }
//
//    public static String GMT(long milliseconds) {
//        return GMT_FORMATTER.format(ofInstant(ofEpochMilli(milliseconds), UTC));
//    }

    @Test
    public void test() {
        testMd5Hex("aaa", "ass");
        System.out.println(signBaichuan("asdas", "asdasd", 1612608024829L));
    }

    private void testMd5Hex(String... args) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (String arg : args) {
            sb.append(arg);
            sb.append(",");
        }
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");

        System.out.println(md5Hex(sb.toString()));
    }

    public static String signBaichuan(String appKey, String appSecret, Long timestamp) {
        return getMd5Hex(appSecret, appKey, getStandardTimestamp(timestamp), appSecret);
    }

    public static String getStandardTimestamp(Long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(timestamp);
    }

    private static String getMd5Hex(String... args) {
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg);
        }
        return md5Hex(sb.toString());
    }
}
