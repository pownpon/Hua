package com.pownpon.hua.adapter.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: BaseViewHolder
 * Author: HUA
 * Date: 2021/5/4 17:37
 * Description:
 * History:
 */
class BaseViewHolder<VDB : ViewDataBinding>(itemView:View) : RecyclerView.ViewHolder(itemView) {

    public var mVDB:VDB? = null

    constructor(vdb:VDB):this(vdb.root){
        this.mVDB = vdb
    }
    constructor(
        context: Context,
        layoutResId: Int
    ) : this(DataBindingUtil.inflate<VDB>(LayoutInflater.from(context), layoutResId, null, false))

}