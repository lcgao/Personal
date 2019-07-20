package com.lcgao.music_module.music.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.common.base.Optional;
import com.lcgao.music_module.music.data.model.Music;

import org.reactivestreams.Publisher;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class MusicsRepository implements MusicsDataSource {

    @Nullable
    private static MusicsRepository INSTANCE = null;

    @NonNull
    private final MusicsDataSource mMusicsLocalDataSource;

    @NonNull
    private final MusicsDataSource mMusicsRemoteDataSource;

    @Nullable
    private Map<Long, Music> mCachedMusics;

    private boolean mCacheIsDirty = false;

    private MusicsRepository(@NonNull MusicsDataSource musicsLocalDataSource,
                             @NonNull MusicsDataSource musicsRemoteDataSource) {
        mMusicsLocalDataSource = musicsLocalDataSource;
        mMusicsRemoteDataSource = musicsRemoteDataSource;
    }

    public static MusicsRepository getInstance(@NonNull MusicsDataSource musicsLocalDataSource,
                                               @NonNull MusicsDataSource musicsRemoteDataSource) {
        if (INSTANCE == null) {
            synchronized (MusicsRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MusicsRepository(musicsLocalDataSource, musicsRemoteDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Flowable<List<Music>> getMusics() {
        if (mCachedMusics != null && !mCacheIsDirty) {
            return Flowable.fromIterable(mCachedMusics.values()).toList().toFlowable();
        } else if (mCachedMusics == null) {
            mCachedMusics = new LinkedHashMap<>();
        }
        Flowable<List<Music>> remoteMusics = getAndSaveRemoteMusics();
        if (mCacheIsDirty) {
            return remoteMusics;
        } else {
            Flowable<List<Music>> localMusics = getAndCacheLocalMusics();
            return Flowable.concat(localMusics, remoteMusics)
                    .filter(musics -> !musics.isEmpty())
                    .firstOrError()
                    .toFlowable();
        }
    }

    private Flowable<List<Music>> getAndCacheLocalMusics() {
        return mMusicsLocalDataSource
                .getMusics()
                .flatMap(new Function<List<Music>, Publisher<List<Music>>>() {
                    @Override
                    public Publisher<List<Music>> apply(List<Music> musics) {
                        return Flowable.fromIterable(musics)
                                .doOnNext(new Consumer<Music>() {
                                    @Override
                                    public void accept(Music music) {
                                        if (mCachedMusics == null) {
                                            mCachedMusics = new LinkedHashMap<>();
                                        }
                                        mCachedMusics.put(music.getId(), music);
                                    }
                                })
                                .toList()
                                .toFlowable();
                    }
                });

    }

    private Flowable<List<Music>> getAndSaveRemoteMusics() {
        return mMusicsRemoteDataSource
                .getMusics()
                .flatMap(new Function<List<Music>, Publisher<List<Music>>>() {
                    @Override
                    public Publisher<List<Music>> apply(List<Music> musics) {
                        return Flowable
                                .fromIterable(musics)
                                .doOnNext(new Consumer<Music>() {
                                    @Override
                                    public void accept(Music music) {
                                        mMusicsLocalDataSource.saveMusic(music);
                                        if (mCachedMusics == null) {
                                            mCachedMusics = new LinkedHashMap<>();
                                        }
                                        mCachedMusics.put(music.getId(), music);
                                    }
                                }).toList().toFlowable();
                    }
                }).doOnComplete(new Action() {
                    @Override
                    public void run() {
                        mCacheIsDirty = false;
                    }
                });
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
        mCacheIsDirty = true;
    }

    @Override
    public void deleteMusic(@NonNull String musicId) {

    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
