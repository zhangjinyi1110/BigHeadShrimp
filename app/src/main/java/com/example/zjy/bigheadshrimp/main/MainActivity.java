package com.example.zjy.bigheadshrimp.main;

import android.view.View;
import android.widget.TextView;

import com.example.zjy.bigheadshrimp.R;
import com.example.zjy.bigheadshrimp.base.BaseActivity;
import com.example.zjy.bigheadshrimp.main.matter.MatterFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        ((TextView) findViewById(R.id.toolbar_title)).setText("首页");
        findViewById(R.id.toolbar_back).setVisibility(View.GONE);
        setFragment(R.id.main_content, MatterFragment.newInstance());
    }
}
