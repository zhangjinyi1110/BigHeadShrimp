package com.example.zjy.bigheadshrimp.base;

import android.app.Application;

import com.example.zjy.bigheadshrimp.database.AppRoomDatabase;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 仕囵弹 on 2018/11/7.
 * Repository基类
 */

public abstract class BaseRepository<T> {

    protected final String TAG = getClass().getSimpleName();
    public AppRoomDatabase database;
    public T dao;

    @SuppressWarnings("unchecked")
    public void whit(Application application){
        database = AppRoomDatabase.getInstance(application);
        dao = (T) database.matterDao();
        init();
    }

    protected abstract void init();

    protected <C> Flowable<C> flowable(Flowable<C> flowable){
        return flowable.subscribeOn(Schedulers.computation());
    }

}
