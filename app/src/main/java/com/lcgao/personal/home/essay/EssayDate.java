package com.lcgao.personal.home.essay;

/**
 * Created by lcgao on 2018/1/29.
 */

public class EssayDate {
    private String curr;
    private String prev;
    private String next;

    public EssayDate() {
    }

    public EssayDate(String curr, String prev, String next) {
        this.curr = curr;
        this.prev = prev;
        this.next = next;
    }

    public String getCurr() {
        return curr;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
