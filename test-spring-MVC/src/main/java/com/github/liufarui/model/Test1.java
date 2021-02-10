package com.github.liufarui.model;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.tools.javac.util.StringUtils;
import org.junit.Test;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author liufarui
 * @Description:
 * @date 2021/2/5 10:38 下午
 */
public class Test1 {

    @org.junit.Test
    public void test() {
        System.out.println('A' - '0');
//        System.out.println(orderResp("{\"code\":\"1\",\"data\":{\"orderNo\":\"TEBD0010000529868\",\"presortResult\":{},\"waybillNo\":\"JDV000504951092\"},\"ext\":{},\"success\":true}", "{\"data\":[{\"agingName\":\"隔日达\",\"collectionAddress\":\"\",\"collectionMoney\":0.0,\"coverCode\":\"\",\"customerCode\":\"010K0417\",\"customerName\":\"010K0417\",\"deliveryId\":\"JDV000504951092\",\"destCrossCode\":\"2002\",\"destSortCenter\":\"北京马驹桥分拣中心\",\"destTabletrolleyCode\":\"106\",\"distributeCode\":\"888\",\"goods\":\"Iphone 11\",\"goodsType\":\"\",\"guaranteeValue\":1,\"orderId\":\"5482820004\",\"orderMark\":\"30001000010000030000000020000000000000000002040000002000010000000000000000000010000100000010100000000000000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000\",\"origCrossCode\":\"2002\",\"origSortCenter\":\"北京马驹桥分拣中心\",\"origTabletrolleyCode\":\"106\",\"packageCode\":\"JDV000504951092-1-2-\",\"packageCount\":2,\"packageNum\":1,\"qrcodeUrl\":\"https://mp.weixin.qq.com/a/~bdyFWnK5nG7Ly5w-xXbAYg\",\"receiveAddress\":\"上海市浦东新区航头镇\",\"receiveCity\":\"浦东新区\",\"receiveCounty\":\"航头镇\",\"receiveMobile\":\"+86 15966668888\",\"receiveName\":\"receiverTest\",\"receiveProvince\":\"上海\",\"receiveTel\":\"15966668888\",\"remark\":\"备注哈\",\"road\":\"2\",\"sendCity\":\"大兴区\",\"senderAddress\":\"北京大兴区亦庄经济开发区朝林广场A座\",\"senderCityName\":\"大兴区\",\"senderCountyName\":\"亦庄经济开发区\",\"senderMobile\":\"+86 13282160692\",\"senderName\":\"字节跳动\",\"senderProvinceName\":\"北京\",\"senderTel\":\"13282160692\",\"siteId\":39,\"siteName\":\"石景山营业部\",\"weight\":1.0},{\"agingName\":\"隔日达\",\"collectionAddress\":\"\",\"collectionMoney\":0.0,\"coverCode\":\"\",\"customerCode\":\"010K0417\",\"customerName\":\"010K0417\",\"deliveryId\":\"JDV000504951092\",\"destCrossCode\":\"2002\",\"destSortCenter\":\"北京马驹桥分拣中心\",\"destTabletrolleyCode\":\"106\",\"distributeCode\":\"888\",\"goods\":\"Iphone 11\",\"goodsType\":\"\",\"guaranteeValue\":1,\"orderId\":\"5482820004\",\"orderMark\":\"30001000010000030000000020000000000000000002040000002000010000000000000000000010000100000010100000000000000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000\",\"origCrossCode\":\"2002\",\"origSortCenter\":\"北京马驹桥分拣中心\",\"origTabletrolleyCode\":\"106\",\"packageCode\":\"JDV000504951092-2-2-\",\"packageCount\":2,\"packageNum\":2,\"qrcodeUrl\":\"https://mp.weixin.qq.com/a/~bdyFWnK5nG7Ly5w-xXbAYg\",\"receiveAddress\":\"上海市浦东新区航头镇\",\"receiveCity\":\"浦东新区\",\"receiveCounty\":\"航头镇\",\"receiveMobile\":\"+86 15966668888\",\"receiveName\":\"receiverTest\",\"receiveProvince\":\"上海\",\"receiveTel\":\"15966668888\",\"remark\":\"备注哈\",\"road\":\"2\",\"sendCity\":\"大兴区\",\"senderAddress\":\"北京大兴区亦庄经济开发区朝林广场A座\",\"senderCityName\":\"大兴区\",\"senderCountyName\":\"亦庄经济开发区\",\"senderMobile\":\"+86 13282160692\",\"senderName\":\"字节跳动\",\"senderProvinceName\":\"北京\",\"senderTel\":\"13282160692\",\"siteId\":39,\"siteName\":\"石景山营业部\",\"weight\":1.0}],\"statusCode\":100,\"statusMessage\":\"调用成功\"}"));
        System.out.println(getErrorResult("{\"code\":\"2\",\"data\":{\"orderNo\":\"TEBD0010000529890\",\"presortResult\":{},\"waybillNo\":\"JDV000504951092\"},\"ext\":{\"31072\":\"Errorsad\"},\"success\":true}"));
    }

    static HashMap<String, String> errorCodeMap = new HashMap<>();
    static HashMap<String, String> errorMessageMap = new HashMap<>();
//    Test1() {
//        errorCodeMap.put("31072", "10703");
//        errorMessageMap.put("10703", "暂不支持该服务");
//    }

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
    }


}
