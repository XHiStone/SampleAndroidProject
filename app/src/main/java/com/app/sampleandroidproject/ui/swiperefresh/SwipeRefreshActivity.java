package com.app.sampleandroidproject.ui.swiperefresh;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.app.sampleandroidproject.R;
import com.app.sampleandroidproject.adapter.recyclerview.base.CommonAdapter;
import com.app.sampleandroidproject.adapter.recyclerview.base.baseitem.ViewHolder;
import com.app.sampleandroidproject.ui.base.BaseActivity;
import com.app.sampleandroidproject.ui.swiperefresh.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SwipeRefreshActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.activity_swipe_refresh)
    LinearLayout activitySwipeRefresh;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private List<String> list = new ArrayList<>();

    @Override
    protected int getContentResource() {
        return R.layout.activity_swipe_refresh;
    }

    @Override
    protected void startWork(Bundle savedInstanceState) {
        setTittleText("Swipe");
        for (int i = 0; i < 10; i++) {
            list.add("RecyclerTest" + i);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        recyclerView.setAdapter(new CommonAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, list) {
            @Override
            protected void convert(ViewHolder holder, String item, int position) {
                holder.setText(android.R.id.text1, item);
            }
        });

    }

    @Override
    protected void stopWork() {

    }
}
