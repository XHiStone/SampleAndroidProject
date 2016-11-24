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
import rx.Observable;

public class GreenDaoActivity extends BaseActivity {
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
                .subscribe(aVoid -> {
                    htttpRequest(AppManagers.getHttpManager().login(false, login, new HttpRequest<PagerBean<SysUserResponseVo>>() {
                        @Override
                        public void onHttpSuccess(PagerBean<SysUserResponseVo> result) {
                            user = result.content.get(0);
                            Observable.just(user)
                                    .filter(sysUserResponseVo -> sysUserResponseVo != null)
                                    .subscribe(sysUserResponseVo -> {
                                        sysUserResponseVo.setId(null);
                                        AppManagers.getDbManagers().insertUser(user);
                                        toastor.showSingleLongToast(AppManagers.getDbManagers().usersCount());
                                        addText();
                                    });
                        }

                        @Override
                        public void onHttpError() {

                        }
                    }));
                });
        RxView.clicks(buttons.get(1)).throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    htttpRequest(AppManagers.getHttpManager().login(false, login, new HttpRequest<PagerBean<SysUserResponseVo>>() {
                        @Override
                        public void onHttpSuccess(PagerBean<SysUserResponseVo> result) {
                            user = result.content.get(0);
                        }

                        @Override
                        public void onHttpError() {

                        }
                    }));
                    List<SysUserResponseVo> users = AppManagers.getDbManagers().queryUser();
                    Observable.just(users).filter(sysUserResponseVos -> sysUserResponseVos.size() > 0)
                            .subscribe(sysUserResponseVos -> {
                                SysUserResponseVo user1 = sysUserResponseVos.get(0);
                                final Long id = user1.getId();
                                AppManagers.getDbManagers().deleteUser(id);
                            });
                    addText();
                });
        RxView.clicks(buttons.get(2)).throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    List<SysUserResponseVo> users = AppManagers.getDbManagers().queryUser();
                    Observable.from(users).subscribe(user12 -> {
                        user12.setUserName("李二虎");
                        AppManagers.getDbManagers().upUser(user12);
                    });
                    addText();
                });
        RxView.clicks(buttons.get(3)).throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    addText();
                });
    }

    private void addText() {
        text_db_content.setText("");
        List<SysUserResponseVo> users = AppManagers.getDbManagers().queryUser();
        Observable.from(users).subscribe(sysUserResponseVo -> {
            text_db_content.append(sysUserResponseVo.userName + ":" + sysUserResponseVo.deptName
                    + "-" + sysUserResponseVo.postName + "-id-" + sysUserResponseVo.getId() + "\n");
        });
    }

    @Override
    protected void stopWork() {

    }
}
