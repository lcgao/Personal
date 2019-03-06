package com.lcgao.music_module.music.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.lcgao.music_module.music.model.MusicListRepository;
import com.lcgao.music_module.music.model.SongInfo;

public class MusicListViewModel extends ViewModel {
    private final MusicListRepository mMusicListRespository;

    private final MutableLiveData<Boolean> mDataLoading = new MutableLiveData<>();

    private final MutableLiveData<Boolean> mError = new MutableLiveData<>();

    private final MutableLiveData<SongInfo> mSonInfo = new MutableLiveData<>();

    private final MutableLiveData mEmpty = new MutableLiveData();

    public MusicListViewModel(MusicListRepository musicListRepository) {
        mMusicListRespository = musicListRepository;
    }

    public void start() {

    }

    public void loadMusicList() {

    }

    public LiveData<Boolean> getDataLoading() {
        return mDataLoading;
    }

    public MutableLiveData<Boolean> getmError() {
        return mError;
    }

    public MutableLiveData<SongInfo> getmMusicList() {
        return mSonInfo;
    }

    public MutableLiveData getmEmpty() {
        return mEmpty;
    }
}
