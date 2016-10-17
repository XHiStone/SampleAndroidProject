package com.app.sampleandroidproject.ui.recycleview;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.Collections;

/**
 * Created by Administrator on 2016/10/14.
 */

public class ReItemTouchHelper extends ItemTouchHelper.Callback {
    private RecycleViewGridAdapter adapter;

    public ReItemTouchHelper(RecycleViewGridAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //此处应判断是grid还是liner,暂时配合demo只考虑一种情况
        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        final int swipeFlags = 0;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosi = viewHolder.getAdapterPosition();
        int toPosi = target.getAdapterPosition();
        Collections.swap(adapter.getList(), fromPosi, toPosi);
        adapter.notifyItemMoved(fromPosi, toPosi);
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
