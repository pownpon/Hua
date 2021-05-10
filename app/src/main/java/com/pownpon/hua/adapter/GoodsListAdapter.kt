package com.pownpon.hua.adapter

import android.content.Context
import com.bumptech.glide.Glide
import com.pownpon.hua.R
import com.pownpon.hua.adapter.base.BasePageDataAdapter
import com.pownpon.hua.adapter.base.BaseViewHolder
import com.pownpon.hua.model.bean.entity.Goods
import com.pownpon.hua.databinding.ItemGoodsBinding

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: GoodsListAdapter
 * Author: HUA
 * Date: 2021/5/10 15:31
 * Description:
 * History:
 */
class GoodsListAdapter(context: Context):BasePageDataAdapter<Goods,ItemGoodsBinding>(context, R.layout.item_goods) {

    override fun onBindViewHolder(holder: BaseViewHolder<ItemGoodsBinding>, position: Int) {
        var model = getItem(position)
        holder.apply {
            Glide.with(mVDB.root)
                .load(model?.midSrc)
                .into(mVDB.iv)
            mVDB.model = model
        }
    }
}