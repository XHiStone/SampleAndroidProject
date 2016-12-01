package com.app.sampleandroidproject.ui.multiitem;

import android.content.Context;
import android.view.View;

import com.app.sampleandroidproject.adapter.listview.base.MultiItemTypeAdapter;
import com.app.sampleandroidproject.adapter.listview.base.baseItem.ViewHolder;
import com.app.sampleandroidproject.ui.multiitem.bean.ChatMessage;

import java.util.List;

/**
 * @Title: ChatAdapter
 * @Description: 多样式Adaoter
 * @date 2016/11/29 14:06
 * @auther xie
 */
public class ChatAdapter extends MultiItemTypeAdapter<ChatMessage> {

    public ChatAdapter(Context context, List<ChatMessage> datas) {
        super(context, datas);
        addItemViewDelegate(new MsgSendItemDelagate());
        addItemViewDelegate(new MsgComingItemDelagate());
    }

    @Override
    public void onViewHolderCreated(ViewHolder holder, View itemView) {
        super.onViewHolderCreated(holder, itemView);

    }


}
