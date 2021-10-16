package com.github.liufarui.freeMarker.service;

import com.github.liufarui.freeMarker.model.User;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liufarui
 * @date 2021/10/14 18:31
 */
public class DemoService {
    public static void main(String[] args) {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
            cfg.setDirectoryForTemplateLoading(new File("src/ftl"));

            Template template = cfg.getTemplate("basic.ftl");

            User user = new User();
            user.setName("Ticket");

            Map<String, Object> root = new HashMap<>(5);
            root.put("user", user);

            Writer writer = new FileWriter("src/ftl/basic.html");

            template.process(root, writer);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
