package com.lcgao.music_module.music.data.model;

public class SongInfo {
    private String name;
    private String singer;
    private String path;
    private int duration;
    private long size;
    private String image;
    private String album;

    public SongInfo() {
    }

    public SongInfo(String name, String singer, String path, int duration, long size, String image, String album) {
        this.name = name;
        this.singer = singer;
        this.path = path;
        this.duration = duration;
        this.size = size;
        this.image = image;
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "SongInfo{" +
                "name='" + name + '\'' +
                ", singer='" + singer + '\'' +
                ", path='" + path + '\'' +
                ", duration=" + duration +
                ", size=" + size +
                ", image='" + image + '\'' +
                ", album='" + album + '\'' +
                '}';
    }
}