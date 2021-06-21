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


    override fun initPageData(savedInstanceState: Bundle?) {

    }

    override fun initPageView() {
        setPageTitle(null, false)
        window.statusBarColor = resources.getColor(R.color.bg_page,null)
    }

    override fun startLoadData() {

    }

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun onTitleClick(posision: Int) {
    }

    override fun onViewClick(view: View?) {
    }

    override fun enableSlideBack(): Boolean = false



}