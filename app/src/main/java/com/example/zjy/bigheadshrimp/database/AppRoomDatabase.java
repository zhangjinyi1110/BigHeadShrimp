package com.example.zjy.bigheadshrimp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.zjy.bigheadshrimp.entity.MatterEntity;

/**
 * Created by 仕囵弹 on 2018/11/8.
 * 数据库
 */
@Database(version = 1, exportSchema = false, entities = {MatterEntity.class})
public abstract class AppRoomDatabase extends RoomDatabase {

    private static AppRoomDatabase INSTANCE;

    public abstract MatterDao matterDao();

    public static AppRoomDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext()
                            , AppRoomDatabase.class, "app_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
