package com.lcgao.home.zhihu;

import java.util.List;

/**
 * Created by lcgao on 2017/12/28.
 */

public class Storie implements Zhihu{
    private int id;
    private int type;
    private String ga_prefix;
    private String title;
    private List<String> images;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public String getGa_prefix() {
        return ga_prefix;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getImage() {
        List<String> images = getImages();
        if(images == null || images.size() == 0)
        {
            return "";
        }
        return images.get(0);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
