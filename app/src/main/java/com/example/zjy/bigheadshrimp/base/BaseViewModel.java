package com.example.zjy.bigheadshrimp.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.lang.reflect.ParameterizedType;

/**
 * Created by 仕囵弹 on 2018/11/7.
 * ViewModel基类
 */

public class BaseViewModel<T extends BaseRepository> extends AndroidViewModel{

    public T repository;
    private final String TAG = BaseViewModel.class.getSimpleName();

    @SuppressWarnings("unchecked")
    public BaseViewModel(@NonNull Application application) {
        super(application);
        repository = createRepository(application);
    }

    @SuppressWarnings("unchecked")
    private T createRepository(Application application) {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<T> tClass = (Class<T>) type.getActualTypeArguments()[0];
        T t = null;
        try {
            t = tClass.newInstance();
            t.whit(application);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            Log.e(TAG, "createRepository: " + e.getLocalizedMessage());
        }
        return t;
    }

}
