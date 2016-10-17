package com.app.sampleandroidproject.ui.recycleview;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.app.sampleandroidproject.R;
import com.app.sampleandroidproject.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RecycleViewWithDragActivity extends BaseActivity {
    @BindView(R.id.rv_main)
    RecyclerView rv_main;
    private RecycleViewGridAdapter adapter;

    @Override
    protected int getContentResource() {
        return R.layout.activity_recycle_view_with_drag;
    }

    @Override
    protected void startWork(Bundle savedInstanceState) {
        setTittleText("RecycleViewActivity");
        rv_main.setHasFixedSize(true);
        List<String> datas = new ArrayList();
        datas.add("1");
        datas.add("2");
        datas.add("3");
        datas.add("4");
        datas.add("5");
        datas.add("6");
        datas.add("7");
        datas.add("8");
        datas.add("9");
        adapter = new RecycleViewGridAdapter(this, datas);
        rv_main.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        rv_main.setLayoutManager(layoutManager);
        ItemTouchHelper.Callback callback=new ReItemTouchHelper(adapter);
        ItemTouchHelper helper=  new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rv_main);
    }

    @Override
    protected void stopWork() {

    }
}
