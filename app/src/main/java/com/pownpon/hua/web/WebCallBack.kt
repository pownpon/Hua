package com.pownpon.hua.web

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: WebCallBack
 * Author: HUA
 * Date: 2021/6/15 9:47
 * Description:
 * History:
 */
interface WebCallBack {
    /**
     * 当接收到网页标题
     */
    fun onRecieverTitle(title:String)

    /**
     * 当加载页面进度变化
     */
    fun onLoadProgressChange(progress:Int)

}