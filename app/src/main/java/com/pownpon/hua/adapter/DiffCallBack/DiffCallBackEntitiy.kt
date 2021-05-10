package com.pownpon.hua.adapter.DiffCallBack

import androidx.recyclerview.widget.DiffUtil
import com.pownpon.hua.model.bean.base.BaseEntity

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: DiffCallBackBean
 * Author: HUA
 * Date: 2021/5/6 17:30
 * Description:
 * History:
 */
class DiffCallBackEntitiy<T : BaseEntity> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}