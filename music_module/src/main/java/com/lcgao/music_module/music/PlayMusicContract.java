package com.lcgao.music_module.music;

import com.lcgao.music_module.BasePresenter;
import com.lcgao.music_module.BaseView;

public interface PlayMusicContract {

    interface View extends BaseView<Presenter> {
        void playMusic();

        void pauseMusic();
    }

    interface Presenter extends BasePresenter {
        void playMusic();

        void pauseMusic();
    }
}
