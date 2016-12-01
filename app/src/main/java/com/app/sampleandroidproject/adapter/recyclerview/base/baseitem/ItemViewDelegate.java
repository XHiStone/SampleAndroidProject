package com.app.sampleandroidproject.adapter.recyclerview.base.baseitem;


/**
 * @Title: ItemViewDelegate
 * @Description: 多样式Item继承接口
 * @date 2016/11/29 11:32
 * @auther xie
 */
public interface ItemViewDelegate<T> {

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);

}
