package com.app.sampleandroidproject.ui.multiitem;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.app.sampleandroidproject.R;
import com.app.sampleandroidproject.ui.base.BaseActivity;
import com.app.sampleandroidproject.ui.multiitem.bean.MyMessageInfo;
import com.app.sampleandroidproject.ui.multiitem.bean.MyMessageItem;
import com.app.sampleandroidproject.ui.multiitem.wrapper.LoadmoreWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MultiRecycleActivity extends BaseActivity {


    @BindView(R.id.swipe_target)
    RecyclerView swipeTarget;
    private LoadmoreWrapper mLoadMoreWrapper;
    private List<MyMessageInfo> mDatas = new ArrayList<>();
    private List<MyMessageItem> mList = new ArrayList<>();

    @Override
    protected int getContentResource() {
        return R.layout.activity_multi_recycle;
    }

    @Override
    protected void startWork(Bundle savedInstanceState) {
        setTittleText("消息");
        swipeTarget.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < 10; i++) {
            MyMessageInfo bean = new MyMessageInfo();
            if (i < 5) {
                bean.setCreatetime("2016");
                bean.setUSER_NEWS_TITLE("报名成功");
                bean.setUSER_NEWS_CONTENT("恭喜打反了离开");
            } else {
                bean.setCreatetime("2017");
                bean.setUSER_NEWS_TITLE("签到成功");
                bean.setUSER_NEWS_CONTENT("签到成功赠送100积分");
            }
            mDatas.add(bean);
        }

        for (int i = 0; i < mDatas.size() * 2; i++) {
            MyMessageItem bean = new MyMessageItem();
            bean.setCreatetime(mDatas.get(i / 2).getCreatetime());
            if (i % 2 == 0) {
                bean.setDate(true);
                if (i > 0) {
                    if (bean.getCreatetime().equals(mDatas.get((i - 1) / 2).getCreatetime())) {
                        bean.setCreatetime("");
                    }
                }
            } else {
                bean.setDate(false);
                bean.setUSER_NEWS_TITLE(mDatas.get(i / 2).getUSER_NEWS_TITLE());
                bean.setUSER_NEWS_CONTENT(mDatas.get(i / 2).getUSER_NEWS_CONTENT());
            }
            mList.add(bean);
        }
        MessageAdapter adapter = new MessageAdapter(this, mList);
        mLoadMoreWrapper = new LoadmoreWrapper(adapter);
        mLoadMoreWrapper.setLoadMoreView(LayoutInflater.from(this).inflate(R.layout.default_loading, swipeTarget, false));
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadmoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mLoadMoreWrapper.notifyDataSetChanged();

                    }
                }, 2000);
            }
        });
        swipeTarget.setAdapter(adapter);
    }

    @Override
    protected void stopWork() {

    }

}
