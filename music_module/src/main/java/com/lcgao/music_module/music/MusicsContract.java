package com.lcgao.music_module.music;

import com.lcgao.music_module.BasePresenter;
import com.lcgao.music_module.BaseView;
import com.lcgao.music_module.music.data.model.Music;

import java.util.List;

public interface MusicsContract {

    interface View extends BaseView<Presenter>{
        void showMusics(List<Music> musics);

        void setLoadingIndicator(boolean active);

        void showNoMusics();
    }

    interface Presenter extends BasePresenter{
        void loadMusics(boolean forceUpdate);
    }
}
