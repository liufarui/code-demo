package com.jd.lsb.edi.ByteDance;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Set;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.HashSet;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringEscapeUtils;

import com.jd.lsb.edi.annotation.EDI;

import static java.time.Instant.ofEpochMilli;
import static java.time.ZoneOffset.UTC;
import static java.time.ZonedDateTime.ofInstant;
import static java.util.Base64.getEncoder;
import static org.apache.commons.codec.digest.DigestUtils.md5Hex;
import static org.apache.commons.codec.digest.HmacAlgorithms.HMAC_SHA_1;
import static org.apache.commons.codec.digest.HmacUtils.getInitializedMac;

@EDI(author = "jizhuozhi")
public final class EDIFunction {

    private static final DateTimeFormatter GMT_FORMATTER = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z");
    static HashMap<String, String> errorCodeMap = new HashMap<>();
    static HashMap<String, String> errorMessageMap = new HashMap<>();

    @EDI(prefix = "fn", name = "join", desc = "拼接报文")
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static String join(Object object, String secret) {
        Map commonMap = null;
        Object signature = null;
        if (object instanceof Map) {
            Object common = ((Map<?, ?>) object).get("common");
            if (common instanceof Map) {
                commonMap = (Map) common;
            }
        }
        if (commonMap != null) {
            signature = commonMap.remove("signature");
        }
        StringBuilder stringBuilder = new StringBuilder();
        doJoin(object, stringBuilder);
        if (commonMap != null && signature != null) {
            commonMap.put("signature", signature);
        }
        return stringBuilder.append(secret).toString();
    }

    /**
     * 获取Hmac签名头信息，调用方需要将此方法生成的头信息放到HTTP头信息中
     *
     * @param username             签名用户名
     * @param secret               签名密钥
     * @param algorithm            签名算法
     * @param extendSignProperties 签名扩展属性，可选
     * @return headers
     */
    private static String assembleAuthorizationHeader(String username, String secret, String algorithm,
                                                      Map<String, String> extendSignProperties) {
        if (username == null || secret == null || algorithm == null) {
            throw new IllegalArgumentException("用户名/密码/签名算法都不能为空");
        }
        String content = assembleExtendedSignContent(extendSignProperties);
        String headers = assembleExtendedSignHeaders(extendSignProperties);
        String sign = generateSign(algorithm, secret, content);
        return MessageFormat.format("hmac username=\"{0}\", algorithm=\"{1}\", headers=\"{2}\", signature=\"{3}\"",
                username, algorithm, headers, sign);
    }

    @EDI(prefix = "fn", name = "hmac_authorization", desc = "HMac签名头")
    public static String authorization(String username, String secret, String algorithm, long milliseconds, String... contents) {
        Map<String, String> extendSignProperties = new LinkedHashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        extendSignProperties.put("X-Date", dateFormat.format(milliseconds));
        extendSignProperties.put("md5-content", md5(contents));
        return assembleAuthorizationHeader(username, secret, algorithm, extendSignProperties);
    }

    @EDI(prefix = "fn", name = "hmac_md5", desc = "MD5签名")
    public static String md5(String... contents) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (String arg : contents) {
            sb.append(arg);
            sb.append(",");
        }
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");

        return md5Hex(sb.toString());
    }

    @EDI(prefix = "fn", name = "hmac_GMT", desc = "GMT时间")
    public static String GMT(long milliseconds) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(milliseconds);
    }

    @EDI(prefix = "fn", name = "unescapeJson", desc = "字符串转义")
    public static String unescapeJson(String string) {
        return StringEscapeUtils.unescapeJson(string);
    }

    @EDI(prefix = "fn", name = "unwrapQuota", desc = "字符串去双引号")
    public static String unwrapQuota(String string) {
        if (string.charAt(0) == '"' && string.charAt(string.length() - 1) == '"') {
            return string.substring(1, string.length() - 1);
        } else if (string.charAt(0) == '"') {
            return string.substring(1);
        } else {
            return string.substring(0, string.length() - 1);
        }
    }

    @EDI(prefix = "fn", name = "unwrapQuotaAndUnescapeJSON", desc = "字符串去双引号并转义")
    public static String unwrapQuotaAndUnescapeJSON(String string) {
        return unescapeJson(unwrapQuota(string));
    }


    @SuppressWarnings("rawtypes")
    private static void doJoin(Object object, StringBuilder stringBuilder) {
        if (object instanceof Map) {
            List list = ((Map<?, ?>) object).keySet().stream().sorted().collect(Collectors.toList());
            for (Object key : list) {
                Object value = ((Map<?, ?>) object).get(key);
                stringBuilder.append(key).append("=");
                doJoin(value, stringBuilder);
            }
        } else if (object instanceof List) {
            for (Object item : (List) object) {
                doJoin(item, stringBuilder);
            }
        } else {
            stringBuilder.append(object);
        }
    }

    @EDI(prefix = "fn", name = "baichuan_sign", desc = "百川签名")
    public static String signBaichuan(String appKey, String appSecret, Long timestamp) {
        return getMd5Hex(appSecret, appKey, getStandardTimestamp(timestamp), appSecret).toUpperCase();
    }

    @EDI(prefix = "fn", name = "standard_timestamp", desc = "获取标准格式时间")
    public static String getStandardTimestamp(Long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(timestamp);
    }

    @EDI(prefix = "fn", name = "json_2_map", desc = "json转map")
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

    @EDI(prefix = "fn", name = "get_product_info", desc = "get_product_info")
    public static HashMap getProductInfo(Map serviceMap) {
        HashMap data = new HashMap();
        String serviceCode = (String) serviceMap.get("serviceCode");
        String productNo = "";
        HashMap<String, String> productAttrs = new HashMap<>();
        Map map = jsonStr2Map((String) serviceMap.get("serviceValue"));
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

    @EDI(prefix = "fn", name = "get_product_type", desc = "get_product_type")
    public static String getProductType(Map serviceMap) {
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

    @EDI(prefix = "fn", name = "get_product_no", desc = "get_product_no")
    public static List getProductNo(String value) {
        List productInfos = new ArrayList();
        HashMap itemMap = new HashMap();
        itemMap.put("productNo", value);
        productInfos.add(itemMap);
        return productInfos;
    }

    @EDI(prefix = "fn", name = "order_resp", desc = "order_resp")
    public static String orderResp(String orderWaybill, String orderWayBillInfo) {
        JSONObject orderWaybillO = JSON.parseObject(orderWaybill);
        JSONObject orderWayBillInfoO = JSON.parseObject(orderWayBillInfo);

        JSONObject ret = new JSONObject();
        ret.put("waybillCode", orderWaybillO.getJSONObject("data").get("waybillNo"));
        if (100 == orderWayBillInfoO.getInteger("statusCode")) {
            JSONArray dataL = orderWayBillInfoO.getJSONArray("data");
            JSONObject data = dataL.getJSONObject(0);
            JSONObject extra = new JSONObject();
            JSONObject extraData = new JSONObject();
            extraData.put("orderMark", getExpressPackageType(data.getString("orderMark")));
            extraData.put("origSortCenter", data.getString("origSortCenter"));
            extraData.put("destSortCenter", data.getString("destSortCenter"));
            extraData.put("origCrossCode", data.getString("origCrossCode"));
            extraData.put("origTabletrolleyCode", data.getString("origTabletrolleyCode"));
            extraData.put("destCrossCode", data.getString("destCrossCode"));
            extraData.put("destTabletrolleyCode", data.getString("destTabletrolleyCode"));
            extraData.put("siteId", data.getInteger("siteId"));
            extraData.put("siteName", data.getString("siteName"));
            extraData.put("road", data.getString("road"));
            extraData.put("airTransport", data.getString("airTransport"));
            extraData.put("coverCode", data.getString("coverCode"));
            extraData.put("distributeCode", data.getString("distributeCode"));
            extra.put("data", extraData);
            extra.put("statusCode", orderWayBillInfoO.getString("statusCode"));
            extra.put("statusMessage", orderWayBillInfoO.getString("statusMessage"));
            ret.put("extraResp", extra);
        }
        return ret.toString();
    }

    private static String getExpressPackageType(String dict) {
        int packageType = (dict.charAt(30) - '0');
        switch (packageType) {
            case 0:
                return "特惠送";
            case 1:
                return "特快送";
            case 9:
                return "生鲜特快";
            case 17:
                return "生鲜特惠";
        }
        return "UNKNOW";
    }


    @EDI(prefix = "fn", name = "get_error_ret", desc = "get_error_ret")
    public static String getErrorResult(String orderRet) {
        initMap();
        JSONObject orderO = JSON.parseObject(orderRet);
        JSONObject orderExt = orderO.getJSONObject("ext");
        JSONObject ret = new JSONObject();
        ret.put("result", "false");
        String byteCode = getErrorCode(orderExt.getString("subCode"));
        ret.put("returnCode", byteCode);
        ret.put("message", getErrorMessage(byteCode));

        return ret.toString();
    }

    private static String getErrorCode(String JDLCode) {
        String byteDanceCode = errorCodeMap.get(JDLCode);
        if (null == byteDanceCode || "".equals(byteDanceCode)) {
            return "10714";
        }
        return errorCodeMap.get(JDLCode);
    }

    private static String getErrorMessage(String code) {
        return errorMessageMap.get(code);
    }

    private static void initMap() {
        // 暂不支持该服务
        errorCodeMap.put("31072", "10703");
        errorCodeMap.put("31062", "10703");
        errorCodeMap.put("31082", "10703");
        errorCodeMap.put("30933", "10703");
        errorCodeMap.put("30462", "10703");
        errorCodeMap.put("20260", "10703");
        errorCodeMap.put("20270", "10703");
        errorCodeMap.put("23122", "10703");
        errorCodeMap.put("23242", "10703");
        errorCodeMap.put("20242", "10703");
        errorCodeMap.put("20401", "10703");
        errorCodeMap.put("20411", "10703");
        errorCodeMap.put("20421", "10703");
        errorCodeMap.put("20422", "10703");
        errorCodeMap.put("20782", "10703");
        errorCodeMap.put("20992", "10703");
        errorCodeMap.put("20142", "10703");
        errorCodeMap.put("20212", "10703");
        errorCodeMap.put("20152", "10703");
        errorCodeMap.put("20151", "10703");
        errorCodeMap.put("20162", "10703");
        errorCodeMap.put("20161", "10703");
        errorCodeMap.put("20172", "10703");
        errorCodeMap.put("20122", "10703");
        errorCodeMap.put("20121", "10703");
        errorCodeMap.put("20182", "10703");
        errorCodeMap.put("20181", "10703");
        errorCodeMap.put("20131", "10703");
        errorCodeMap.put("20192", "10703");
        errorCodeMap.put("20191", "10703");
        errorCodeMap.put("20011", "10703");
        errorCodeMap.put("20012", "10703");
        errorCodeMap.put("20021", "10703");
        errorCodeMap.put("20042", "10703");
        errorCodeMap.put("20041", "10703");
        errorCodeMap.put("20112", "10703");
        errorCodeMap.put("20091", "10703");
        errorCodeMap.put("20092", "10703");
        errorCodeMap.put("20052", "10703");
        errorCodeMap.put("20032", "10703");
        errorCodeMap.put("20031", "10703");
        errorCodeMap.put("23022", "10703");

        // 服务不可达
        errorCodeMap.put("40002", "10704");
        errorCodeMap.put("31491", "10704");

        // 订单ID不能为空
        errorCodeMap.put("20062", "10705");
        errorCodeMap.put("20061", "10705");

        // 发件人信息不完整
        errorCodeMap.put("21172", "10706");
        errorCodeMap.put("21001", "10706");

        // 收件人信息不完整
        errorCodeMap.put("22172", "10707");
        errorCodeMap.put("22001", "10707");

        // 暂不支持该产品
        errorCodeMap.put("31322", "10712");
        errorCodeMap.put("1001", "10712");
        errorCodeMap.put("1002", "10712");
        errorCodeMap.put("1003", "10712");
        errorCodeMap.put("1016", "10712");
        errorCodeMap.put("1071", "10712");
        errorCodeMap.put("1072", "10712");
        errorCodeMap.put("1073", "10712");
        errorCodeMap.put("1074", "10712");
        errorCodeMap.put("1075", "10712");
        errorCodeMap.put("1080", "10712");
        errorCodeMap.put("1081", "10712");
        errorCodeMap.put("1082", "10712");
        errorCodeMap.put("1012", "10712");
        errorCodeMap.put("1050", "10712");

        errorMessageMap.put("10703", "暂不支持该服务");
        errorMessageMap.put("10704", "服务不可达");
        errorMessageMap.put("10705", "订单ID不能为空");
        errorMessageMap.put("10706", "发件人信息不完整");
        errorMessageMap.put("10707", "收件人信息不完整");
        errorMessageMap.put("10711", "电子面单订购关系不存在");
        errorMessageMap.put("10712", "暂不支持该产品");
        errorMessageMap.put("10714", "物流商系统内部错误");
    }

    private static String getMd5Hex(String... args) {
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg);
        }
        return md5Hex(sb.toString());
    }

    private static String assembleExtendedSignContent(Map<String, String> extendSignProperties) {
        if (extendSignProperties == null) {
            return "";
        } else {
            StringBuilder content = new StringBuilder();
            boolean appendNextLine = false;
            for (Map.Entry<String, String> entry : extendSignProperties.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!appendNextLine) {
                    appendNextLine = true;
                } else {
                    content.append("\n");
                }
                content.append(key).append(": ").append(value);
            }
            return content.toString();
        }
    }

    private static String assembleExtendedSignHeaders(Map<String, String> extendSignProperties) {
        if (extendSignProperties == null) {
            return "";
        } else {
            return String.join(" ", extendSignProperties.keySet());
        }
    }

    private static String generateSign(String algorithm, String secret, String content) {
        if ("hmac-sha1".equals(algorithm)) {
            return getEncoder().encodeToString(getInitializedMac(HMAC_SHA_1, secret.getBytes(StandardCharsets.UTF_8))
                    .doFinal(content.getBytes(StandardCharsets.UTF_8)));
        } else if ("md5-salt".equals(algorithm)) {
            return md5Hex(secret + content + secret).toLowerCase(Locale.ROOT);
        } else {
            throw new UnsupportedOperationException();
        }
    }


//    public static void main(String[] args) {
//        String username = "username";
//        String secret = "secret";
//        String algorithm = "hmac-sha1";
//        long milliseconds = System.currentTimeMillis();
//        String content = "{}";
//        System.out.println(authorization(username, secret, algorithm, milliseconds, content));
//        System.out.println(md5(content));
//        System.out.println(GMT(milliseconds));
//    }
}
