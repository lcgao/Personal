package com.lcgao.personal.home.essay;

import java.util.List;

/**
 * Created by lcgao on 2018/1/29.
 */

public class EssayEntity {
    private EssayDate date;
    private String author;
    private String title;
    private String digest;
    private String content;
    private long wc;

    public EssayEntity() {
    }

    public EssayEntity(EssayDate date, String author, String title, String digest, String content, long wc) {
        this.date = date;
        this.author = author;
        this.title = title;
        this.digest = digest;
        this.content = content;
        this.wc = wc;
    }

    public EssayDate getDate() {
        return date;
    }

    public void setDate(EssayDate date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getWc() {
        return wc;
    }

    public void setWc(long wc) {
        this.wc = wc;
    }

    @Override
    public String toString() {
        return "EssayEntity{" +
                "date=" + date +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", digest='" + digest + '\'' +
                ", content='" + content + '\'' +
                ", wc=" + wc +
                '}';
    }
}
