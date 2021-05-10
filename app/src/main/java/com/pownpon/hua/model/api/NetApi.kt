package com.pownpon.hua.model.api

import com.pownpon.hua.model.bean.response.GetListAuctionP
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: NetApi
 * Author: HUA
 * Date: 2021/5/10 10:01
 * Description: 网络数据接口
 * History:
 */
interface NetApi {

    @GET("/Auction/GetListAuctionP")
    suspend fun GetListAuctionP(@Query("KeyWord") keyword: String?=null): GetListAuctionP
}