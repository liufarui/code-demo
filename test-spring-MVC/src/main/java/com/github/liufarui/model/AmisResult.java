package com.github.liufarui.model;

/**
 * @author lfr
 * @date 2020/11/30 下午5:04
 */
public class AmisResult {
    int status;
    String msg;
    AmisData data;

    public AmisResult() {
    }

    public AmisResult(int status, String msg, AmisData data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
