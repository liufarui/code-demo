package com.github.liufarui.controller;

import com.github.liufarui.model.AmisResult;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lfr
 * @date 2020/11/30 下午4:50
 */
@Controller
@RequestMapping(value = "/amis")
@CrossOrigin(origins = "*")
public class AmisController {
    @RequestMapping(method = RequestMethod.GET, value = "/menu")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public String amis(HttpServletRequest request, HttpServletResponse response) {
        return "{\"$schema\":\"https://houtai.baidu.com/v2/schemas/page.json\",\"title\":\"应用管理系统\",\"toolbar\":[{\"type\":\"button\",\"actionType\":\"dialog\",\"label\":\"新增\",\"primary\":true,\"dialog\":{\"title\":\"新增\",\"body\":{\"type\":\"form\",\"name\":\"sample-edit-form\",\"api\":\"post:http://localhost:8088/amis\",\"controls\":[{\"type\":\"text\",\"name\":\"name\",\"label\":\"name\",\"required\":true},{\"type\":\"divider\"},{\"type\":\"text\",\"name\":\"sex\",\"label\":\"sex\",\"required\":true}]}}}],\"body\":{\"type\":\"crud\",\"draggable\":true,\"api\":\"http://localhost:8088/amis\",\"filter\":{\"title\":\"表属性搜索\",\"submitText\":\"\",\"controls\":[{\"type\":\"text\",\"name\":\"keywords\",\"placeholder\":\"通过属性名搜索\",\"addOn\":{\"label\":\"搜索\",\"type\":\"submit\"}},{\"type\":\"plain\",\"text\":\"这里的表单项可以配置多个\"}]},\"bulkActions\":[{\"label\":\"批量删除\",\"actionType\":\"ajax\",\"api\":\"delete:http://localhost:8088/amis/batch/delete/${ids}\",\"confirmText\":\"确定要批量删除?\"}],\"quickSaveApi\":\"http://localhost:8088/amis/update\",\"columns\":[{\"name\":\"id\",\"label\":\"id\",\"width\":20,\"sortable\":true,\"type\":\"text\",\"toggled\":true},{\"name\":\"name\",\"label\":\"name\",\"sortable\":true,\"searchable\":true,\"type\":\"text\",\"toggled\":true,\"quickEdit\":true},{\"name\":\"sex\",\"label\":\"sex\",\"sortable\":true,\"type\":\"text\",\"toggled\":true,\"quickEdit\":true},{\"type\":\"operation\",\"label\":\"操作\",\"width\":130,\"buttons\":[{\"type\":\"button\",\"icon\":\"fa fa-eye\",\"actionType\":\"dialog\",\"dialog\":{\"title\":\"查看\",\"body\":{\"type\":\"form\",\"controls\":[{\"type\":\"static\",\"name\":\"name\",\"label\":\"name\"},{\"type\":\"static\",\"name\":\"sex\",\"label\":\"sex\"},{\"type\":\"html\",\"html\":\"<p>添加其他 <span>Html 片段</span> 需要支持变量替换（todo）.</p>\"}]}}},{\"type\":\"button\",\"icon\":\"fa fa-pencil\",\"actionType\":\"dialog\",\"dialog\":{\"title\":\"编辑\",\"body\":{\"type\":\"form\",\"name\":\"sample-edit-form\",\"api\":\"http://localhost:8088/amis/$id\",\"controls\":[{\"type\":\"text\",\"name\":\"name\",\"label\":\"name\",\"required\":true},{\"type\":\"divider\"},{\"type\":\"text\",\"name\":\"sex\",\"label\":\"sex\",\"required\":true},{\"type\":\"divider\"}]}}},{\"type\":\"button\",\"icon\":\"fa fa-times text-danger\",\"actionType\":\"ajax\",\"confirmText\":\"您确认要删除?\",\"api\":\"delete:http://localhost:8088/amis/delete/$id\"}],\"toggled\":true}]},\"type\":\"page\"}";
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public String getData(HttpServletRequest request, HttpServletResponse response,
                          String keywords) {
        return "{\"status\":0,\"msg\":\"\",\"data\":[{\"id\":\"1\",\"name\":\"Peter\",\"sex\":\"男\"},{\"id\":\"2\",\"name\":\"Lily\",\"sex\":\"女\"},{\"id\":\"3\",\"name\":\"Sherry\",\"sex\":\"女\"}]}";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public String insertData(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody String requestParam) {
        Gson gson = new Gson();
        AmisResult result = new AmisResult(0, "", null);
        return gson.toJson(result);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public String deleteData(HttpServletRequest request, HttpServletResponse response, @PathVariable() String id) {
        Gson gson = new Gson();
        AmisResult result = new AmisResult(0, id + "删除成功", null);
        return gson.toJson(result);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/batch/delete/{ids}")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public String deleteDatas(HttpServletRequest request, HttpServletResponse response, @PathVariable() String ids) {
        Gson gson = new Gson();
        AmisResult result = new AmisResult(0, ids+"删除成功", null);
        return gson.toJson(result);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public String updateData(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody String requestParam) {
        Gson gson = new Gson();
        AmisResult result = new AmisResult(0, "", null);
        return gson.toJson(result);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/aside")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public String getData1(HttpServletRequest request, HttpServletResponse response,
                           String perPage, String page, String app, String keywords) {
        return "{\"status\":0,\"msg\":\"\",\"data\":{\"options\":[{\"label\":\"App1\",\"value\":\"App1\",\"children\":[{\"label\":\"Table1\",\"value\":\"Table1\"},{\"label\":\"Table2\",\"value\":\"Table2\"}]},{\"label\":\"App2\",\"value\":\"App2\",\"children\":[{\"label\":\"Table3\",\"value\":\"Table3\"},{\"label\":\"Table4\",\"value\":\"Table4\"}]},{\"label\":\"App3\",\"value\":\"App3\",\"children\":[{\"label\":\"Table5\",\"value\":\"Table5\"},{\"label\":\"Table6\",\"value\":\"Table6\"}]}]}}";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/table")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public String table(HttpServletRequest request, HttpServletResponse response,
                        String perPage, String page, String app, String keywords) {
        return "{\"status\":0,\"msg\":\"\",\"data\":[{\"id\":\"1\",\"name\":\"Peter\",\"sex\":\"男\"},{\"id\":\"2\",\"name\":\"Lily\",\"sex\":\"女\"},{\"id\":\"3\",\"name\":\"Sherry\",\"sex\":\"女\"}]}";
    }
}
