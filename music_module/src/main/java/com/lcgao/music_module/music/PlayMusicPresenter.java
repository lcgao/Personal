package com.lcgao.music_module.music;

public class PlayMusicPresenter implements PlayMusicContract.Presenter {

    private final PlayMusicContract.View mView;

    public PlayMusicPresenter(PlayMusicContract.View view){
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void playMusic() {

    }

    @Override
    public void pauseMusic() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
