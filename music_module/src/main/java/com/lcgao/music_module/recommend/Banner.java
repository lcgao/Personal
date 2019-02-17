package com.lcgao.music_module.recommend;

public class Banner {
    private String imageUrl;
    private Long targetId;
    private String adid;
    private Integer targetType;
    private String titleColor;
    private String typeTitle;
    private String url;
    private boolean exclusive;
    private String encodeId;

    public Banner() {
    }

    public Banner(String imageUrl, Long targetId, String adid, Integer targetType, String titleColor, String typeTitle, String url, boolean exclusive, String encodeId) {
        this.imageUrl = imageUrl;
        this.targetId = targetId;
        this.adid = adid;
        this.targetType = targetType;
        this.titleColor = titleColor;
        this.typeTitle = typeTitle;
        this.url = url;
        this.exclusive = exclusive;
        this.encodeId = encodeId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getAdid() {
        return adid;
    }

    public void setAdid(String adid) {
        this.adid = adid;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public String getTypeTitle() {
        return typeTitle;
    }

    public void setTypeTitle(String typeTitle) {
        this.typeTitle = typeTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public void setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
    }

    public String getEncodeId() {
        return encodeId;
    }

    public void setEncodeId(String encodeId) {
        this.encodeId = encodeId;
    }
}
