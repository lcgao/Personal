package com.lcgao.music_module.event;

import com.lcgao.music_module.music.data.model.PlayMusicInfo;

public class PlayMusicEvent {
    public PlayMusicInfo mPlayMusicInfo;

    public PlayMusicEvent(PlayMusicInfo playMusicInfo){
        mPlayMusicInfo = playMusicInfo;
    }
}
