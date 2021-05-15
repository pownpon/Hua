package com.pownpon.hua.model.bean.request

import com.pownpon.hua.model.bean.base.BaseRequest

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: RqGoodsList
 * Author: HUA
 * Date: 2021/5/10 14:44
 * Description: 物品列表参数
 * History:
 */
data class RqGoodsList(
    var page: Int = 1,
    var pageSize: Int = 20,
    var KeyWord: String? = null
) : BaseRequest(){
    constructor(bean:RqGoodsList):this(page = bean.page,pageSize = bean.pageSize,KeyWord = bean.KeyWord)
}
