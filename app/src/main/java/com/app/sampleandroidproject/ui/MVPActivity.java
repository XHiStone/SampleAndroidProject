package com.app.sampleandroidproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.app.sampleandroidproject.R;
import com.app.sampleandroidproject.app.AppManagers;
import com.app.sampleandroidproject.app.BaseApplication;
import com.app.sampleandroidproject.beans.request.LoginRequest;
import com.app.sampleandroidproject.beans.result.SysUserResponseVo;
import com.app.sampleandroidproject.ui.base.BaseActivity;
import com.app.sampleandroidproject.ui.login.mvp.LoginPresenter;
import com.app.sampleandroidproject.utils.AppUtil;
import com.app.sampleandroidproject.view.ClearEditText;
import com.app.sampleandroidproject.view.LoginView;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import rx.functions.Action1;

public class MVPActivity extends BaseActivity implements LoginView {

    @Inject
    protected LoginPresenter loginPresenter;
    @Inject
    protected LoginRequest login;
    @BindView(R.id.edit_account_number)
    ClearEditText edit_account_number;
    @BindView(R.id.edit_account_psd)
    ClearEditText edit_account_psd;
    @BindView(R.id.btn_login_commit)
    Button btn_login_commit;

    @Override
    protected int getContentResource() {
        return R.layout.activity_mvp;
    }

    @Override
    protected void startWork(Bundle savedInstanceState) {
        setTittleText("MVPActivity");
        initData();
    }

    private void initData() {
        BaseApplication.getInstance().creatLoginComponent(this).inject(this);
        edit_account_number.setText("15892");
        edit_account_psd.setText("123456");
        RxTextView.textChanges(edit_account_number)
                .subscribe(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {
                        loginPresenter.checkInput(charSequence.toString(),
                                edit_account_psd.getText().toString());
                    }
                });

        RxTextView.textChanges(edit_account_psd)
                .subscribe(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {
                        loginPresenter.checkInput(edit_account_number.getText().toString(),
                                charSequence.toString());
                    }
                });
        RxView.clicks(btn_login_commit).throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        login.setUser(edit_account_number.getText().toString(),
                                edit_account_psd.getText().toString(),
                                "e3225cc1-eba7-4993-93f9-63044d4ee540",
                                AppUtil.getPackageInfo(MVPActivity.this).versionName, 2);
                        subscription = loginPresenter.login(MVPActivity.this, login);
                    }
                });
    }

    @Override
    protected void stopWork() {

    }

    @Override
    public void canLogin(boolean canLogin) {
        if (canLogin) {
            btn_login_commit.setEnabled(canLogin);
        } else {
            btn_login_commit.setEnabled(false);
        }
    }

    @Override
    public void success(SysUserResponseVo user) {
        startActivity(new Intent(this, MainActivity.class));
        AppManagers.getActivitiesManager().finishActivity(this);
    }

    @Override
    public void failture() {
        dissmissProgressDialog();
    }

    @Override
    public void showLoading() {
        showProgressDialog("加载中...");
    }

    @Override
    public void hideLoading() {
        dissmissProgressDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManagers.getToastor().showSingletonToast("MVP");
    }
}
