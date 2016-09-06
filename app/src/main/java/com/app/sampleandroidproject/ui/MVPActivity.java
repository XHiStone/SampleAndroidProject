package com.app.sampleandroidproject.ui;

import android.os.Bundle;

import com.app.sampleandroidproject.R;
import com.app.sampleandroidproject.ui.base.BaseActivity;

public class MVPActivity extends BaseActivity {

    @Override
    protected int getContentResource() {
        return R.layout.activity_mvp;
    }

    @Override
    protected void startWork(Bundle savedInstanceState) {
        setTittleText("MVP");
    }

}
