package com.lcgao.music_module.music.data.model;


import android.content.Context;

import com.lcgao.music_module.util.LocalMusicHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MusicListRepository {

    private volatile static MusicListRepository INSTANCE = null;

    Map<String, Music> mCachedMusicList;

    private boolean mCacheIsDirty = false;

    private Context mContext;

    private MusicListRepository(Context context){
        mContext = context.getApplicationContext();
    }

    public static MusicListRepository getInstance(Context context){
        if(INSTANCE == null){
            synchronized (MusicListRepository.class){
                if(INSTANCE == null){
                    INSTANCE = new MusicListRepository(context);
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    public void getMusicList(final IDataSource.LoadDataCallback<Music> callback){
        if(callback == null){
            return;
        }
        List<Music> musicList = new ArrayList<>();
//        LocalMusicHelper.scanLocalMusic(mContext,musicList);
//        List<SongInfo> songInfos = new ArrayList<>();
//        SongInfo songInfo1 = new SongInfo("春风十里", "鹿先森乐队", "xxx", (6*60+15)*1000, 10*1024*1024, "xxx", "所有的酒，都不如你");
//        SongInfo songInfo2 = new SongInfo("成都", "赵雷", "xxx", (6*60+15)*1000, 10*1024*1024, "xxx", "无法长大");
//        SongInfo songInfo3 = new SongInfo("See You Again", "Wiz Khalifa", "xxx", (6*60+15)*1000, 10*1024*1024, "xxx", "Furious7");
//        SongInfo songInfo4 = new SongInfo("夜空中最亮的星", "逃跑计划", "xxx", (6*60+15)*1000, 10*1024*1024, "xxx", "世界");
//        SongInfo songInfo5 = new SongInfo("Always With Me", "木村弓/奥户巴寿", "xxx", (6*60+15)*1000, 10*1024*1024, "xxx", "幸福的味道");
//        songInfos.add(songInfo1);
//        songInfos.add(songInfo2);
//        songInfos.add(songInfo3);
//        songInfos.add(songInfo4);
//        songInfos.add(songInfo5);
//        callback.onDataLoaded(songInfos);
    }
}
