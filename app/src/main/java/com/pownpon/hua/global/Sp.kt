package com.pownpon.hua.global

import android.content.Context
import android.content.SharedPreferences

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: Sp
 * Author: HUA
 * Date: 2021/5/15 17:31
 * Description:
 * History:
 */
object Sp {

    private const val Sp_Name = "huasp"

    const val Sp_KeyCookie = "SpKey_Cookie"

    val sp: SharedPreferences by lazy {
        App.getApp().getSharedPreferences(Sp_Name, Context.MODE_PRIVATE)
    }

    /**
     * 存储字符串集合
     */
    fun putStringSet(key: String, stringSet: Set<String>) {
        sp.edit().putStringSet(key, stringSet).commit()
    }

    /**
     * 获取字符串集合
     */
    fun getStringSet(key: String): MutableSet<String>? {
        return sp.getStringSet(key, setOf())
    }


}