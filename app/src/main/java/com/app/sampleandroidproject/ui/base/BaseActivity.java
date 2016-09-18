package com.app.sampleandroidproject.ui.base;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.app.sampleandroidproject.R;
import com.app.sampleandroidproject.app.AppManagers;
import com.app.sampleandroidproject.http.HttpManager;

import java.util.List;

import butterknife.ButterKnife;
import rx.Subscription;

/**
 * SampleAndroidProject
 * com.app.sampleandroidproject.ui.base
 *
 * @Author: xie
 * @Time: 2016/9/2 9:48
 * @Description:
 */
public abstract class BaseActivity extends AppCompatActivity {

    private TextView tittle_text;
    private HttpManager httpManager;
    private FragmentManager fragmentManager;
    protected Subscription subscription;

    protected abstract int getContentResource();

    protected abstract void startWork(Bundle savedInstanceState);

    protected abstract void stopWork();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        fragmentManager = getSupportFragmentManager();
        AppManagers.getActivitiesManager().addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_base);
        getContentView();
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus();
        }
        startWork(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager == null) {
            AppManagers.getActivitiesManager().finishActivity();
        } else {
            if (fragmentManager.getBackStackEntryCount() == 0) {
                AppManagers.getActivitiesManager().finishActivity();
            } else {
                fragmentManager.popBackStack();
            }
        }
        super.onBackPressed();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null && fragments.size() != 0) {
            for (Fragment fragment : fragments) fragment = null;
        }
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        AppManagers.getActivitiesManager().removeActivity(this);
        stopWork();
        super.onDestroy();
    }

    private void getContentView() {
        FrameLayout content = (FrameLayout) findViewById(R.id.content_frame);
        content.addView(LayoutInflater.from(this).inflate(getContentResource(), null));
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

    private void headerInit() {
        ViewStub stub = ((ViewStub) findViewById(R.id.vs_title));
        stub.inflate();
        tittle_text = (TextView) findViewById(R.id.tittle_text);
    }

    public void setTittleText(String title) {
        headerInit();
        tittle_text.setText(title);
    }

    public HttpManager htttpRequest() {
        if (httpManager == null) {
            httpManager = AppManagers.getHttpManager();
        }
        return httpManager;
    }

    private ProgressDialog progressDialog;

    protected void showProgressDialog(String message) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(message);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    protected void dissmissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
