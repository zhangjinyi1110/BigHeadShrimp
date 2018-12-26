package com.example.zjy.bigheadshrimp.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by 仕囵弹 on 2018/11/8.
 * 事情类
 */
@Entity(tableName = "matter_table")
public class MatterEntity {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo
    public String title;

    @ColumnInfo
    public String content;

    @ColumnInfo
    public long createTime;

    @ColumnInfo
    public long executeTime;

    @ColumnInfo
    public long remindTime;

    @ColumnInfo
    public int remindType;

    @ColumnInfo
    public String matterPicPath;

}
