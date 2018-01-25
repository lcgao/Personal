package com.lcgao.personal.favourite.express;

/**
 * Created by lcgao on 2018/1/25.
 */

public class ExpressData {
    private String time;
    private String ftime;
    private String context;
    private String location;

    public ExpressData() {
    }

    public ExpressData(String time, String ftime, String context, String location) {
        this.time = time;
        this.ftime = ftime;
        this.context = context;
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "ExpressData{" +
                "time='" + time + '\'' +
                ", ftime='" + ftime + '\'' +
                ", context='" + context + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
