package com.lcgao.music_module.music.data.source.local;

import androidx.annotation.NonNull;

import com.google.common.base.Optional;
import com.lcgao.music_module.MyApplication;
import com.lcgao.music_module.music.data.MusicsDataSource;
import com.lcgao.music_module.music.data.model.DaoSession;
import com.lcgao.music_module.music.data.model.Music;
import com.lcgao.music_module.music.data.model.MusicDao;

import java.util.List;

import io.reactivex.Flowable;

public class MusicsLocalDataSource implements MusicsDataSource {

    private static MusicsLocalDataSource INSTANCE;
    private final DaoSession mDaoSession;

    private MusicsLocalDataSource() {
        mDaoSession = MyApplication.getInstance().getmDaoSession();
    }

    public static MusicsLocalDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (MusicsLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MusicsLocalDataSource();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Flowable<List<Music>> getMusics() {
        List<Music> musics = mDaoSession.getMusicDao().queryBuilder().list();
        return Flowable.just(musics);
    }

    @Override
    public Flowable<Optional<Music>> getMusic(@NonNull String musicId) {
        Music music = mDaoSession.getMusicDao()
                .queryBuilder()
                .where(MusicDao.Properties.Id.eq(musicId))
                .uniqueOrThrow();
        return Flowable.just(Optional.of(music));
    }

    @Override
    public void saveMusic(@NonNull Music music) {
        mDaoSession.getMusicDao().insertOrReplace(music);
    }

    @Override
    public void saveMusics(@NonNull List<Music> musics) {
        for (int i = 0; i < musics.size(); i++) {
            saveMusic(musics.get(i));
        }
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
