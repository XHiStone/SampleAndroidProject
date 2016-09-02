package com.app.sampleandroidproject.ui.base;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

/**
 * SampleAndroidProject
 * com.app.sampleandroidproject.ui.base
 *
 * @Author: xie
 * @Time: 2016/9/2 9:48
 * @Description:
 */

public class BaseActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus();
        }


    }

    /**
     * @return void
     * @Title: setTranslucentStatus
     * @Description: (设置标题栏透明色)
     * @params []
     */
    private void setTranslucentStatus() {
        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
        localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
    }

}
