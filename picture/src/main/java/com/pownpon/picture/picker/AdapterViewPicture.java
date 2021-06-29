package com.pownpon.picture.picker;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pownpon.picture.PictureView;
import com.pownpon.picture.R;
import com.pownpon.picture.util.PictureLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: AdapterViewPicture
 * Author: HUA
 * Date: 2021/6/25 17:37
 * Description:
 * History:
 */
class AdapterViewPicture extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<BeanPicture> mList = new ArrayList<>();

    AdapterViewPicture(Context context) {
        this.mContext = context;
    }

    /**
     * 刷新数据
     *
     * @param datas
     */
    void refresh(List<BeanPicture> datas) {
        mList.clear();
        mList.addAll(datas);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PicureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PicureViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_view_picture, parent, false));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PicureViewHolder pvh = (PicureViewHolder) holder;
        String path = mList.get(position).getPicturePath();
        pvh.pvPicItemPicture.setPath(path);
    }

    private class PicureViewHolder extends RecyclerView.ViewHolder {

        PictureView pvPicItemPicture;

        public PicureViewHolder(@NonNull View itemView) {
            super(itemView);
            pvPicItemPicture = itemView.findViewById(R.id.pv_pic_item_view_picture);
        }

    }
}
