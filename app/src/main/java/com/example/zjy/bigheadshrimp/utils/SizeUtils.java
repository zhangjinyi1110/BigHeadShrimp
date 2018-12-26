package com.example.zjy.bigheadshrimp.utils;

import android.content.Context;

/**
 * Created by 仕囵弹 on 2018/11/11.
 * 尺寸工具类
 */

public class SizeUtils {

    private Context context;

    public SizeUtils(Context context) {
        this.context = context;
    }

    public int dp2px(float value){
        return dp2px(context, value);
    }

    public static int dp2px(Context context, float value){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (value * scale);
    }

}
