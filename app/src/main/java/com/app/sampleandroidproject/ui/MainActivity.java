package com.app.sampleandroidproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.sampleandroidproject.R;
import com.app.sampleandroidproject.ui.base.BaseActivity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.Arrays;

@EActivity
public class MainActivity extends BaseActivity {

    @ViewById
    ListView listView;

    private Intent intent;
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

    private void initData() {
        names = new String[]{
                "MVP"
        };
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_expandable_list_item_1, Arrays.asList(names));
        listView.setAdapter(adapter);
    }

    @ItemClick({R.id.listView})
    public void ItemClick(int position) {
        switch (position) {
            case 0:
                intent = new Intent(MainActivity.this, MVPActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }


}
