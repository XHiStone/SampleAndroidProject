package com.app.sampleandroidproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.app.sampleandroidproject.R;
import com.app.sampleandroidproject.app.AppManagers;
import com.app.sampleandroidproject.app.BaseApplication;
import com.app.sampleandroidproject.beans.PagerBean;
import com.app.sampleandroidproject.beans.request.LoginRequest;
import com.app.sampleandroidproject.beans.result.SysUserResponseVo;
import com.app.sampleandroidproject.http.HttpRequest;
import com.app.sampleandroidproject.ui.base.BaseActivity;
import com.app.sampleandroidproject.utils.AppUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;

public class DaggerActivity extends BaseActivity {

    @Inject
    protected LoginRequest login;

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.my_image_view)
    SimpleDraweeView my_image_view;


    @Override
    protected int getContentResource() {
        return R.layout.activity_dagger;
    }

    @Override
    protected void startWork(Bundle savedInstanceState) {
        setTittleText("DaggerActivity");
        initData();
    }

    private void initData() {
        BaseApplication.getInstance().creatLoginComponent(null).inject(this);
        username.setText("15892");
        password.setText("123456");
        RxView.clicks(button).throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    login.setUser(username.getText().toString(), password.getText().toString(),
                            "e3225cc1-eba7-4993-93f9-63044d4ee540",
                            AppUtil.getPackageInfo(DaggerActivity.this).versionName, 2);
                    htttpRequest(AppManagers.getHttpManager().login( false, login,
                            new HttpRequest<PagerBean<SysUserResponseVo>>() {
                        @Override
                        public void onHttpSuccess(PagerBean<SysUserResponseVo> result) {
                            startActivity(new Intent(DaggerActivity.this, MainActivity.class));
                            AppManagers.getActivitiesManager().finishActivity(DaggerActivity.this);
                        }

                        @Override
                        public void onHttpError() {

                        }

                    }));
                });
        my_image_view.setImageURI("http://img2.3lian.com/2014/f2/37/d/40.jpg");
    }

    @Override
    protected void stopWork() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManagers.getToastor().showSingletonToast("Dagger");
    }
}
