package com.example.zjy.bigheadshrimp.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.zjy.bigheadshrimp.entity.MatterEntity;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by 仕囵弹 on 2018/11/8.
 * MatterEntity表操作类
 */
@Dao
public interface MatterDao {

    @Insert
    void insert(MatterEntity entity);

    @Delete
    void delete(MatterEntity entity);

    @Update
    void update(MatterEntity entity);

    @Query("SELECT * from matter_table")
    LiveData<List<MatterEntity>> queryAll();

}
