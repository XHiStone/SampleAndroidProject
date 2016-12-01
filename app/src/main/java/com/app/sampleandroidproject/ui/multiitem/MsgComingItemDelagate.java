package com.app.sampleandroidproject.ui.multiitem;


import com.app.sampleandroidproject.R;
import com.app.sampleandroidproject.adapter.listview.base.baseItem.ItemViewDelegate;
import com.app.sampleandroidproject.adapter.listview.base.baseItem.ViewHolder;
import com.app.sampleandroidproject.app.AppManagers;
import com.app.sampleandroidproject.ui.multiitem.bean.ChatMessage;

/**
 * @Title: MsgComingItemDelagate
 * @Description: 描述
 * @date 2016/11/29 13:57
 * @auther xie
 */
public class MsgComingItemDelagate implements ItemViewDelegate<ChatMessage> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.main_chat_from_msg;
    }

    @Override
    public boolean isForViewType(ChatMessage item, int position) {
        return item.isComMeg();
    }

    @Override
    public void convert(ViewHolder holder, ChatMessage chatMessage, int position) {
        holder.setText(R.id.chat_from_content, chatMessage.getContent());
        holder.setText(R.id.chat_from_name, chatMessage.getName());
        holder.setImageResource(R.id.chat_from_icon, chatMessage.getIcon());
        holder.setOnClickListener(R.id.chat_from_icon,
                v -> AppManagers.getToastor().showSingleLongToast("--from--"+position));
    }
}
