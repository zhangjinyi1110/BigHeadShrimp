package com.example.zjy.bigheadshrimp.main.matter;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.zjy.bigheadshrimp.base.BaseRepository;
import com.example.zjy.bigheadshrimp.database.MatterDao;
import com.example.zjy.bigheadshrimp.entity.MatterEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 仕囵弹 on 2018/11/8.
 * 事件仓库
 */

public class MatterRepository extends BaseRepository<MatterDao> {

    private LiveData<List<MatterEntity>> liveData;

//    public MatterRepository(Application application) {
//        super(application);
//        liveData = dao.queryAll();
//    }

    public LiveData<List<MatterEntity>> getLiveData() {
        return liveData;
    }

    public void delete(MatterEntity entity) {
        Observable.just(entity)
                .subscribeOn(Schedulers.computation())
                .subscribe(new Consumer<MatterEntity>() {
                    @Override
                    public void accept(MatterEntity entity) throws Exception {
                        dao.delete(entity);
                    }
                });
    }

    public void insert(MatterEntity entity){
//        flowable(dao.insert(entity))
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        Log.d(TAG, "accept: " + aLong);
//                    }
//                });
        Observable.just(entity)
                .subscribeOn(Schedulers.computation())
                .subscribe(new Consumer<MatterEntity>() {
                    @Override
                    public void accept(MatterEntity entity) throws Exception {
                        dao.insert(entity);
                    }
                });
    }

    public void update(MatterEntity entity){
        Observable.just(entity)
                .subscribeOn(Schedulers.computation())
                .subscribe(new Consumer<MatterEntity>() {
                    @Override
                    public void accept(MatterEntity entity) throws Exception {
                        dao.update(entity);
                    }
                });
    }

    @Override
    protected void init() {
        liveData = dao.queryAll();
    }
}
