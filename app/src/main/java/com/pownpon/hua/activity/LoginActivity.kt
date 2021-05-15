package com.pownpon.hua.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import com.pownpon.hua.R
import com.pownpon.hua.activity.base.BaseActivity
import com.pownpon.hua.databinding.ActivityLoginBinding
import com.pownpon.hua.global.lc

class LoginActivity : BaseActivity<ActivityLoginBinding>() {


    private val mNavigationListener: NavController.OnDestinationChangedListener =
        NavController.OnDestinationChangedListener { _, des, _ ->
//            when (des.label) {
//                "LoginByPhoneFragment" -> changePageTitle("手机号码登录")
//                "LoginByPasswordFragment" -> changePageTitle("用户名密码登录")
            lc("111：${mController.currentDestination}")
//            }

        }

    private lateinit var mController: NavController

    override fun initBeforeLogin() {
        //增加监听
        mController = Navigation.findNavController(LoginActivity@ this, R.id.frag_act_login)
        mController.addOnDestinationChangedListener(mNavigationListener)

    }

    override fun initAfterLogin() {
    }

    override fun initAfterLayout() {
        setPageTitle(null, false)
        window.statusBarColor = resources.getColor(R.color.bg_page,null)
    }

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun onTitleClick(posision: Int) {
    }

    override fun onViewClick(view: View?) {
    }

    override fun enableSlideBack(): Boolean = false

    override fun onDestroy() {
        super.onDestroy()
        //移除监听
        mController.removeOnDestinationChangedListener(mNavigationListener)
    }

}