package com.pownpon.hua.activity

import android.view.View
import com.pownpon.hua.listener.NoDoubleClickListener
import com.pownpon.hua.R
import com.pownpon.hua.activity.base.BaseActivity
import com.pownpon.hua.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initBeforeLogin() {
        mVDb.tvActMainStart.setOnClickListener(object : NoDoubleClickListener() {
            override fun click(v: View?) {
                goActivity(ListGoodsActivity::class.java)
            }

        })
    }

    override fun initAfterLogin() {

    }

}