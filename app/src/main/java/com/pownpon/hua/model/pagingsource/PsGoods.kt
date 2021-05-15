package com.pownpon.hua.model.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pownpon.hua.model.api.ApiService
import com.pownpon.hua.model.bean.entity.Goods
import com.pownpon.hua.model.bean.request.RqGoodsList
import com.pownpon.hua.model.bean.response.GetListAuctionP
import com.pownpon.hua.exception.NoDataException
import retrofit2.HttpException
import java.io.IOException

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: PsGoods
 * Author: HUA
 * Date: 2021/5/10 15:03
 * Description:
 * History:
 */

/**
 * 每次刷新此对象会重新创建
 */
class PsGoods(private val rqGoodsList: RqGoodsList) : PagingSource<Int, Goods>() {

    override fun getRefreshKey(state: PagingState<Int, Goods>): Int? {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Goods> {
        return try {
            var page = params.key ?: 1
            rqGoodsList.page = page
            rqGoodsList.pageSize = params.loadSize

            var getListAuctionP: GetListAuctionP =
                ApiService.api.GetListAuctionP(rqGoodsList.KeyWord)
            if (getListAuctionP.ListAuctionP.isNullOrEmpty()) {
                LoadResult.Error(NoDataException())
            } else {
                LoadResult.Page(
                    data = getListAuctionP.ListAuctionP ?: ArrayList<Goods>(),
                    prevKey = null,
                    nextKey = page + 1
                )
            }
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

    }


}