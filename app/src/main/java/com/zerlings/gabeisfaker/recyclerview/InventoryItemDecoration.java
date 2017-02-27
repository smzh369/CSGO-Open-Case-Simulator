package com.zerlings.gabeisfaker.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 令子 on 2017/2/19.
 */

public class InventoryItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public InventoryItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.bottom = space;

    }
}
