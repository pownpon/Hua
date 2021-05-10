package com.pownpon.hua.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: ApiService
 * Author: HUA
 * Date: 2021/5/10 11:04
 * Description:
 * History:
 */
object ApiService {

    val api: NetApi by lazy {
        var retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.hmlan.com")
            .addConverterFactory(GsonConverterFactory.create()).build()
        return@lazy retrofit.create(NetApi::class.java)
    }

}