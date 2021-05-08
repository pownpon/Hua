package com.pownpon.hua.adapter.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
class BaseViewHolder<VDB : ViewDataBinding>(val mVDB: VDB) :

    RecyclerView.ViewHolder(mVDB.root) {

    constructor(
        context: Context,
        layoutResId: Int
    ) : this(context,layoutResId,null)

    constructor(
        context: Context,
        layoutResId: Int,
        parent:ViewGroup?
    ) : this(DataBindingUtil.inflate<VDB>(LayoutInflater.from(context), layoutResId, parent, false))
}