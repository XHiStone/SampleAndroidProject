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
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import rx.functions.Action1;

public class DaggerActivity extends BaseActivity implements HttpRequest<PagerBean<SysUserResponseVo>> {

    @Inject
    protected LoginRequest login;

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;

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
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        login.setUser(username.getText().toString(), password.getText().toString(),
                                "e3225cc1-eba7-4993-93f9-63044d4ee540",
                                AppUtil.getPackageInfo(DaggerActivity.this).versionName, 2);
                        htttpRequest().login(DaggerActivity.this, false, login, DaggerActivity.this);
                    }
                });
    }

    @Override
    protected void stopWork() {

    }


    @Override
    public void onHttpStart() {
        showProgressDialog("加载中...");
    }

    @Override
    public void onHttpSuccess(PagerBean<SysUserResponseVo> sysUserResponseVoPagerBean) {
        startActivity(new Intent(this, MainActivity.class));
        AppManagers.getActivitiesManager().finishActivity(this);
    }

    @Override
    public void onHttpFinish() {
        dissmissProgressDialog();
    }

    @Override
    public void onHttpError() {
        dissmissProgressDialog();
    }
}
