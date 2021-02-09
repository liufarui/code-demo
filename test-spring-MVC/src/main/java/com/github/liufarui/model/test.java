package com.github.liufarui.model;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

import com.sun.tools.javac.util.StringUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Set;

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
//        System.out.println(jsonStr2Map(JSONObject.parseObject("{\"value\":\"222222\", \"value1\":\"\",\"value2\":\"\"}")));
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

    public static HashMap jsonStr2Map(String jOStr) {
        HashMap data = new HashMap();
        JSONObject jO = JSONObject.parseObject(jOStr);
        Set keys = jO.keySet();
        // 遍历jsonObject数据，添加到Map对象
        for (Object key : keys) {
            String value = (String) jO.get(key);
            data.put(key, value);
        }
        return data;
    }

    @Test
    public void test3() {
        System.out.println(getProductInfo("{\"serviceCode\":\"SVC-INSURE\",\"serviceValue\":\"{\\\"value\\\":\\\"222222\\\", \\\"value1\\\":\\\"\\\",\\\"value2\\\":\\\"\\\"}\"}"));
    }

    public static HashMap getProductInfo(Map serviceMap) {
        HashMap data = new HashMap();
        String serviceCode = (String) serviceMap.get("serviceCode");
        String productNo;
        HashMap<String, String> productAttrs = new HashMap<>();
        if ("SVC-INSURE".equals(serviceCode)) {
            productNo = "ed-a-0002";
            productAttrs.put("guaranteeMoney", (String) jsonStr2Map((String) serviceMap.get("serviceValue")).get("value"));
        } else if ("SVC-COD".equals(serviceCode)) {
            productNo = "ed-a-0009";
            productAttrs.put("shouldPayMoney", (String) jsonStr2Map((String) serviceMap.get("serviceValue")).get("value"));
        }
        data.put("productNo", productNo);
        data.put("productAttrs", productAttrs);
        return data;
    }

    public static int getProductType(Map serviceMap) {
        String serviceValue = (String) jsonStr2Map((String) serviceMap.get("serviceValue")).get("value");
        if (null == serviceValue) {
            return serviceValue;
        } else switch (serviceValue) {
            case "2":
                return "usual";
            case "7":
                return "cold";
            case "8":
                return "freezing";
            case "9":
                return "deepCool";
            default:
                return serviceValue;
        }
    }
}
