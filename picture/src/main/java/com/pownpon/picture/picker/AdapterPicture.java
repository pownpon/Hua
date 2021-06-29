package com.pownpon.picture.picker;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
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
    private PictureLoader mPicLoader;

    AdapterPicture(Context context) {
        this.mContext = context;
        mPicLoader = new PictureLoader(context);
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
        return new PicureViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_picture, parent, false));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PicureViewHolder pvh = (PicureViewHolder) holder;
        pvh.ivPicItemPicture.setImageResource(R.drawable.ic_placeholder);
        if (pvh.loading && null != pvh.pathUrl) {
            mPicLoader.cancel(pvh.pathUrl);
        }
        String path = mList.get(position).getPicturePath();
        Log.e("adapterBitmap", "onbind___" + path);
        pvh.pathUrl = path;
        pvh.loading = true;
        mPicLoader.get(mList.get(position), pvh);
    }

    private class PicureViewHolder extends RecyclerView.ViewHolder implements PictureLoader.PicCallBack {

        ImageView ivPicItemPicture;
        String pathUrl;
        boolean loading;

        public PicureViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPicItemPicture = itemView.findViewById(R.id.iv_pic_item_pictreu);
        }

        @Override
        public void onReuslt(boolean success, String path, Bitmap bitmap) {
            loading = false;
            if (success && null != path && path.equals(pathUrl)) {
                if (null == bitmap) {
                    ivPicItemPicture.setImageResource(R.drawable.ic_placeholder);
                } else {
                    Log.e("adapterBitmap", bitmap.getByteCount() + "___" + path);
                    ivPicItemPicture.setImageBitmap(bitmap);
                }
            }
        }
    }
}
