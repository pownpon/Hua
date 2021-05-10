package com.pownpon.hua.api

import retrofit2.http.GET

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: NetApi
 * Author: HUA
 * Date: 2021/5/10 10:01
 * Description: 网络数据接口
 * History:
 */
interface NetApi {

    @GET("")
    suspend fun GetListAuctionP()
}