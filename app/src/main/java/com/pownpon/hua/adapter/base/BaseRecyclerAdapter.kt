package com.pownpon.hua.adapter.base

import android.content.Context
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: BaseRecyclerAdapter
 * Author: HUA
 * Date: 2021/5/4 17:34
 * Description: RecyclerView适配器基类
 * History:
 */
abstract class BaseRecyclerAdapter<T, VDB : ViewDataBinding>(
    private val mContext: Context,
    private val mLayoutResId: Int,
    private val mHeadView: View?,

    ) :
    RecyclerView.Adapter<BaseViewHolder<VDB>>(){
//
//    companion object {
//        private const val Type_Head = 1
//        private const val Type_Content = 2
//        private const val Type_Foot = 3
//    }
//
//    override var mIsNoData = false
//    override var mIsNoMoreData = false
//
//    /**
//     * 是否有头部布局文件
//     */
//    private var mHasHead = false
//
//    /**
//     * 数据集合
//     */
//    private val mList = ArrayList<T>()
//
//    init {
//        mHasHead = null != mHeadView
//    }
//
//    final override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): BaseViewHolder {
//        if (viewType == Type_Head) {
//            return BaseViewHolder(mHeadView!!)
//        }
//        if (viewType == Type_Foot) {
//            return BaseViewHolder(mContext, R.layout.layout_foot_viewholder)
//        }
//        return BaseViewHolder(mContext, mLayoutResId)
//    }
//
//    final override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
//        //头部
//        if (mHasHead && position == 0) {
//            return
//        }
//        var dataBinding: ViewDataBinding? = holder.mVDB ?: return
//
//        //最后布局
//        if (dataBinding is LayoutFootViewholderBinding) {
//            dataBinding.isNoData = mIsNoData
//            dataBinding.isNoMoreData = mIsNoMoreData
//            return
//        }
//
//        var index = if (mHasHead) position - 1 else position
//        //强制转换
//        var targetBinding = dataBinding as VDB
//        handItemViewHolder(dataBinding, mList[index], index)
//    }
//
//
//    final override fun getItemViewType(position: Int): Int {
//        if (mHasHead && 0 == position) {
//            return Type_Head
//        }
//
//        if ((mHasHead && position == mList.size + 1) || (!mHasHead && position == mList.size)) {
//            return Type_Foot
//        }
//
//        return Type_Content
//    }
//
//    final override fun getItemCount(): Int {
//        return if (mHasHead) {
//            mList.size + 2
//        } else {
//            mList.size + 1
//        }
//    }
//
//
//    /*           ————————————————————————————————分割分割——————————————————————————————————————————               */
//
//    final override fun refreshData(dataList: List<T>) {
//        mList.clear()
//        mList.addAll(dataList)
//        mIsNoData = dataList.isNullOrEmpty()
//        mIsNoMoreData = false
//        notifyDataSetChanged()
//    }
//
//    final override fun addData(data: T) {
//        if (null == data) {
//            return
//        }
//        mList.add(data)
//        notifyItemInserted(if (mHasHead) mList.size else mList.size - 1)
//
//        changFoot(isNoData = false, isNoMoreData = false)
//    }
//
//    final override fun addData(dataList: List<T>) {
//        if (dataList.isNullOrEmpty()) {
//            return
//        }
//        mList.addAll(dataList)
//        notifyItemRangeInserted(
//            if (mHasHead) mList.size - dataList.size + 1 else mList.size - dataList.size,
//            dataList.size
//        )
//
//        changFoot(isNoData = false, isNoMoreData = false)
//    }
//
//    final override fun clearData() {
//        mList.clear()
//        mIsNoData = true
//        mIsNoMoreData = false
//        notifyDataSetChanged()
//    }
//
//    final override fun updateItem(position: Int, data: T) {
//        if (position < 0 || position >= mList.size) {
//            return
//        }
//        mList[position] = data
//        notifyItemChanged(if (mHasHead) position + 1 else position)
//    }
//
//    final override fun removeItem(data: T) {
//        if (null == data) {
//            return
//        }
//        var index = mList.indexOf(data)
//        if (index < 0) {
//            return
//        }
//        var result = mList.remove(data)
//        if (result) {
//            notifyItemRemoved(if (mHasHead) index + 1 else index)
//        }
//
//        changFoot(isNoData = mList.isNullOrEmpty(), isNoMoreData = false)
//    }
//
//    final override fun removeItem(position: Int) {
//        if (position < 0 || position >= mList.size) {
//            return
//        }
//        var data = mList.removeAt(position)
//        notifyItemRemoved(if (mHasHead) position + 1 else position)
//
//        changFoot(isNoData = mList.isNullOrEmpty(), isNoMoreData = false)
//    }
//
//    final override fun noData() {
//        changFoot(isNoData = true, isNoMoreData = false)
//    }
//
//    final override fun noMoreData() {
//        changFoot(isNoData = false, isNoMoreData = true)
//    }
//
//    /**
//     * 改变底部的显示
//     */
//    private fun changFoot(isNoData: Boolean, isNoMoreData: Boolean) {
//        mIsNoData = false
//        mIsNoMoreData = true
//        notifyItemChanged(if (mHasHead) mList.size + 1 else mList.size)
//    }
//
//    /**
//     * 子类处理每个item
//     */
//    abstract fun handItemViewHolder(vdb: VDB, bean: T, position: Int)

}