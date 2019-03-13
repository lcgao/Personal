package com.lcgao.music_module.event;

public class UpdateProgressEvent {
    public long mCurrentTime;

    public UpdateProgressEvent(long currentTime) {
        mCurrentTime = currentTime;
    }
}
