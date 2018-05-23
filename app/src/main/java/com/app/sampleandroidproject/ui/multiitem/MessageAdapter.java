package com.app.sampleandroidproject.ui.multiitem;

import android.content.Context;

import com.app.sampleandroidproject.adapter.recyclerview.base.MultiItemTypeAdapter;
import com.app.sampleandroidproject.ui.multiitem.bean.MyMessageItem;

import java.util.List;

/**
 * SampleAndroidProject
 * com.app.sampleandroidproject.ui.multiitem
 *
 * @Author: xie
 * @Time: 2017/4/24 10:15
 * @Description:
 */


public class MessageAdapter extends MultiItemTypeAdapter<MyMessageItem> {

    public MessageAdapter(Context context, List<MyMessageItem> datas) {
        super(context, datas);
        addItemViewDelegate(new ItemDateDelegate());
        addItemViewDelegate(new ItemMessageDelegate());
    }
}
