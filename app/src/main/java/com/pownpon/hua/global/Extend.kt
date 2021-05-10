package com.pownpon.hua.global

import android.util.Log
import kotlin.reflect.KClass

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: Extend
 * Author: HUA
 * Date: 2021/5/7 10:11
 * Description:
 * History:
 */

/**
 * 任意对象的日志扩展函数
 */
fun Any.lc(logContent: String) {
    Log.e(this.javaClass.name, logContent)
}
/**
 * 任意对象的日志扩展函数
 */
fun Any.lc() {
    Log.e(this.javaClass.name, "${this.hashCode()}___${System.currentTimeMillis()}")
}



