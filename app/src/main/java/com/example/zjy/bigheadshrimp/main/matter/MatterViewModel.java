package com.example.zjy.bigheadshrimp.main.matter;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.zjy.bigheadshrimp.base.BaseViewModel;
import com.example.zjy.bigheadshrimp.entity.MatterEntity;

import java.util.List;

/**
 * Created by 仕囵弹 on 2018/11/8.
 * MatterViewModel
 */

public class MatterViewModel extends BaseViewModel<MatterRepository> {

    private LiveData<List<MatterEntity>> liveData;

    public MatterViewModel(@NonNull Application application) {
        super(application);
//        repository = new MatterRepository(application);
        liveData = repository.getLiveData();
    }

    public LiveData<List<MatterEntity>> getLiveData() {
        return liveData;
    }

    public void delete(MatterEntity entity){
        repository.delete(entity);
    }

    public void insert(MatterEntity entity){
        repository.insert(entity);
    }

    public void update(MatterEntity entity){
        repository.update(entity);
    }
}
