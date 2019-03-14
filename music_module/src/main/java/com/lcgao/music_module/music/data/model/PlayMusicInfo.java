package com.lcgao.music_module.music.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class PlayMusicInfo implements Parcelable {
    private List<Music> playList;
    private boolean isPause;
    private long currentTime;
    private int currentPosition;

    public PlayMusicInfo() {
    }

    public PlayMusicInfo(List<Music> playList,int currentPosition, boolean isPause, long currentTime) {
        this.playList = playList;
        this.currentPosition = currentPosition;
        this.isPause = isPause;
        this.currentTime = currentTime;
    }


    public List<Music> getPlayList() {
        return playList;
    }

    public void setPlayList(List<Music> playList) {
        this.playList = playList;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
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
                "playList=" + playList +
                ", isPause=" + isPause +
                ", currentTime=" + currentTime +
                ", currentPosition=" + currentPosition +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(playList);
        dest.writeInt(currentPosition);
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
        if(playList == null){
            playList = new ArrayList<>();
        }
        in.readTypedList(playList, Music.CREATOR);
        currentPosition = in.readInt();
        isPause = in.readInt() == 1;
        currentTime = in.readLong();
    }
}
