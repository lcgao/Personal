package com.lcgao.music_module.music.data;


import androidx.annotation.NonNull;

import com.google.common.base.Optional;
import com.lcgao.music_module.music.data.model.Music;

import java.util.List;

import io.reactivex.Flowable;

public interface MusicsDataSource {
    Flowable<List<Music>> getMusics();

    Flowable<Optional<Music>> getMusic(@NonNull String musicId);

    void saveMusic(@NonNull Music music);

    void saveMusics(@NonNull List<Music> musics);

    void refreshMusics();

    void deleteMusic(@NonNull String musicId);

}
