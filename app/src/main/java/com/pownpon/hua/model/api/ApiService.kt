package com.pownpon.hua.model.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.CookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

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
            .client(builHttpClient())
            .addConverterFactory(GsonConverterFactory.create()).build()
        return@lazy retrofit.create(NetApi::class.java)
    }

    private fun builHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .cookieJar(HuaCookieJar())
            .connectTimeout(3, TimeUnit.SECONDS)//3秒连接超时
            .build()
    }

}