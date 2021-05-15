package com.pownpon.hua.fragment

import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import com.pownpon.hua.R
import com.pownpon.hua.databinding.FragmentLoginPhoneBinding
import com.pownpon.hua.fragment.base.BaseFragment
import com.pownpon.hua.global.lc
import com.pownpon.util.SysUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: LoginByPhoneFragment
 * Author: HUA
 * Date: 2021/5/13 11:31
 * Description: 手机号码登录
 * History:
 */
class LoginByPhoneFragment : BaseFragment<FragmentLoginPhoneBinding>() {

    /**
     * 显示验证码的textview
     */
    private val tvCodes: Array<TextView> by lazy {
        arrayOf(
            mVDB.tvCode1FragLoginPhone,
            mVDB.tvCode2FragLoginPhone,
            mVDB.tvCode3FragLoginPhone,
            mVDB.tvCode4FragLoginPhone,
            mVDB.tvCode5FragLoginPhone,
            mVDB.tvCode6FragLoginPhone
        )
    }

    override fun getLayoutResId(): Int = R.layout.fragment_login_phone

    private val backPressedCallback:OnBackPressedCallback = object :OnBackPressedCallback(false){
        override fun handleOnBackPressed() {
            backInputPhone()
        }

    }

    override fun initBeforeLayout() {

        setClick(
            mVDB.tvTopassFragLoginPhone,
            mVDB.btnCommitFragLoginPhone,
            mVDB.llCodeContentFragLoginPhone
        )
        //增加验证码输入监听
        mVDB.etCodeFragLoginPhone.addTextChangedListener {
            changeCodeNumber(it?.toString())
        }
        //增加返回键监听
        requireActivity().onBackPressedDispatcher.addCallback(LoginByPhoneFragment@this,backPressedCallback)
    }


    override fun initAfterLayout() {
        backInputPhone()
    }

    override fun onViewClick(clickView: View?) {
        when (clickView) {
            mVDB.tvTopassFragLoginPhone -> {
                Navigation.findNavController(clickView!!)
                    .navigate(R.id.action_loginByPhoneFragment_to_loginByPasswordFragment)
            }
            mVDB.btnCommitFragLoginPhone -> {
                goInputCode()
            }
            mVDB.llCodeContentFragLoginPhone -> {
                mVDB.etCodeFragLoginPhone.requestFocus()
                SysUtil.showSoftInput(mVDB.etCodeFragLoginPhone)

            }
        }

    }


    /**
     * 输入手机号码
     */
    private fun backInputPhone() {
        backPressedCallback.isEnabled  =false
        mVDB.isCode = false
        mVDB.etPhoneFragLoginPhone.requestFocus()
        mVDB.etPhoneFragLoginPhone.text = null
        //弹起软键盘
        lifecycleScope.launch {
            delay(300)
            SysUtil.showSoftInput(mVDB.etPhoneFragLoginPhone)
        }
    }

    /**
     * 输入验证码
     */
    private fun goInputCode() {
        backPressedCallback.isEnabled  =true
        mVDB.isCode = true
        mVDB.etCodeFragLoginPhone.requestFocus()
        mVDB.etCodeFragLoginPhone.text = null
        //弹起软键盘
        lifecycleScope.launch {
            delay(300)
            SysUtil.showSoftInput(mVDB.etCodeFragLoginPhone)
        }
    }

    /**
     * 当验证码发生变化
     */
    private fun changeCodeNumber(code: String?) {
        //红色框显示位置
        var redIndex = code?.length ?: 0
        repeat(tvCodes.size) { index ->
            if (null != code && code.length > index) {
                tvCodes[index].text = code[index].toString()
            } else {
                tvCodes[index].text = null
            }
            if (index == redIndex) {
                tvCodes[index].setBackgroundResource(R.drawable.bg_transparent_stroke_red)
            } else {
                tvCodes[index].setBackgroundResource(R.drawable.bg_transparent_stroke_grey)
            }
        }

        if (redIndex >= tvCodes.size) {
            lc("提交登录")
        }
    }
}