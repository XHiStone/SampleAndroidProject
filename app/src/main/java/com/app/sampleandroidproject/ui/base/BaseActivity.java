package com.app.sampleandroidproject.ui.base;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.sampleandroidproject.R;
import com.app.sampleandroidproject.app.AppManagers;
import com.app.sampleandroidproject.app.RequestManager;
import com.app.sampleandroidproject.event.FEvent;
import com.app.sampleandroidproject.event.StopEvent;
import com.app.sampleandroidproject.utils.BusProvider;
import com.app.sampleandroidproject.utils.Toastor;
import com.facebook.drawee.view.SimpleDraweeView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.List;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * SampleAndroidProject
 * com.app.sampleandroidproject.ui.base
 *
 * @Author: xie
 * @Time: 2016/9/2 9:48
 * @Description:
 */
public abstract class BaseActivity extends AppCompatActivity implements RequestManager.OnRequestListener{
    private CompositeSubscription subscriptions = new CompositeSubscription();
    private TextView tittle_text;
    private FragmentManager fragmentManager;
    private ProgressDialog progressDialog;
    private SystemBarTintManager tintManager;
    private ViewStub stub;
    private ImageView leftImg, rightImg, titleImg;
    private SimpleDraweeView titleHead;
    private TextView leftTv, rightTv;
    public Toastor toastor;
    private FrameLayout content;
    private View.OnClickListener leftClick;

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
        BusProvider.register(this);
        toastor = AppManagers.getToastor();
        AppManagers.getRequestManager().addOnRequestListener(this);
        startWork(savedInstanceState);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus&&Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            View mDecorView = getWindow().getDecorView();
            mDecorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    |View.SYSTEM_UI_FLAG_FULLSCREEN

            );
        }
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
        AppManagers.getRequestManager().removeOnRequestListener(this);
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null && fragments.size() != 0) {
            for (Fragment fragment : fragments) fragment = null;
        }
        subscriptions.clear();
        AppManagers.getActivitiesManager().removeActivity(this);
        stopWork();
        BusProvider.unregister(this);
        super.onDestroy();
    }

    private void getContentView() {
        content = (FrameLayout) findViewById(R.id.content_frame);
        content.addView(LayoutInflater.from(this).inflate(getContentResource(), null));
    }

    private void setTranslucentStatus() {
        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
        localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
    }

    private void headerInit() {
        stub = ((ViewStub) findViewById(R.id.vs_title));
        stub.inflate();
        tittle_text = (TextView) findViewById(R.id.tittle_text);
    }

    protected void setTittleText(int resId) {
        setTittleText(getString(resId));
    }

    protected void setTittleText(String title) {
        if (tittle_text == null)
            headerInit();
        if (title == null) {
            stub.setVisibility(View.GONE);
            if (tintManager == null)
                tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.colorPrimary);
        } else {
            if (tintManager != null)
                tintManager.setStatusBarTintEnabled(false);
            stub.setVisibility(View.VISIBLE);
            tittle_text.setText(title);
        }
    }

    protected void setLeftView(int visibility) {
        if (leftImg == null) {
            leftImg = (ImageView) findViewById(R.id.tittle_logo_img);
            leftTv = (TextView) findViewById(R.id.tittle_huanxin_text);
        }
        if (View.VISIBLE == visibility) {
            leftImg.setImageResource(R.mipmap.back);
            leftTv.setText(R.string.title_back);
        } else {
            leftImg.setVisibility(visibility);
            leftTv.setVisibility(visibility);
        }
        leftClick = v -> onBackPressed();
        leftTv.setOnClickListener(leftClick);
        leftImg.setOnClickListener(leftClick);
    }

    public View.OnClickListener getLeftClick() {
        return leftClick;
    }

    public void setTitleHead(int visibility, String url) {
        if (titleHead == null)
            titleHead = (SimpleDraweeView) findViewById(R.id.sdv_title_head);
        titleHead.setVisibility(visibility);
        titleHead.setImageURI(url);
    }

    public void setTitleImg(int visibility, int id, View.OnClickListener onClickListener) {
        if (titleImg == null)
            titleImg = (ImageView) findViewById(R.id.img_title_right);
        titleImg.setVisibility(View.VISIBLE);
        titleImg.setImageResource(id);
        titleImg.setOnClickListener(onClickListener);
    }

    private void initRightView() {
        rightImg = (ImageView) findViewById(R.id.tittle_img_drawer);
        rightTv = (TextView) findViewById(R.id.tittle_login_text);
    }

    protected void setRightView(int rightId, String name, View.OnClickListener onClickListener) {
        if (rightImg == null) {
            initRightView();
        }
        rightImg.setImageResource(rightId);
        rightImg.setOnClickListener(onClickListener);
        rightTv.setText(name);
        rightTv.setOnClickListener(onClickListener);
    }

    protected View getRightView() {
        if (rightImg == null) {
            initRightView();
        }
        return rightImg;
    }

    public void htttpRequest(Subscription subscription) {
        if (subscription != null) {
            subscriptions.add(subscription);
            showProgressDialog();
        }
    }

    protected void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage(getString(R.string.process_dialog_message));
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        } else {
            if (progressDialog.isShowing())
                progressDialog.cancel();
            progressDialog.show();
        }
    }

    protected void dissmissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void onStop(StopEvent e) {
        dissmissProgressDialog();
    }

    @Override
    public void onError(FEvent e) {
        dissmissProgressDialog();
    }

    public void onErrorPage(ViewGroup view) {
        view.removeAllViews();
        View v = LayoutInflater.from(this).inflate(R.layout.layout_error, null);
        view.addView(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.rl_error);
        layout.setVisibility(View.VISIBLE);
    }

    public void onEmptyPage(ViewGroup view) {
        view.removeAllViews();
        View v = LayoutInflater.from(this).inflate(R.layout.layout_empty, null);
        view.addView(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.rl_empty);
        layout.setVisibility(View.VISIBLE);
    }
}
