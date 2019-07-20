package com.lcgao.music_module.music;

import androidx.annotation.NonNull;

import com.lcgao.music_module.music.data.MusicsRepository;
import com.lcgao.music_module.music.data.model.Music;
import com.lcgao.music_module.util.LogUtil;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MusicsPresenter implements MusicsContract.Presenter {

    private static final String TAG = "MusicsPresenter: ";

    @NonNull
    private final MusicsRepository mMusicsRepository;

    @NonNull
    private final MusicsContract.View mMusicsView;

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    private boolean mFirstLoad = true;

    public MusicsPresenter(@NonNull MusicsRepository musicsRepository,
                           @NonNull MusicsContract.View musicsView) {
        mMusicsRepository = musicsRepository;
        mMusicsView = musicsView;
        mCompositeDisposable = new CompositeDisposable();
        mMusicsView.setPresenter(this);
    }

    @Override
    public void loadMusics(boolean forceUpdate) {
        loadMusics(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    private void loadMusics(final boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mMusicsView.setLoadingIndicator(true);
        }
        if (forceUpdate) {
            mMusicsRepository.refreshMusics();
        }

        mCompositeDisposable.clear();
        Disposable disposable = mMusicsRepository
                .getMusics()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> LogUtil.d(TAG + "getMusics()->RxJava->doFinally->run()"))
                .subscribe(musics -> {
                    processMusics(musics);
                    mMusicsView.setLoadingIndicator(false);
                });
        mCompositeDisposable.add(disposable);
    }

    private void processMusics(@NonNull List<Music> musics) {
        if (musics.isEmpty()) {
            mMusicsView.showNoMusics();
        } else {
            mMusicsView.showMusics(musics);
        }
    }

    @Override
    public void subscribe() {
        loadMusics(false);
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }
}
