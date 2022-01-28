package com.github.liufarui.demo.Http;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Hex;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.commons.codec.digest.DigestUtils.sha256;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

/**
 * @Package: com.github.liufarui.demo.Http
 * @ClassName: ByteBody
 * @Description:
 * @Author: liufarui
 * @CreateDate: 2021/5/17 6:10 下午
 * @Copyright: Copyright (c)2021 JDL.CN All Right Reserved
 * @Since: JDK 1.8
 * @Version: V1.0
 */
public class ByteBody {
    private static final String ACCESS_KEY = "8345nek072H32";
    private static final String ACCESS_SECRET = "763uyiY&30K%2";

    public static String check(String jsonStr) throws IOException, NoSuchAlgorithmException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.readValue(jsonStr, Map.class);
        return join(map, ACCESS_SECRET);
    }

    public static String join(Object object, String secret) {
        Map map = null;
        Map commonMap = null;

        if (object instanceof Map) {
            map = (Map) object;
            Object common = ((Map<?, ?>) object).get("common");
            if (common instanceof Map) {
                commonMap = (Map) common;
            }
        }
        if (commonMap != null) {
            commonMap.remove("signature");
            commonMap.put("accessKey", ACCESS_KEY);
        }
        StringBuilder stringBuilder = new StringBuilder();
        doJoin(object, stringBuilder);

        String str = stringBuilder.append(secret).toString();

        commonMap.put("signature", Hex.encodeHexString(sha256(str)));

        System.out.println(Hex.encodeHexString(sha256(str)));
        return map.toString();
    }

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
            if (object instanceof Double) {
                stringBuilder.append(object);
            } else {
                stringBuilder.append(object);
            }

        }
    }
}
