package com.pownpon.picture.picker;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pownpon.picture.R;
import com.pownpon.picture.util.PictureLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: AdapterPicture
 * Author: HUA
 * Date: 2021/6/22 17:07
 * Description:
 * History:
 */
class AdapterPicture extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<BeanPicture> mList = new ArrayList<>();

    AdapterPicture(Context context){
        this.mContext = context;
    }

    /**
     * 刷新数据
     * @param datas
     */
    void refresh(List<BeanPicture> datas){
        mList.clear();
        mList.addAll(datas);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PicureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PicureViewHolder(View.inflate(mContext, R.layout.item_picture,null));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PicureViewHolder pvh= (PicureViewHolder) holder;
        pvh.ivPicItemPicture.setImageBitmap(PictureLoader.getInstance(mContext).get(mList.get(position).getPicturePath()));
    }

    private class PicureViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPicItemPicture;

        public PicureViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPicItemPicture = itemView.findViewById(R.id.iv_pic_item_pictreu);
        }
    }
}
