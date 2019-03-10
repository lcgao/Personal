package com.lcgao.music_module.music.data.model;

import java.util.List;

public interface IDataSource<T> {
    interface LoadDataCallback<T> {
        void onDataLoaded(List<T> datas);
        void onDataNotAvailable();
    }

    interface GetDataCallback<T> {
        void onDataLoaded(T data);
        void onDataNotAvailable();
    }
}
