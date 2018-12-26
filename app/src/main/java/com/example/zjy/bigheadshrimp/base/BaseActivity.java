package com.example.zjy.bigheadshrimp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by 仕囵弹 on 2018/11/5.
 * activity基类
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        init();
    }

    protected abstract int getLayoutId();

    protected abstract void init();

    protected <F extends Fragment> void setFragment(int layoutId, F fragment){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(layoutId, fragment)
                .commit();
    }

    public void showLoad(){
        showLoad("数据加载中...");
    }

    public void showLoad(String string){
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    public void hideLoad(){
        Toast.makeText(this, "dismiss", Toast.LENGTH_SHORT).show();
    }
}
