package com.app.sampleandroidproject.ui.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.sampleandroidproject.R;

import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 */

public class RecycleViewGridAdapter extends RecyclerView.Adapter<RecycleViewGridAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> mList;

    public RecycleViewGridAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public List<String> getList() {
        return mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_simple_text, parent, false);
        MyViewHolder itemViewHolder = new MyViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.btn.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        Button btn;

        public MyViewHolder(View itemView) {
            super(itemView);
            btn = (Button) itemView.findViewById(R.id.btn);
        }


    }
}
