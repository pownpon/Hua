package com.pownpon.hua.fragment

import android.view.View
import androidx.navigation.Navigation
import com.pownpon.hua.R
import com.pownpon.hua.databinding.FragmentLoginPasswordBinding
import com.pownpon.hua.fragment.base.BaseFragment

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: LoginByPasswordFragment
 * Author: HUA
 * Date: 2021/5/13 11:45
 * Description: 用户名密码登录
 * History:
 */
class LoginByPasswordFragment : BaseFragment<FragmentLoginPasswordBinding>() {

    override fun getLayoutResId(): Int = R.layout.fragment_login_password
    override fun initBeforeLayout() {
        setClick(mVDB.tvTopassFragLoginPassword)
    }

    override fun initAfterLayout() {
    }

    override fun onViewClick(clickView: View?) {
        when (clickView) {
            mVDB.tvTopassFragLoginPassword -> {
                Navigation.findNavController(clickView!!)
                    .popBackStack()
            }
        }
    }
}