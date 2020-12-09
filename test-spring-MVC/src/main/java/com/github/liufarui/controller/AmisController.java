package com.github.liufarui.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.liufarui.model.AmisResult;
import com.google.gson.Gson;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

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
    public String amis(HttpServletRequest request, HttpServletResponse response) throws IOException {

//        String aaa =geti();
//        return aaa;

        return "{\"type\":\"page\",\"title\":\"HelloWorld\",\"body\":[{\"type\":\"crud\",\"api\":\"\",\"columns\":[{\"name\":\"id\",\"label\":\"ID\",\"type\":\"text\",\"placeholder\":\"-\",\"className\":\"word-break \",\"fixed\":\"left\"},{\"name\":\"name\",\"label\":\"名称\",\"type\":\"text\",\"fixed\":\"left\",\"quickEdit\":{\"mode\":\"inline\",\"saveImmediately\":{\"api\":{\"method\":\"post\",\"url\":\"http://localhost/update\",\"dataType\":\"json\"}}}},{\"type\":\"text\",\"label\":\"性别\",\"name\":\"sex\",\"className\":\"\",\"fixed\":\"left\"},{\"type\":\"text\",\"label\":\"年龄\",\"name\":\"age\",\"fixed\":\"left\",\"quickEdit\":false},{\"type\":\"text\",\"label\":\"邮箱\",\"name\":\"mail\",\"className\":\"word-break \",\"fixed\":\"left\",\"groupName\":\"\",\"placeholder\":\"-\",\"quickEdit\":{\"mode\":\"inline\",\"saveImmediately\":{\"api\":{\"method\":\"post\",\"url\":\"http://localhost/update\",\"data\":null,\"dataType\":\"json\"}}}},{\"type\":\"operation\",\"label\":\"管理\",\"buttons\":[{\"type\":\"dropdown-button\",\"label\":\"操作\",\"buttons\":[{\"type\":\"button\",\"label\":\"查看\",\"actionType\":\"dialog\",\"dialog\":{\"title\":\"系统提示\",\"body\":\"对你点击了\"}},{\"type\":\"button\",\"label\":\"修改\",\"actionType\":\"dialog\",\"dialog\":{\"title\":\"系统提示\",\"body\":\"对你点击了\"}},{\"type\":\"button\",\"label\":\"删除\",\"actionType\":\"dialog\",\"dialog\":{\"title\":\"系统提示\",\"body\":\"对你点击了\"}}],\"closeOnClick\":false,\"size\":\"md\",\"level\":\"info\"}],\"placeholder\":\"-\"}]}],\"messages\":{},\"subTitle\":\"json\",\"toolbar\":[{\"type\":\"submit\",\"label\":\"新增\",\"actionType\":\"dialog\",\"dialog\":{\"type\":\"dialog\",\"title\":\"新增\",\"body\":[{\"type\":\"form\",\"title\":\"表单\",\"controls\":[{\"label\":\"姓名\",\"type\":\"text\",\"name\":\"name\",\"required\":true},{\"type\":\"text\",\"label\":\"性别\",\"name\":\"sex\",\"required\":true,\"hint\":\"只能输入男/女\"},{\"type\":\"number\",\"label\":\"年龄\",\"name\":\"age\",\"required\":true},{\"type\":\"email\",\"label\":\"邮箱\",\"name\":\"email\",\"source\":\"\"}]}],\"closeOnEsc\":true,\"showCloseButton\":true,\"footer\":[{\"type\":\"button-group\",\"buttons\":[{\"type\":\"button\",\"label\":\"按钮1\",\"actionType\":\"dialog\",\"dialog\":{\"title\":\"系统提示\",\"body\":\"对你点击了\"}},{\"type\":\"button\",\"label\":\"按钮2\",\"actionType\":\"dialog\",\"dialog\":{\"title\":\"系统提示\",\"body\":\"对你点击了\"}}]},{\"type\":\"button\",\"label\":\"按钮\",\"actionType\":\"dialog\",\"dialog\":{\"title\":\"系统提示\",\"body\":\"对你点击了\"}},{\"type\":\"tabs\",\"tabs\":[{\"title\":\"选项卡1\",\"body\":\"内容1\"},{\"title\":\"选项卡2\",\"body\":\"内容2\"}]},{\"type\":\"button-group\",\"buttons\":[{\"type\":\"button\",\"label\":\"按钮1\",\"actionType\":\"dialog\",\"dialog\":{\"title\":\"系统提示\",\"body\":\"对你点击了\"}},{\"type\":\"button\",\"label\":\"按钮2\",\"actionType\":\"dialog\",\"dialog\":{\"title\":\"系统提示\",\"body\":\"对你点击了\"}}]},{\"type\":\"button\",\"label\":\"按钮\",\"actionType\":\"dialog\",\"dialog\":{\"title\":\"系统提示\",\"body\":\"对你点击了\"}},{\"type\":\"submit\",\"label\":\"提交\",\"level\":\"primary\"}]},\"size\":\"md\",\"block\":false,\"api\":\"\"}]}";

//        return "{\"data\":{\"id\":1,\"name\":\"Issue CRUD\",\"schema\":\"{\\\"$schema\\\":\\\"https://houtai.baidu.com/v2/schemas/page.json\\\",\\\"title\\\":\\\"Issue CRUD\\\",\\\"toolbar\\\":[{\\\"type\\\":\\\"button\\\",\\\"actionType\\\":\\\"dialog\\\",\\\"label\\\":\\\"新增\\\",\\\"primary\\\":true,\\\"dialog\\\":{\\\"title\\\":\\\"新增\\\",\\\"body\\\":{\\\"type\\\":\\\"form\\\",\\\"name\\\":\\\"sample-edit-form\\\",\\\"api\\\":\\\"post:http://localhost:8088/amis/\\\",\\\"controls\\\":[{\\\"type\\\":\\\"text\\\",\\\"name\\\":\\\"标题\\\",\\\"label\\\":\\\"标题\\\"},{\\\"type\\\":\\\"text\\\",\\\"name\\\":\\\"紧急程度\\\",\\\"label\\\":\\\"紧急程度\\\"},{\\\"type\\\":\\\"text\\\",\\\"name\\\":\\\"需求描述\\\",\\\"label\\\":\\\"需求描述\\\"},{\\\"type\\\":\\\"text\\\",\\\"name\\\":\\\"需求类型\\\",\\\"label\\\":\\\"需求类型\\\"}]}}}],\\\"body\\\":{\\\"type\\\":\\\"crud\\\",\\\"draggable\\\":true,\\\"api\\\":\\\"http://localhost:8000/api/v0.1/data/Model/3/Row\\\",\\\"filter\\\":{\\\"title\\\":\\\"表属性搜索\\\",\\\"submitText\\\":\\\"\\\",\\\"controls\\\":[{\\\"type\\\":\\\"text\\\",\\\"name\\\":\\\"keywords\\\",\\\"placeholder\\\":\\\"通过属性名搜索\\\",\\\"addOn\\\":{\\\"label\\\":\\\"搜索\\\",\\\"type\\\":\\\"submit\\\"}},{\\\"type\\\":\\\"plain\\\",\\\"text\\\":\\\"这里的表单项可以配置多个\\\"}]},\\\"bulkActions\\\":[{\\\"label\\\":\\\"批量删除\\\",\\\"actionType\\\":\\\"ajax\\\",\\\"api\\\":\\\"delete:http://localhost:8088/amis/batch/delete/${ids}\\\",\\\"confirmText\\\":\\\"确定要批量删除?\\\"}],\\\"quickSaveApi\\\":\\\"http://localhost:8088/amis/update\\\",\\\"columns\\\":[{\\\"name\\\":\\\"标题\\\",\\\"label\\\":\\\"标题\\\",\\\"width\\\":20,\\\"sortable\\\":true,\\\"type\\\":\\\"text\\\",\\\"toggled\\\":true},{\\\"name\\\":\\\"紧急程度\\\",\\\"label\\\":\\\"紧急程度\\\",\\\"width\\\":20,\\\"sortable\\\":true,\\\"type\\\":\\\"text\\\",\\\"toggled\\\":true},{\\\"name\\\":\\\"需求描述\\\",\\\"label\\\":\\\"需求描述\\\",\\\"width\\\":20,\\\"sortable\\\":true,\\\"type\\\":\\\"text\\\",\\\"toggled\\\":true},{\\\"name\\\":\\\"需求类型\\\",\\\"label\\\":\\\"需求类型\\\",\\\"width\\\":20,\\\"sortable\\\":true,\\\"type\\\":\\\"text\\\",\\\"toggled\\\":true},{\\\"type\\\":\\\"operation\\\",\\\"label\\\":\\\"操作\\\",\\\"width\\\":130,\\\"buttons\\\":[{\\\"type\\\":\\\"button\\\",\\\"icon\\\":\\\"fa fa-eye\\\",\\\"actionType\\\":\\\"dialog\\\",\\\"dialog\\\":{\\\"title\\\":\\\"查看\\\",\\\"body\\\":{\\\"type\\\":\\\"form\\\",\\\"controls\\\":[{\\\"type\\\":\\\"static\\\",\\\"name\\\":\\\"name\\\",\\\"label\\\":\\\"name\\\"},{\\\"type\\\":\\\"divider\\\"},{\\\"type\\\":\\\"static\\\",\\\"name\\\":\\\"type\\\",\\\"label\\\":\\\"type\\\"},{\\\"type\\\":\\\"text\\\",\\\"name\\\":\\\"indexed\\\",\\\"label\\\":\\\"indexed\\\"},{\\\"type\\\":\\\"divider\\\"},{\\\"type\\\":\\\"divider\\\"},{\\\"type\\\":\\\"html\\\",\\\"html\\\":\\\"<p>添加其他 <span>Html 片段</span> 需要支持变量替换（todo）.</p>\\\"}]}}},{\\\"type\\\":\\\"button\\\",\\\"icon\\\":\\\"fa fa-pencil\\\",\\\"actionType\\\":\\\"dialog\\\",\\\"dialog\\\":{\\\"title\\\":\\\"编辑\\\",\\\"body\\\":{\\\"type\\\":\\\"form\\\",\\\"name\\\":\\\"sample-edit-form\\\",\\\"api\\\":\\\"http://localhost:8088/amis/$id\\\",\\\"controls\\\":[{\\\"type\\\":\\\"text\\\",\\\"name\\\":\\\"name\\\",\\\"label\\\":\\\"name\\\",\\\"required\\\":true},{\\\"type\\\":\\\"divider\\\"},{\\\"type\\\":\\\"text\\\",\\\"name\\\":\\\"type\\\",\\\"label\\\":\\\"type\\\",\\\"required\\\":true},{\\\"type\\\":\\\"divider\\\"}]}}},{\\\"type\\\":\\\"button\\\",\\\"icon\\\":\\\"fa fa-times text-danger\\\",\\\"actionType\\\":\\\"ajax\\\",\\\"confirmText\\\":\\\"您确认要删除?\\\",\\\"api\\\":\\\"delete:http://localhost:8088/amis/delete/$id\\\"}],\\\"toggled\\\":true}]},\\\"type\\\":\\\"page\\\"}\"}}";
//        return "{\"$schema\":\"https://houtai.baidu.com/v2/schemas/page.json\",\"title\":\"应用管理系统\",\"toolbar\":[{\"type\":\"button\",\"actionType\":\"dialog\",\"label\":\"新增\",\"primary\":true,\"dialog\":{\"title\":\"新增\",\"body\":{\"type\":\"form\",\"name\":\"sample-edit-form\",\"api\":\"post:http://localhost:8088/amis\",\"controls\":[{\"type\":\"text\",\"name\":\"name\",\"label\":\"name\",\"required\":true},{\"type\":\"divider\"},{\"type\":\"text\",\"name\":\"sex\",\"label\":\"sex\",\"required\":true}]}}}],\"body\":{\"type\":\"crud\",\"draggable\":true,\"api\":\"http://localhost:8088/amis\",\"filter\":{\"title\":\"表属性搜索\",\"submitText\":\"\",\"controls\":[{\"type\":\"text\",\"name\":\"keywords\",\"placeholder\":\"通过属性名搜索\",\"addOn\":{\"label\":\"搜索\",\"type\":\"submit\"}},{\"type\":\"plain\",\"text\":\"这里的表单项可以配置多个\"}]},\"bulkActions\":[{\"label\":\"批量删除\",\"actionType\":\"ajax\",\"api\":\"delete:http://localhost:8088/amis/batch/delete/${ids}\",\"confirmText\":\"确定要批量删除?\"}],\"quickSaveApi\":\"http://localhost:8088/amis/update\",\"columns\":[{\"name\":\"id\",\"label\":\"id\",\"width\":20,\"sortable\":true,\"type\":\"text\",\"toggled\":true},{\"name\":\"name\",\"label\":\"name\",\"sortable\":true,\"searchable\":true,\"type\":\"text\",\"toggled\":true,\"quickEdit\":true},{\"name\":\"sex\",\"label\":\"sex\",\"sortable\":true,\"type\":\"text\",\"toggled\":true,\"quickEdit\":true},{\"type\":\"operation\",\"label\":\"操作\",\"width\":130,\"buttons\":[{\"type\":\"button\",\"icon\":\"fa fa-eye\",\"actionType\":\"dialog\",\"dialog\":{\"title\":\"查看\",\"body\":{\"type\":\"form\",\"controls\":[{\"type\":\"static\",\"name\":\"name\",\"label\":\"name\"},{\"type\":\"static\",\"name\":\"sex\",\"label\":\"sex\"},{\"type\":\"html\",\"html\":\"<p>添加其他 <span>Html 片段</span> 需要支持变量替换（todo）.</p>\"}]}}},{\"type\":\"button\",\"icon\":\"fa fa-pencil\",\"actionType\":\"dialog\",\"dialog\":{\"title\":\"编辑\",\"body\":{\"type\":\"form\",\"name\":\"sample-edit-form\",\"api\":\"http://localhost:8088/amis/$id\",\"controls\":[{\"type\":\"text\",\"name\":\"name\",\"label\":\"name\",\"required\":true},{\"type\":\"divider\"},{\"type\":\"text\",\"name\":\"sex\",\"label\":\"sex\",\"required\":true},{\"type\":\"divider\"}]}}},{\"type\":\"button\",\"icon\":\"fa fa-times text-danger\",\"actionType\":\"ajax\",\"confirmText\":\"您确认要删除?\",\"api\":\"delete:http://localhost:8088/amis/delete/$id\"}],\"toggled\":true}]},\"type\":\"page\"}";
    }

    @Test
    public void test () throws IOException {
        String aaa  = geti();
        int bb =0;
    }

    private String geti() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("/Users/lfr/Documents/aaa")));

        StringBuffer sb = new StringBuffer();

        for (String line = br.readLine(); line != null; line = br.readLine()) {
            sb.append(line);
        }
        br.close();

        return sb.toString();
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
        JSONObject obj = JSON.parseObject(requestParam);

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
