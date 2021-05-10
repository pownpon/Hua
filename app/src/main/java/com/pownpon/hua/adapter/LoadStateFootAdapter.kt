package com.pownpon.hua.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.pownpon.hua.R
import com.pownpon.hua.adapter.base.BaseViewHolder
import com.pownpon.hua.databinding.LayoutViewholderFootBinding
import com.pownpon.hua.exception.NoDataException

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: LoadStateFootAdapter
 * Author: HUA
 * Date: 2021/5/6 21:32
 * Description:
 * History:
 */
class LoadStateFootAdapter(
    private val context: Context,
    private val block: ()->Unit
) :
    LoadStateAdapter<BaseViewHolder<LayoutViewholderFootBinding>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): BaseViewHolder<LayoutViewholderFootBinding> {
        return BaseViewHolder(context, R.layout.layout_viewholder_foot, parent)
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<LayoutViewholderFootBinding>,
        loadState: LoadState
    ) {
        holder.apply {
            mVDB.loadStatus = when (loadState) {
                is LoadState.Loading -> 0
                is LoadState.NotLoading -> if (loadState.endOfPaginationReached) 1 else 0
                is LoadState.Error -> if (loadState.error is NoDataException) 1 else 2
            }
            mVDB.btnRetryLayoutViewholderFoot.setOnClickListener {
                block()
            }
        }

    }


}