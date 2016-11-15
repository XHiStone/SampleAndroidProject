package com.app.sampleandroidproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.sampleandroidproject.R;
import com.app.sampleandroidproject.app.AppManagers;
import com.app.sampleandroidproject.beans.result.HttpResultCityAndSpace;
import com.app.sampleandroidproject.http.HttpRequest;
import com.app.sampleandroidproject.ui.base.BaseActivity;
import com.app.sampleandroidproject.ui.recycleview.RecycleViewWithDragActivity;
import com.app.sampleandroidproject.ui.rxexample.RxDaoActivity;
import com.jakewharton.rxbinding.widget.RxAdapterView;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {

    @BindView(R.id.listView)
    ListView listView;

    private String[] names;

    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void startWork(Bundle savedInstanceState) {
        setTittleText("首页");
        initData();
    }

    @Override
    protected void stopWork() {

    }

    private void initData() {
        AppManagers.getHttpManager().getCity(this, false, new HttpRequest<HttpResultCityAndSpace>() {
            @Override
            public void onHttpStart() {

            }

            @Override
            public void onHttpSuccess(HttpResultCityAndSpace httpResultCityAndSpace) {
                Log.i("tag"," httpResultCityAndSpace.getCity().size()--"+ httpResultCityAndSpace.getCity().get(0).getName());
            }

            @Override
            public void onHttpFinish() {

            }

            @Override
            public void onHttpError() {

            }
        });
        names = new String[]{
                "DaggerActivity", "MVPActivity", "GreenDaoActivity", "RxDaoActivity","recycleViewWithDragActivity"
        };
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_expandable_list_item_1, Arrays.asList(names));
        listView.setAdapter(adapter);
        listView.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        RxAdapterView.itemClicks(listView).throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        switch (integer) {
                            case 0:
                                startActivity(new Intent(MainActivity.this, DaggerActivity.class));
                                break;
                            case 1:
                                startActivity(new Intent(MainActivity.this, MVPActivity.class));
                                break;
                            case 2:
                                startActivity(new Intent(MainActivity.this, GreenDaoActivity.class));
                                break;
                            case 3:
                                startActivity(new Intent(MainActivity.this, RxDaoActivity.class));
                                break;
                            case 4:
                                startActivity(new Intent(MainActivity.this, RecycleViewWithDragActivity.class));
                                break;
                            default:
                                break;
                        }
                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            this.startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManagers.getToastor().showSingletonToast("Main");
    }
}
