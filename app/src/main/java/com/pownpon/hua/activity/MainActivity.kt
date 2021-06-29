package com.pownpon.hua.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.pownpon.hua.listener.NoDoubleClickListener
import com.pownpon.hua.R
import com.pownpon.hua.activity.base.BaseActivity
import com.pownpon.hua.databinding.ActivityMainBinding
import com.pownpon.hua.web.WebActivity
import com.pownpon.picture.picker.PickPictureActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initPageData(savedInstanceState: Bundle?) {
        //主Act启动的时候，先直接启动到广告页面
        //startActivity(Intent(this@MainActivity, GgActivity::class.java))

    }

    override fun initPageView() {
        setPageTitle("xxxx", false, R.mipmap.icon)

        mVDB.tv1.setOnClickListener(object : NoDoubleClickListener() {
            override fun click(v: View?) {
                startActivity(Intent(this@MainActivity, ListGoodsActivity::class.java))
            }
        })
        mVDB.tv2.setOnClickListener(object : NoDoubleClickListener() {
            override fun click(v: View?) {
                startActivity(Intent(this@MainActivity, WebActivity::class.java))
            }

        })
        mVDB.tv3.setOnClickListener(object : NoDoubleClickListener() {
            override fun click(v: View?) {
                startActivity(Intent(this@MainActivity, PickPictureActivity::class.java))
            }

        })
    }

    override fun startLoadData() {

    }

    override fun onTitleClick(posision: Int) {
    }

    override fun onViewClick(view: View?) {
    }

    override fun enableSlideBack(): Boolean =false

}