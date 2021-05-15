package com.pownpon.hua.model.bean.base

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
open class BaseResponse(val success: Boolean, val errinfo: Map<String, String>?) {

    constructor() : this(true, null)

    /**
     * 获取错误显示信息
     */
    fun showErrInfo(): String? {
        if (success || null == errinfo || errinfo.isEmpty()) {
            return null
        }
        for (item in errinfo) {
            return@showErrInfo item.value
        }
        return null
    }
}