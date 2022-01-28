package com.github.liufarui.demo.JacksonT;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liufarui
 * @Description:
 * @date 2021/2/19 5:01 下午
 */
public class JacksonT {
    @Test
    public void test() {
        ObjectMapper mapper = new ObjectMapper();

        String jsonString = "{\"name\":\"Mahesh\", \"age\":21}";
        try {
            Person person = mapper.readValue(jsonString, Person.class);
            System.out.println(person);

            String jsonStr = mapper.writeValueAsString(person);
            System.out.println(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("aaa", "bbb");
        map.put("aaa1", "bbb1");
        String jsonStr = mapper.writeValueAsString(map);
        System.out.println(jsonStr);
    }

    @Test
    public void test2() {
        System.out.println("asdasds" + null + "asdasda111");
    }
}
