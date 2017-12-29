package com.lcgao.personal.home.zhihu;

import java.util.List;

/**
 * Created by lcgao on 2017/12/28.
 */

public class ResultZhihu {
    private String data;
    private List<Storie> stories;
    private List<TopStorie> top_stories;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<Storie> getStories() {
        return stories;
    }

    public void setStories(List<Storie> stories) {
        this.stories = stories;
    }

    public List<TopStorie> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStorie> top_stories) {
        this.top_stories = top_stories;
    }

    @Override
    public String toString() {
        return "ResultZhihu{" +
                "data='" + data + '\'' +
                ", stories=" + stories.toString() +
                ", top_stories=" + top_stories.toString() +
                '}';
    }
}
