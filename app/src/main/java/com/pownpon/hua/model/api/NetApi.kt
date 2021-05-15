package com.pownpon.hua.model.api

import com.pownpon.hua.model.bean.base.BaseResponse
import com.pownpon.hua.model.bean.response.GetListAuctionP
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
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

    /**
     * 获取物品列表
     */
    @GET("/Auction/GetListAuctionP")
    suspend fun GetListAuctionP(@Query("KeyWord") keyword: String?=null): GetListAuctionP

    /**
     * 手机登录前的随机码
     */
    @GET("/Passport/GetSendMobieRandomToAPPCode")
    suspend fun GetSendMobieRandomToAPPCode():String?

    /**
     * 获取手机登录验证码
     */
    @POST("/Passport/SendLoginMsmCodeAPP")
    suspend fun SendLoginMsmCodeAPP(@Field("param") param:String,@Field("sign") sign:String):BaseResponse
}