package com.lcgao.music_module.recommend.model;

public class MusicList {
    private Long id;
    private int type;
    private String name;
    private String copywriter;
    private String picUrl;
    private boolean canDislike;
    private Long playCount;
    private Long trackCount;
    private boolean highQuality;
    private String alg;

    public MusicList() {
    }

    public MusicList(Long id, int type, String name, String copywriter, String picUrl, boolean canDislike, Long playCount, Long trackCount, boolean highQuality, String alg) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.copywriter = copywriter;
        this.picUrl = picUrl;
        this.canDislike = canDislike;
        this.playCount = playCount;
        this.trackCount = trackCount;
        this.highQuality = highQuality;
        this.alg = alg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCopywriter() {
        return copywriter;
    }

    public void setCopywriter(String copywriter) {
        this.copywriter = copywriter;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public boolean isCanDislike() {
        return canDislike;
    }

    public void setCanDislike(boolean canDislike) {
        this.canDislike = canDislike;
    }

    public Long getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Long playCount) {
        this.playCount = playCount;
    }

    public Long getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Long trackCount) {
        this.trackCount = trackCount;
    }

    public boolean isHighQuality() {
        return highQuality;
    }

    public void setHighQuality(boolean highQuality) {
        this.highQuality = highQuality;
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }
}
