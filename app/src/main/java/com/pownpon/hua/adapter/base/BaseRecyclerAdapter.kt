package com.pownpon.hua.adapter.base

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.pownpon.hua.`interface`.IData

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: BaseRecyclerAdapter
 * Author: HUA
 * Date: 2021/5/4 17:34
 * Description: RecyclerView适配器基类
 * History:
 */
class BaseRecyclerAdapter<T, VDB : ViewDataBinding>(
    private val mContext: Context,
    private val mLayoutResId: Int,
    private val mHeadView: View
) :
    RecyclerView.Adapter<BaseViewHolder<ViewDataBinding>>(), IData<T> {

    companion object {
        const val Type_Head = 1
        const val Type_Content = 2
    }

    /**
     * 是否有头部布局文件
     */
    private var mHasHead = false

    /**
     * 数据集合
     */
    private val mList = ArrayList<T>()

    init {
        mHasHead = null != mHeadView
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewDataBinding> {
        return if (viewType == Type_Head) {
            BaseViewHolder(mHeadView)
        } else {
            BaseViewHolder(mContext, mLayoutResId)
        }

    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewDataBinding>, position: Int) {
        var dataBinding = holder.mVDB
        if (null == dataBinding || dataBinding.javaClass != getType(dataBinding)) {
            return
        }
        var x = ViewDataBinding::class.java
    }

    inline fun <reified X> getType(xxx: X): Class<*> {
        return X::class.java
    }

    override fun getItemViewType(position: Int): Int {
        return if (mHasHead && 0 == position) {
            Type_Head
        } else {
            Type_Content
        }
    }

    override fun getItemCount(): Int {
        return if (mHasHead) {
            mList.size + 1
        } else {
            mList.size
        }
    }


    /*           ————————————————————————————————分割分割——————————————————————————————————————————               */

    override fun refreshData(dataList: List<T>) {
        TODO("Not yet implemented")
    }

    override fun addData(data: T) {
        TODO("Not yet implemented")
    }

    override fun addData(dataList: List<T>) {
        TODO("Not yet implemented")
    }

    override fun clearData() {
        TODO("Not yet implemented")
    }

    override fun updateItem(data: T) {
        TODO("Not yet implemented")
    }

    override fun removeItem(data: T) {
        TODO("Not yet implemented")
    }

    override fun removeItem(position: Int) {
        TODO("Not yet implemented")
    }


}