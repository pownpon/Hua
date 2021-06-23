package com.pownpon.picture.picker;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: ItemPicutreDecoration
 * Author: HUA
 * Date: 2021/6/22 16:57
 * Description:
 * History:
 */
class ItemPicutreDecoration extends RecyclerView.ItemDecoration {

    private int mColumn;

    ItemPicutreDecoration(int column) {
        this.mColumn = column;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);
        int index = position % mColumn;
        if (index == 0) {
            outRect.set(5, 8, 8, 0);
        } else if (index == mColumn - 1) {
            outRect.set(0, 8, 5, 0);
        }else {
            outRect.set(0, 8, 8, 0);
        }
    }
}
