package com.app.sampleandroidproject.adapter.listview.base.baseItem;

/**
 * SampleAndroidProject
 * com.app.sampleandroidproject.adapter.listview.base.baseItem
 *
 * @Author: xie
 * @Time: 2016/11/29 11:19
 * @Description: 多样式继承Item
 */
public interface ItemViewDelegate<T> {
    public abstract int getItemViewLayoutId();

    public abstract boolean isForViewType(T item, int position);

    public abstract void convert(ViewHolder holder, T t, int position);

}
