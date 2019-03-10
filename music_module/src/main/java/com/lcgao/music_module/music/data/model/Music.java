package com.lcgao.music_module.music.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "Music.db")
public class Music implements Parcelable {
    @Id
    private long id;

    @Property(nameInDb = "title")
    private String title;

    @Property(nameInDb = "artist")
    private String artist;

    @Property(nameInDb = "album")
    private String album;

    @Property(nameInDb = "album_id")
    private long albumId;

    @Property(nameInDb = "duration")
    private long duration;

    @Property(nameInDb = "path")
    private String path;

    @Property(nameInDb = "file_name")
    private String fileName;

    @Property(nameInDb = "file_size")
    private long fileSize;

    public Music() {
    }

    @Keep
    public Music(long id, String title, String artist, String album, long albumId, long duration, String path, String fileName, long fileSize) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.albumId = albumId;
        this.duration = duration;
        this.path = path;
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", albumId=" + albumId +
                ", duration=" + duration +
                ", path='" + path + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(artist);
        dest.writeString(album);
        dest.writeLong(albumId);
        dest.writeLong(duration);
        dest.writeString(path);
        dest.writeString(fileName);
        dest.writeLong(fileSize);
    }

    public static final Parcelable.Creator<Music> CREATOR = new Parcelable.Creator<Music>(){

        @Override
        public Music createFromParcel(Parcel source) {
            return new Music(source);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };

    private Music(Parcel in){
        id = in.readLong();
        title = in.readString();
        artist = in.readString();
        album = in.readString();
        albumId = in.readLong();
        duration = in.readLong();
        path = in.readString();
        fileName = in.readString();
        fileSize = in.readLong();
    }
}
