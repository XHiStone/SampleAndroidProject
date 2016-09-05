package com.app.sampleandroidproject.ui;

import android.os.Bundle;

import com.app.sampleandroidproject.R;
import com.app.sampleandroidproject.app.ActivitiesManager;
import com.app.sampleandroidproject.app.AppManagers;
import com.app.sampleandroidproject.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void startWork(Bundle savedInstanceState) {
        AppManagers.getToastor().showSingletonToast(ActivitiesManager.getInstance().getStack().size()+"--");
    }
}
