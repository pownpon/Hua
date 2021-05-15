package com.pownpon.hua.`interface`

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: HttpCallBack
 * Author: HUA
 * Date: 2021/5/14 17:24
 * Description: 网络请求回调接口
 * History:
 */
interface HttpCallBack {

    fun onResult(success:Boolean,errInfo:String?)
}