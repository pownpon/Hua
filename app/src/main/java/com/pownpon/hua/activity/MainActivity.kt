package com.pownpon.hua.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.pownpon.hua.R
import com.pownpon.hua.activity.base.BaseActivity
import com.pownpon.hua.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initBeforeLogin() {
    }

    override fun initAfterLogin() {
    }

}