package com.pownpon.hua.adapter.base

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import com.pownpon.hua.adapter.DiffCallBack.DiffCallBackEntitiy
import com.pownpon.hua.bean.base.BaseEntity

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: BasePageDataAdapter
 * Author: HUA
 * Date: 2021/5/6 17:06
 * Description:
 * History:
 */
abstract class BasePageDataAdapter<T : BaseEntity, VDB : ViewDataBinding>(
    private val context: Context,
    private val layoutResId: Int
) : PagingDataAdapter<T, BaseViewHolder<VDB>>(DiffCallBackEntitiy<T>()) {

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VDB> {
        return BaseViewHolder<VDB>(context, layoutResId,parent)
    }

}