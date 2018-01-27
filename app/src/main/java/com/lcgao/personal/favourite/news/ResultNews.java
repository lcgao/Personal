package com.lcgao.personal.favourite.news;

import java.util.List;

/**
 * Created by lcgao on 2018/1/27.
 */

public class ResultNews {
    private String message;
    private List<NewsData> data;
    private long total_number;
    private boolean has_more;
    private String post_content_hint;
    private boolean has_more_to_refresh;

    public ResultNews() {
    }

    public ResultNews(String message, List<NewsData> data, long total_number, boolean has_more, String post_content_hint, boolean has_more_to_refresh) {
        this.message = message;
        this.data = data;
        this.total_number = total_number;
        this.has_more = has_more;
        this.post_content_hint = post_content_hint;
        this.has_more_to_refresh = has_more_to_refresh;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<NewsData> getData() {
        return data;
    }

    public void setData(List<NewsData> data) {
        this.data = data;
    }

    public long getTotal_number() {
        return total_number;
    }

    public void setTotal_number(long total_number) {
        this.total_number = total_number;
    }

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public String getPost_content_hint() {
        return post_content_hint;
    }

    public void setPost_content_hint(String post_content_hint) {
        this.post_content_hint = post_content_hint;
    }

    public boolean isHas_more_to_refresh() {
        return has_more_to_refresh;
    }

    public void setHas_more_to_refresh(boolean has_more_to_refresh) {
        this.has_more_to_refresh = has_more_to_refresh;
    }

    @Override
    public String toString() {
        return "ResultNews{" +
                "message='" + message + '\'' +
                ", data=" + data +
                ", total_number=" + total_number +
                ", has_more=" + has_more +
                ", post_content_hint='" + post_content_hint + '\'' +
                ", has_more_to_refresh=" + has_more_to_refresh +
                '}';
    }
}
