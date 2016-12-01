package com.app.sampleandroidproject.adapter.listview.base;

import android.content.Context;

import com.app.sampleandroidproject.adapter.listview.base.baseItem.ItemViewDelegate;
import com.app.sampleandroidproject.adapter.listview.base.baseItem.ViewHolder;

import java.util.List;

/**
 * @Title: CommonAdapter
 * @Description: 通用单一类型Item适配器
 * @date 2016/11/29 11:26
 * @auther xie
 */
public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T> {

    public CommonAdapter(Context context, final int layoutId, List<T> datas) {
        super(context, datas);

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T item, int position) {
                CommonAdapter.this.convert(holder, item, position);
            }
        });
    }

    protected abstract void convert(ViewHolder holder, T item, int position);

}
