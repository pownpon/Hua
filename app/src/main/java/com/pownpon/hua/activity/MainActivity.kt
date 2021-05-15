package com.pownpon.hua.activity

import android.content.Intent
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.pownpon.hua.listener.NoDoubleClickListener
import com.pownpon.hua.R
import com.pownpon.hua.activity.base.BaseActivity
import com.pownpon.hua.databinding.ActivityMainBinding
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initBeforeLogin() {
        mVDB.tvActMainStart.setOnClickListener(object : NoDoubleClickListener() {
            override fun click(v: View?) {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            }

        })


    }

    override fun initAfterLogin() {

    }

    override fun initAfterLayout() {
        setPageTitle("xxxx", false, R.mipmap.icon)
    }

    override fun onTitleClick(posision: Int) {
    }

    override fun onViewClick(view: View?) {
    }

    override fun enableSlideBack(): Boolean =false

}