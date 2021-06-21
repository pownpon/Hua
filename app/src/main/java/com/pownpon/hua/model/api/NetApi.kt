package com.pownpon.hua.model.api

import com.pownpon.hua.model.bean.base.BaseResponse
import com.pownpon.hua.model.bean.response.GetListAuctionP
import com.pownpon.hua.model.bean.response.LoginByUsername
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.util.*

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
    suspend fun GetListAuctionP(@Query("KeyWord") keyword: String? = null): GetListAuctionP


    /**
     * 获取当前登录状态
     */
    @GET("/Passport/GetUserLoginSta")
    suspend fun GetUserLoginSta(): ResponseBody

    /**
     * 手机登录前的随机码
     */
    @GET("/Passport/GetSendMobieRandomToAPPCode")
    suspend fun GetSendMobieRandomToAPPCode(): ResponseBody

    /**
     * 获取手机登录验证码
     */
    @FormUrlEncoded
    @POST("/Passport/SendLoginMsmCodeAPP")
    suspend fun SendLoginMsmCodeAPP(
        @Field("param") param: String,
        @Field("sign") sign: String
    ): BaseResponse

    /**
     * 手机验证码登录
     */
    @FormUrlEncoded
    @POST("/Passport/LoginMsmCode")
    suspend fun LoginMsmCode(
        @Field("txtphone") phone: String,
        @Field("txtphonecode") code: String
    ): BaseResponse

    /**
     * 用户名密码登录
     */
    @FormUrlEncoded
    @POST("/Passport/Login")
    suspend fun LoginUserName(
        @Field("username") name: String,
        @Field("password") psd: String,
        @Field("code") code: String = ""
    ): LoginByUsername
}