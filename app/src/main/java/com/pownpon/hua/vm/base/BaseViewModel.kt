package com.pownpon.hua.vm.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pownpon.hua.model.bean.entity.VmActionResult

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: BaseViewModel
 * Author: HUA
 * Date: 2021/5/10 14:32
 * Description:
 * History:
 */
abstract class BaseViewModel : ViewModel() {

    /**
     * 不带数据的操作结果，可共用
     */
    val actionResult: MutableLiveData<VmActionResult> by lazy { MutableLiveData<VmActionResult>() }

}