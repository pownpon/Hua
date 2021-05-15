package com.pownpon.hua.model.bean.base

import com.google.gson.JsonParseException
import org.json.JSONException
import org.json.JSONObject

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: BaseResponse
 * Author: HUA
 * Date: 2021/5/10 14:45
 * Description:
 * History:
 */
open class BaseResponse(val success: Boolean, val errinfo: String?) {

    constructor() : this(true, null)

    /**
     * 获取错误显示信息
     */
    fun getErrInfo(): String? {
        try {
            if (success || errinfo.isNullOrEmpty()) {
                return null
            }
            var jsonObject: JSONObject = JSONObject(errinfo)
            var keys: Iterator<String> = jsonObject.keys()
            while (keys.hasNext()) {
                return@getErrInfo jsonObject.getString(keys.next())
            }
            return null
        } catch (e: JsonParseException) {
            return null
        }
    }
}