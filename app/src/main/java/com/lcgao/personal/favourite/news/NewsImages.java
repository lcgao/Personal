package com.lcgao.personal.favourite.news;

/**
 * Created by lcgao on 2018/1/27.
 */

public class NewsImages {
    private long height;
    private String uri;
    private String url;

    public NewsImages() {
    }

    public NewsImages(long height, String uri, String url) {
        this.height = height;
        this.uri = uri;
        this.url = url;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NewsImages{" +
                "height=" + height +
                ", uri='" + uri + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
