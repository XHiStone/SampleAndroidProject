package com.app.sampleandroidproject.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

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

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import rx.functions.Action1;

public class GreenDaoActivity extends BaseActivity implements HttpRequest<PagerBean<SysUserResponseVo>> {
    @Inject
    protected LoginRequest login;

    @BindView(R.id.text_db_content)
    TextView text_db_content;

    @BindViews({R.id.button0, R.id.button1, R.id.button2, R.id.button3})
    List<Button> buttons;

    private SysUserResponseVo user;

    @Override
    protected int getContentResource() {
        return R.layout.activity_green_dao;
    }

    @Override
    protected void startWork(Bundle savedInstanceState) {
        BaseApplication.getInstance().creatLoginComponent(null).inject(this);
        setTittleText("GreenDaoActivity");
        login.setUser("15892", "123456",
                "e3225cc1-eba7-4993-93f9-63044d4ee540",
                AppUtil.getPackageInfo(this).versionName, 2);
        initData();
    }

    private void initData() {
        RxView.clicks(buttons.get(0)).throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        htttpRequest().login(GreenDaoActivity.this, false, login, GreenDaoActivity.this);
                        if (user != null) {
                            user.setId(null);
                            AppManagers.getDbManagers().insertUser(user);
//                            AppManagers.getToastor().showSingletonToast(
//                                    AppManagers.getDbManagers().usersCount()
//                            );
                            addText();
                        }
                    }
                });
        RxView.clicks(buttons.get(1)).throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        AppManagers.getDbManagers().deleteUser();
                        addText();
                    }
                });
        RxView.clicks(buttons.get(2)).throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        List<SysUserResponseVo> users = AppManagers.getDbManagers().queryUser();
                        for (SysUserResponseVo user : users) {
                            user.setUserName("李二虎");
                            AppManagers.getDbManagers().upUser(user);
                        }
                        addText();
                    }
                });
        RxView.clicks(buttons.get(3)).throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        addText();
                    }
                });
    }

    private void addText() {
        text_db_content.setText("");
        List<SysUserResponseVo> users = AppManagers.getDbManagers().queryUser();
        for (int i = 0; i < users.size(); i++) {
            text_db_content.append(users.get(i).userName + ":" + users.get(i).deptName + "-" + users.get(i).postName + "-id-" + users.get(i).getId() + "\n");
        }
    }

    @Override
    protected void stopWork() {

    }

    @Override
    public void onHttpStart() {

    }

    @Override
    public void onHttpSuccess(PagerBean<SysUserResponseVo> sysUserResponseVoPagerBean) {
        user = sysUserResponseVoPagerBean.content.get(0);
    }

    @Override
    public void onHttpFinish() {

    }

    @Override
    public void onHttpError() {

    }
}
