package com.lcgao.personal.favourite.news;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lcgao on 2018/1/27.
 */

public class NewsContent {
    private String title;
    private String article_url;
    private boolean has_image;
    private List<NewsImages> image_list;
    private String keywords;
    private String read_count;
    private String source;
    @SerializedName("abstract")
    private String _abstract;
    private long publish_time;

    public NewsContent() {
    }

    public NewsContent(String title, String article_url, boolean has_image, List<NewsImages> image_list, String keywords, String read_count, String source, String _abstract, long publish_time) {
        this.title = title;
        this.article_url = article_url;
        this.has_image = has_image;
        this.image_list = image_list;
        this.keywords = keywords;
        this.read_count = read_count;
        this.source = source;
        this._abstract = _abstract;
        this.publish_time = publish_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }

    public boolean isHas_image() {
        return has_image;
    }

    public void setHas_image(boolean has_image) {
        this.has_image = has_image;
    }

    public List<NewsImages> getImage_list() {
        return image_list;
    }

    public void setImage_list(List<NewsImages> image_list) {
        this.image_list = image_list;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getRead_count() {
        return read_count;
    }

    public void setRead_count(String read_count) {
        this.read_count = read_count;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String get_abstract() {
        return _abstract;
    }

    public void set_abstract(String _abstract) {
        this._abstract = _abstract;
    }

    @Override
    public String toString() {
        return "NewsContent{" +
                "title='" + title + '\'' +
                ", article_url='" + article_url + '\'' +
                ", has_image=" + has_image +
                ", image_list=" + image_list +
                ", keywords='" + keywords + '\'' +
                ", read_count='" + read_count + '\'' +
                ", source='" + source + '\'' +
                ", _abstract='" + _abstract + '\'' +
                ", publish_time=" + publish_time +
                '}';
    }

    public long getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(long publish_time) {
        this.publish_time = publish_time;
    }

}
