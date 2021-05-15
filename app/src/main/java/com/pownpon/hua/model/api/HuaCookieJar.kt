package com.pownpon.hua.model.api

import com.google.gson.Gson
import com.pownpon.hua.global.Sp
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import java.util.HashSet

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: HuaCookieJar
 * Author: HUA
 * Date: 2021/5/15 16:22
 * Description: http链接的cookie设置
 * History:
 */
class HuaCookieJar : CookieJar {

    companion object {
        var appcookis: MutableList<Cookie>? = null
    }

    private val gson: Gson by lazy { Gson() }

    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
        //赋值
        appcookis = cookies
        //转换实体类
        var saveCookieStrings: HashSet<String> = HashSet<String>()
        for (cookie in cookies) {
            var itemString = gson.toJson(HuaCookie(cookie))
            saveCookieStrings.add(itemString)
        }
        //存储到本地，覆盖存储
        Sp.putStringSet(Sp.Sp_KeyCookie, saveCookieStrings)
    }

    override fun loadForRequest(url: HttpUrl): MutableList<Cookie> {
        if (null == appcookis) {
            //取出保存的cookie
            var saveCookieStrings: MutableSet<String>? = Sp.getStringSet(Sp.Sp_KeyCookie)
            appcookis = if (null == saveCookieStrings || saveCookieStrings.size <= 0) {
                mutableListOf()
            } else {
                var cookies = ArrayList<Cookie>()
                for (cookieString in saveCookieStrings) {
                    var huaCookie: HuaCookie = gson.fromJson(cookieString, HuaCookie::class.java)
                    cookies.add(huaCookie.toCookie())
                }
                //赋值返回
                cookies
            }
        }
        return appcookis!!

    }
}