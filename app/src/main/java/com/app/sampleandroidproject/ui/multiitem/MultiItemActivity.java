package com.app.sampleandroidproject.ui.multiitem;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.app.sampleandroidproject.R;
import com.app.sampleandroidproject.ui.base.BaseActivity;
import com.app.sampleandroidproject.ui.multiitem.bean.ChatMessage;

import butterknife.BindView;

/**
 * @Title: MultiItemActivity
 * @Description: 多样式Item
 * @date 2016/11/29 13:57
 * @auther xie
 */
public class MultiItemActivity extends BaseActivity {

    @BindView(R.id.lv_list)
    ListView lvList;
    @BindView(R.id.rl_empty)
    RelativeLayout rlEmpty;

    @Override
    protected int getContentResource() {
        return R.layout.activity_multi_item;
    }

    @Override
    protected void startWork(Bundle savedInstanceState) {
        setTittleText("Multi");
        lvList.setDivider(null);
        ChatAdapter adapter = new ChatAdapter(this, ChatMessage.MOCK_DATAS);
        lvList.setAdapter(adapter);
    }

    @Override
    protected void stopWork() {

    }

}
