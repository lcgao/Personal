package com.lcgao.personal.favourite.news;

import java.util.List;

/**
 * Created by lcgao on 2018/1/27.
 */

public class NewsData {
    private String content;
    private String code;

    public NewsData() {
    }

    public NewsData(String content, String code) {
        this.content = content;
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "NewsData{" +
                "content=" + content +
                ", code='" + code + '\'' +
                '}';
    }
}
