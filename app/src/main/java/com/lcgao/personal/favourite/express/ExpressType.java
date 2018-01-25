package com.lcgao.personal.favourite.express;

import me.yokeyword.indexablerv.IndexableEntity;

/**
 * Created by lcgao on 2018/1/24.
 */

public class ExpressType implements IndexableEntity {

    private String expressName;
    private String expressType;

    public ExpressType() {
    }

    public ExpressType(String expressName, String expressType) {
        this.expressName = expressName;
        this.expressType = expressType;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressType() {
        return expressType;
    }

    public void setExpressType(String expressType) {
        this.expressType = expressType;
    }

    @Override
    public String getFieldIndexBy() {
        return expressName;
    }

    @Override
    public void setFieldIndexBy(String indexField) {
        this.expressName = indexField;
    }

    @Override
    public void setFieldPinyinIndexBy(String pinyin) {

    }

    @Override
    public String toString() {
        return "ExpressType{" +
                "expressName='" + expressName + '\'' +
                ", expressType='" + expressType + '\'' +
                '}';
    }
}
