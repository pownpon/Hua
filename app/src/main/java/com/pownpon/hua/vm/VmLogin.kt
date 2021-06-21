package com.pownpon.hua.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pownpon.hua.R
import com.pownpon.hua.global.App
import com.pownpon.hua.global.lc
import com.pownpon.hua.model.api.ApiService
import com.pownpon.hua.model.bean.base.BaseResponse
import com.pownpon.hua.model.bean.entity.VmActionDataResult
import com.pownpon.hua.model.bean.entity.VmActionResult
import com.pownpon.hua.model.bean.response.LoginByUsername
import com.pownpon.hua.utils.Security
import com.pownpon.hua.vm.base.BaseViewModel
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.lang.Exception

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

        const val Tag_LoginByPhoneCode = 2

        const val Tag_LoginByName = 3
    }

    val actionDataResult: MutableLiveData<VmActionDataResult<Int>> =
        MutableLiveData<VmActionDataResult<Int>>()

    private var phoneNumb: String = ""

    /**
     * 获取登录状态
     */
    fun getLoginSta() {
        viewModelScope.launch {
            val res: ResponseBody = ApiService.api.GetUserLoginSta()
            lc("loginstatus:${res.string()}")
        }
    }

    /**
     * 用户名那个密码登录
     */
    fun loginByName(name: String, psd: String, code: String?) {
        viewModelScope.launch {
            try {
                var result: LoginByUsername = if (code.isNullOrEmpty()) {
                    ApiService.api.LoginUserName(name, psd)
                } else {
                    ApiService.api.LoginUserName(name, psd, code)
                }
                actionDataResult.postValue(
                    VmActionDataResult(Tag_LoginByName, result.success, result.showErrInfo(),result.LoginErrNum)
                )
            } catch (e: HttpException) {
                actionDataResult.postValue(
                    VmActionDataResult(Tag_LoginByName, false, App.getResString(R.string.http_err),0)
                )
            }

        }
    }


    /**
     * 开始获取手机验证码
     */
    fun startLoginByPhone(phoneNumb: String) {
        viewModelScope.launch {
            try {
                val res: ResponseBody = ApiService.api.GetSendMobieRandomToAPPCode()
                val aesCode: String? = res?.string()

                if (aesCode.isNullOrEmpty()) {
                    actionResult.postValue(VmActionResult(Tag_GetPhoneCode, false, "获取失败"))
                } else {
                    //获取随机码成功，进行下一步
                    val randCode: String = Security.aesDecrypt(aesCode)
                    val appcode: String = phoneNumb + randCode
                    this@VmLogin.phoneNumb = phoneNumb
                    getPhoneCodeByRandom(appcode)
                }
            } catch (e: HttpException) {
                actionResult.postValue(
                    VmActionResult(Tag_GetPhoneCode, false, App.getResString(R.string.http_err))
                )
            }
        }
    }

    /**
     * 正式获取手机验证码
     */
    fun getPhoneCodeByRandom(appcode: String) {
        //AES拼合参数
        val param = Security.aesEncrypt(appcode)
        //MD5拼合参数
        val sign = Security.getMD5(appcode + Security.SENDOBIERANDOMKEY).toLowerCase()
        viewModelScope.launch {
            try {
                val result: BaseResponse = ApiService.api.SendLoginMsmCodeAPP(param, sign)
                actionResult.postValue(
                    VmActionResult(Tag_GetPhoneCode, result.success, result.showErrInfo())
                )
            } catch (e: HttpException) {
                actionResult.postValue(
                    VmActionResult(Tag_GetPhoneCode, false, App.getResString(R.string.http_err))
                )
            }
        }
    }

    /**
     * 手机验证码登录
     */
    fun loginByPhoneCode(code: String) {
        if (phoneNumb.isNullOrEmpty()) {
            actionResult.postValue(
                VmActionResult(Tag_LoginByPhoneCode, false, "请先输入手机号码")
            )
            return
        }
        viewModelScope.launch {
            try {
                val result: BaseResponse = ApiService.api.LoginMsmCode(this@VmLogin.phoneNumb, code)
                if (result.success) {
                    phoneNumb = ""
                }
                actionResult.postValue(
                    VmActionResult(Tag_LoginByPhoneCode, result.success, result.showErrInfo())
                )
            } catch (e: HttpException) {
                actionResult.postValue(
                    VmActionResult(Tag_LoginByPhoneCode, false, App.getResString(R.string.http_err))
                )
            }
        }
    }

}