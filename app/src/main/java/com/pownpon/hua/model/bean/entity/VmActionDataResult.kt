package com.pownpon.hua.model.bean.entity

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: VmActionDataResult
 * Author: HUA
 * Date: 2021/5/14 17:46
 * Description: VM的动作结果，带数据
 * History:
 */
class VmActionDataResult<T>(tag: Int, success: Boolean, errInfo: String?, val data: T? = null) :
    VmActionResult(tag, success, errInfo)