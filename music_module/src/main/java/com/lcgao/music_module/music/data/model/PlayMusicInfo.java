package com.lcgao.music_module.music.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class PlayMusicInfo implements Parcelable {
    private Music music;
    private List<Music> playList;
    private boolean isPause;
    private long currentTime;

    public PlayMusicInfo() {
    }

    public PlayMusicInfo(Music music, List<Music> playList, boolean isPause, long currentTime) {
        this.music = music;
        this.playList = playList;
        this.isPause = isPause;
        this.currentTime = currentTime;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public List<Music> getPlayList() {
        return playList;
    }

    public void setPlayList(List<Music> playList) {
        this.playList = playList;
    }

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean pause) {
        isPause = pause;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        return "PlayMusicInfo{" +
                "music=" + music +
                ", playList=" + playList +
                ", isPause=" + isPause +
                ", currentTime=" + currentTime +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(music, 0);
        dest.writeTypedList(playList);
        dest.writeInt(isPause ? 1 : 0);
        dest.writeLong(currentTime);
    }

    public static final Parcelable.Creator<PlayMusicInfo> CREATOR = new Parcelable.Creator<PlayMusicInfo>(){

        @Override
        public PlayMusicInfo createFromParcel(Parcel source) {
            return new PlayMusicInfo(source);
        }

        @Override
        public PlayMusicInfo[] newArray(int size) {
            return new PlayMusicInfo[size];
        }
    };

    private PlayMusicInfo(Parcel in) {
        music = in.readParcelable(Thread.currentThread().getContextClassLoader());
        if(playList == null){
            playList = new ArrayList<>();
        }
        in.readTypedList(playList, Music.CREATOR);
        isPause = in.readInt() == 1;
        currentTime = in.readLong();
    }
}
