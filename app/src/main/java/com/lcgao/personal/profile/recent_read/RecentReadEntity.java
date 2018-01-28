package com.lcgao.personal.profile.recent_read;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.lcgao.personal.MyApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lcgao on 2018/1/28.
 */

public class RecentReadEntity {
    private String url;
    private String title;
    private String content;

    public RecentReadEntity() {
    }

    public RecentReadEntity(String url, String title, String content) {
        this.url = url;
        this.title = title;
        this.content = content;
    }

    public static void addToReadHistory(RecentReadEntity entity){
        SharedPreferences sharedPreferences = MyApplication.getInstance().getSharedPreferences("read_history", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = new Gson().toJson(entity);
        editor.putString(entity.getUrl(), json);
        editor.apply();
    }

    public static List<RecentReadEntity> getAllReadHistory(){
        SharedPreferences sharedPreferences = MyApplication.getInstance().getSharedPreferences("read_history", Context.MODE_PRIVATE);
        Map<String, String> map = (Map<String, String>)sharedPreferences.getAll();

        List<RecentReadEntity> recentReadEntities = new ArrayList<>();
        for(String json : map.values()){
            RecentReadEntity entity = new Gson().fromJson(json, RecentReadEntity.class);
            recentReadEntities.add(entity);
        }
        return recentReadEntities;
    }

    public static void deletAllHistory(){
        SharedPreferences sharedPreferences = MyApplication.getInstance().getSharedPreferences("read_history", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().commit();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
