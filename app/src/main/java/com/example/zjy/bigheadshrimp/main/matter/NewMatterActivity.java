package com.example.zjy.bigheadshrimp.main.matter;

import android.view.View;
import android.widget.TextView;

import com.example.zjy.bigheadshrimp.R;
import com.example.zjy.bigheadshrimp.base.BaseActivity;

public class NewMatterActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_matter;
    }

    @Override
    protected void init() {
        ((TextView) findViewById(R.id.toolbar_title)).setText("添加新事件");
        findViewById(R.id.toolbar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setFragment(R.id.new_matter_content, NewMatterFragment.newInstance());
    }
}
