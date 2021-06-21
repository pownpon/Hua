package com.pownpon.hua.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.pownpon.hua.R
import com.pownpon.hua.databinding.FragmentLoginPasswordBinding
import com.pownpon.hua.fragment.base.BaseFragment
import com.pownpon.hua.global.showToast
import com.pownpon.hua.vm.VmLogin
import com.pownpon.util.SysUtil

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: LoginByPasswordFragment
 * Author: HUA
 * Date: 2021/5/13 11:45
 * Description: 用户名密码登录
 * History:
 */
class LoginByPasswordFragment : BaseFragment<FragmentLoginPasswordBinding>() {

    private val vmLogin: VmLogin by viewModels<VmLogin> {
        ViewModelProvider.NewInstanceFactory()
    }

    override fun getLayoutResId(): Int = R.layout.fragment_login_password
    override fun initBeforeLayout() {
        mVDB.needCode = false
        setClick(mVDB.tvTopassFragLoginPassword, mVDB.btnCommitFragLoginPassword)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vmLogin.actionDataResult.observe(this) {
            if (it.success) {
                when (it.tag) {
                    VmLogin.Tag_LoginByName -> {
                        context?.showToast("登录成功")
                    }
                }
            } else {

                if (it.tag == VmLogin.Tag_LoginByName && it?.data ?: 0 > 2) {
                    mVDB.needCode = true
                }
                context?.showToast(it.errInfo ?: "")
            }
        }
    }

    override fun initAfterLayout() {
        mVDB.etNameFragLoginPassword.requestFocus()
        SysUtil.showSoftInput(mVDB.etNameFragLoginPassword)
    }

    override fun onViewClick(clickView: View?) {
        when (clickView) {
            mVDB.tvTopassFragLoginPassword -> {
                Navigation.findNavController(clickView!!)
                    .popBackStack()
            }
            mVDB.btnCommitFragLoginPassword -> {
                vmLogin.loginByName(
                    mVDB.etNameFragLoginPassword.text.toString(),
                    mVDB.etPasswordFragLoginPassword.text.toString(),
                    mVDB.etCodeFragLoginPassword.text.toString()
                )
            }
        }
    }
}