package com.pownpon.hua.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pownpon.hua.global.lc
import com.pownpon.hua.model.bean.entity.Goods
import com.pownpon.hua.model.bean.request.RqGoodsList
import com.pownpon.hua.model.pagingsource.PsGoods
import com.pownpon.hua.vm.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: VmGoods
 * Author: HUA
 * Date: 2021/5/10 14:32
 * Description:
 * History:
 */
class VmGoods : BaseViewModel() {


    private var rqGoodsList:RqGoodsList = RqGoodsList()

    /**
     * 物品列表参数变化监听
     */
    private val goodsParams: MutableStateFlow<RqGoodsList> =
        MutableStateFlow<RqGoodsList>(rqGoodsList)

    /**
     * 当物品列表参数发生变化时会执行其中的变化
     */
    val goodsFlow: Flow<PagingData<Goods>> = goodsParams.flatMapLatest {

        Pager<Int, Goods>(
            config = PagingConfig(
                pageSize = it.pageSize,
                initialLoadSize = it.pageSize,
                prefetchDistance = 2
            ),
            pagingSourceFactory = { PsGoods(it) })
            .flow
            .cachedIn(viewModelScope)
    }

    /**
     * 改变参数
     */
    fun changParams(key: String) {
//        rqGoodsList.KeyWord = key
//        var x = rqGoodsList.copy()
//        lc("1:${rqGoodsList.hashCode()},2:${x.hashCode()}")
        goodsParams.value = RqGoodsList(KeyWord =  key)
    }

}