package com.github.liufarui.demo;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author liufarui
 * @date 2021/9/18 21:55
 */
public class HmacUtil {
    public enum Algorithm {
        hmac_md5("HMacMD5"), hmac_sha512("HMacSHA512"), hmac_sha256("HMacSHA256"), hmac_sha1("hmac-sha1"), md5_salt("md5-salt");
        private String name;

        Algorithm(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private static final String ENCODING = "UTF-8";

    //获取GMT时间（注意时区问题，非GMT的一定要转成GMT）
    private static String getServerTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(calendar.getTime());
    }

    //获取MD5签名摘要
    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }//获取hamc-sha1签名摘要

    /***
     * MD5加盐算法
     * @param secret
     * @param txt
     * @return
     */
    public static String MD5_SALT(String secret, String txt) {
        String md5Sign = MD5(secret + txt + secret);
        String sign = md5Sign.toLowerCase(); //如果已经是小写了则不需要转
        return sign;
    }

    /*****
     * HAMC 算法集合
     * @param encryptText
     * @param encryptKey
     * @param algorithm
     * @return
     * @throws Exception
     */
    public static String HMAC(String encryptText, String encryptKey, Algorithm algorithm) throws Exception {
        String macName = null;
        if (algorithm == Algorithm.hmac_sha1) {
            macName = "HMacSHA1";
        } else {
            macName = algorithm.getName();
        }
        byte[] digest = HmacEncrypt(encryptText, encryptKey, macName);
        //对摘要进行base64编码
        String sign = Base64.encodeBase64String(digest);
        return sign;
    }

    private static byte[] HmacEncrypt(String encryptText, String encryptKey, String macName) throws Exception {
        byte[] data = encryptKey.getBytes(ENCODING);
        //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, macName);
        //生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(macName);
        //用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = encryptText.getBytes(ENCODING);
        //完成 Mac 操作
        byte[] digest = mac.doFinal(text);
        //注意此处进行了base64编码
        return digest;
    }

    /**
     * 获取Hmac签名头信息，调用方需要将此方法生成的头信息放到HTTP头信息中
     *
     * @param userName             签名用户名
     * @param secret               签名密钥
     * @param algorithm            签名算法
     * @param extendSignProperties 签名扩展属性，可选
     * @return
     */
    public static Map<String, String> makeHmacHeaders(String userName, String secret, Algorithm algorithm, Map<String, String> extendSignProperties) {
        if (userName == null || secret == null || algorithm == null) {
            throw new java.lang.IllegalArgumentException("用户名/密码/签名算法都不能为空");
        }
        Map<String, String> result = new HashMap<String, String>();
        String X_Date = getServerTime();
        String txt = "X-Date: " + X_Date;
        String headers = "X-Date";
        result.put("X-Date", X_Date);
        if (extendSignProperties != null) {
            for (Map.Entry<String, String> entry : extendSignProperties.entrySet()) {
                txt = txt + "\n" + entry.getKey() + ": " + entry.getValue();
                headers = headers + " " + entry.getKey();
                result.put(entry.getKey(), entry.getValue());
            }
        }
        String sign = null;
        if (algorithm == Algorithm.hmac_sha1 || algorithm == Algorithm.hmac_sha256 || algorithm == Algorithm.hmac_sha512
                || algorithm == Algorithm.hmac_md5
        ) {
            //hmac-sha1算法获取签名串方法
            try {
                sign = HMAC(txt, secret, algorithm);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (algorithm == Algorithm.md5_salt) {
            sign = MD5_SALT(secret, txt);
        } else {
            throw new RuntimeException("不支持的签名算法:" + algorithm.getName());
        }
        result.put("Authorization", "hmac username=\"" + userName + "\", algorithm=\"" + algorithm.getName() + "\", headers=\"" + headers + "\",signature=\"" + sign + "\"");
        return result;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=========================================makeHmacHeaders_sample===========================================");
        makeHmacHeaders_sample(args);
        System.out.println("=============================================sign_sample==================================================");
        sign_sample(args);
    }

    public static void makeHmacHeaders_sample(String[] args) throws Exception {
        // 参数说明：
        // userName:用户名：由服务提供方提供
        // secret:密码：由服务提供方提供
        // algorithm 算法类型见Algorithm枚举值,一般使用hmac_sha1，若接入方系统不支持hmac_sha算法，可以选择使用md5_salt算法，需要调用方参考算法的java代码自行实现。
        // extendProperties 扩展属性集合：扩展属性用于增加签名生成的复杂度，提高网关调用的安全性，可以根据需要自行增加，也可以不定义扩展属性
        Map<String, String> extendProperties = new HashMap();
        //例如需要支持防篡改，可以增加属性名称为md5-content扩展属性（属性名称可自定义），值为请求体的Md5摘要
        extendProperties.put("signature-summary", HmacUtil.MD5("requestbody"));
        Map<String, String> headers = HmacUtil.makeHmacHeaders("JDC4UAT", "123456", Algorithm.hmac_sha256, extendProperties);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        /******
         * 上述代码参考输出如下，请将生成的这些字段放到HTTP请求头中一起发送给网关
         * Authorization=hmac username="user1", algorithm="hmac-sha1", headers="X-Date md5-content",signature="UNSFSTZbiUJUnP2bXGbeQY2Ixjk="
         * X-Date=Wed, 13 Mar 2019 09:21:56 GMT
         * md5-content=ee2ae9240c717598de2a3cb86bc9fa1d
         */
    }

    public static void sign_sample(String[] args) throws Exception {

        System.out.println("MD5_SALT:" + MD5_SALT("123456", "v1v2v3"));
        System.out.println("hmac_sha1:" + HMAC("123456v1v2v3123456", "1234567890", Algorithm.hmac_sha1));
        System.out.println("hmac_md5:" + HMAC("123456v1v2v3123456", "1234567890", Algorithm.hmac_md5));
        System.out.println("hmac_sha256:" + HMAC("123456v1v2v3123456", "1234567890", Algorithm.hmac_sha256));
        System.out.println("hmac_sha512:" + HMAC("123456v1v2v3123456", "1234567890", Algorithm.hmac_sha512));
    }
}
