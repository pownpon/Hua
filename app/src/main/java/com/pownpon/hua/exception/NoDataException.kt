package com.pownpon.hua.exception

import java.lang.Exception

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: NoDataException
 * Author: HUA
 * Date: 2021/5/8 10:43
 * Description: 用于在没有数据的时候抛出，让PagingDataAdapter 可以显示没有到底啦
 * History:
 */
class NoDataException(message: String?) : Exception(message) {

    constructor() : this(null)

}