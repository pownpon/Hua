package com.pownpon.hua.activity.base

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.pownpon.hua.R
import com.pownpon.hua.activity.GgActivity
import com.pownpon.hua.global.App
import com.pownpon.hua.global.Constans
import com.pownpon.hua.global.lc

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: TopBaseActivity
 * Author: HUA
 * Date: 2021/6/8 10:57
 * Description: 最上层(基类)的activity
 * History:
 */
abstract class TopBaseActivity : AppCompatActivity(){

    companion object{
        /**
         * act最后暂停的时间
         */
        var lastActPauseTime: Long = System.currentTimeMillis()
            private set
    }

    /**
     * 当前act的显示标志
     */
    var showTag: Int = Constans.actShowNothing
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showTag = Constans.actShowCreate
        App.actIn(this@TopBaseActivity)
        lc("oncreate")
    }

    override fun onResume() {
        super.onResume()

        showTag = Constans.actShowResume

        val current:Long = System.currentTimeMillis()
        if (current - lastActPauseTime > Constans.showGgInterval) {
            startActivity(Intent(this@TopBaseActivity, GgActivity::class.java))
        }
        lc("onresume")
    }

    override fun onPause() {
        super.onPause()
        showTag = Constans.actShowPause
        lastActPauseTime = System.currentTimeMillis()
        lc("onpause")
    }

    override fun onDestroy() {
        super.onDestroy()
        showTag = Constans.actShowDestory
        App.actOut(this@TopBaseActivity)
        lc("oondestory")
    }
}