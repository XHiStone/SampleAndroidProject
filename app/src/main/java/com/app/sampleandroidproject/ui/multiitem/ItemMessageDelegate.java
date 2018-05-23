package com.app.sampleandroidproject.ui.multiitem;

import com.app.sampleandroidproject.R;
import com.app.sampleandroidproject.adapter.recyclerview.base.baseitem.ItemViewDelegate;
import com.app.sampleandroidproject.adapter.recyclerview.base.baseitem.ViewHolder;
import com.app.sampleandroidproject.ui.multiitem.bean.MyMessageItem;

/**
 * SampleAndroidProject
 * com.app.sampleandroidproject.ui.multiitem
 *
 * @Author: xie
 * @Time: 2017/4/24 11:10
 * @Description:
 */


public class ItemMessageDelegate implements ItemViewDelegate<MyMessageItem> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_my_message;
    }

    @Override
    public boolean isForViewType(MyMessageItem item, int position) {
        return !item.isDate();
    }

    @Override
    public void convert(ViewHolder holder, MyMessageItem myMessageInfo, int position) {
        holder.setText(R.id.tv_message_title, myMessageInfo.getUSER_NEWS_TITLE());
        holder.setText(R.id.tv_message_content, myMessageInfo.getUSER_NEWS_CONTENT());
    }
}
