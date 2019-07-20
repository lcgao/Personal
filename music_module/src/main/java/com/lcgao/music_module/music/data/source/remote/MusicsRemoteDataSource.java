package com.lcgao.music_module.music.data.source.remote;


import androidx.annotation.NonNull;

import com.google.common.base.Optional;
import com.lcgao.music_module.MyApplication;
import com.lcgao.music_module.music.data.MusicsDataSource;
import com.lcgao.music_module.music.data.model.Music;
import com.lcgao.music_module.util.LocalMusicHelper;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;

public class MusicsRemoteDataSource implements MusicsDataSource {

    private static MusicsRemoteDataSource INSTANCE;

    private static final int SERVICE_LATENCY_IN_MILLIS = 5000;

    private static final Map<Long, Music> MUSICS_SERVICE_DATA;

    static {
        MUSICS_SERVICE_DATA = new LinkedHashMap<>(2);
    }

    private MusicsRemoteDataSource() {

    }

    public static MusicsRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (MusicsRemoteDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MusicsRemoteDataSource();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Flowable<List<Music>> getMusics() {
        List<Music> musics = new LinkedList<>();
        LocalMusicHelper.scanLocalMusic(MyApplication.getInstance().getApplicationContext(), musics);
        return Flowable.just(musics);
    }

    @Override
    public Flowable<Optional<Music>> getMusic(@NonNull String musicId) {
        return null;
    }

    @Override
    public void saveMusic(@NonNull Music music) {

    }

    @Override
    public void saveMusics(@NonNull List<Music> musics) {

    }

    @Override
    public void refreshMusics() {

    }

    @Override
    public void deleteMusic(@NonNull String musicId) {

    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
