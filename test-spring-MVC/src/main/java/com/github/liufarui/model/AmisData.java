package com.github.liufarui.model;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * @author lfr
 * @date 2020/11/30 下午6:05
 */
public class AmisData {
    String title;
    Date date;

    public AmisData(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
