package com.lcgao.personal.home.essay;

/**
 * Created by lcgao on 2018/1/29.
 */

public class EssayData {
    private EssayEntity data;

    public EssayData() {
    }

    public EssayData(EssayEntity data) {
        this.data = data;
    }

    public EssayEntity getData() {
        return data;
    }

    public void setData(EssayEntity data) {
        this.data = data;
    }
}
