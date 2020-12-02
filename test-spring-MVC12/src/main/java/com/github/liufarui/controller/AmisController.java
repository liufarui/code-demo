package com.github.liufarui.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.liufarui.model.AmisResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lfr
 * @date 2020/11/30 下午4:50
 */
@Controller
@RequestMapping(value = "amis")
public class AmisController {
    @RequestMapping(method= RequestMethod.GET)
    @ResponseBody
    public String getData(HttpServletRequest request, HttpServletResponse response) {
//        Gson gson = new Gson();

        AmisResult result = new AmisResult(0, "ok","");

        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(result);
//        return gson.toJson(result);
        return jsonObject.toJSONString();
    }

}
