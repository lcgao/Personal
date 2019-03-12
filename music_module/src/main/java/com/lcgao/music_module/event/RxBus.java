package com.lcgao.music_module.event;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by lcgao on 2017/9/1.
 */

public class RxBus {
    private static volatile RxBus INSTANCE;

    private final Subject<Object> mBus;

    private RxBus(){
        mBus = PublishSubject.create().toSerialized();
    }

    public static RxBus getDefault(){
        if(INSTANCE == null){
            synchronized (RxBus.class){
                if (INSTANCE == null) {
                    INSTANCE = new RxBus();
                }
            }
        }
        return INSTANCE;
    }

    public void post(Object o){
        mBus.onNext(o);
    }

    public <T> Observable<T> toObservable(Class<T> eventType){
        return mBus.ofType(eventType);
    }
}
