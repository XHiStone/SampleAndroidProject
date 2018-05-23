package com.app.sampleandroidproject.ui.multiitem;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.app.sampleandroidproject.R;
import com.app.sampleandroidproject.adapter.recyclerview.base.baseitem.ItemViewDelegate;
import com.app.sampleandroidproject.adapter.recyclerview.base.baseitem.ViewHolder;
import com.app.sampleandroidproject.ui.multiitem.bean.MyMessageItem;

/**
 * SampleAndroidProject
 * com.app.sampleandroidproject.ui.multiitem
 *
 * @Author: xie
 * @Time: 2017/4/24 10:54
 * @Description:
 */


public class ItemDateDelegate implements ItemViewDelegate<MyMessageItem> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_message_date;
    }

    @Override
    public boolean isForViewType(MyMessageItem item, int position) {
        return item.isDate();
    }

    @Override
    public void convert(ViewHolder holder, MyMessageItem myMessageInfo, int position) {
        TextView date = holder.getView(R.id.tv_message_date);
        if (!TextUtils.isEmpty(myMessageInfo.getCreatetime())){
            date.setVisibility(View.VISIBLE);
            date.setText(myMessageInfo.getCreatetime());
        }else {
            date.setVisibility(View.GONE);
        }

    }
}
