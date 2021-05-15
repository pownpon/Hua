package com.pownpon.hua.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pownpon.hua.R
import com.pownpon.hua.global.App
import com.pownpon.hua.model.api.ApiService
import com.pownpon.hua.model.bean.entity.VmActionResult
import com.pownpon.hua.vm.base.BaseViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: VmLogin
 * Author: HUA
 * Date: 2021/5/14 17:07
 * Description: 登录相关
 * History:
 */
class VmLogin : BaseViewModel() {

    companion object {
        /**
         * 获取手机验证码的标志
         */
        const val Tag_GetPhoneCode = 1
    }

    fun startLoginByPhone(phoneNumb: String) {
        viewModelScope.launch {
            try {
                var randCode = ApiService.api.GetSendMobieRandomToAPPCode()
                if (randCode.isNullOrEmpty()) {

                    actionResult.value = VmActionResult(Tag_GetPhoneCode, false, "获取失败")
                }else{
                    //获取随机码成功，进行下一步

                }
            } catch (e: HttpException) {
                actionResult.value =
                    VmActionResult(Tag_GetPhoneCode, false, App.getResString(R.string.http_err))
            }
        }
    }


}