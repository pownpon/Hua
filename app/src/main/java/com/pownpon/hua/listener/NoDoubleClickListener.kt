package com.pownpon.hua.listener

import android.view.View

abstract class NoDoubleClickListener : View.OnClickListener {

    private var mLastMillis = 0L

    /**
     * 两次点击的时间间隔，小于此毫秒值，则拦截返回
     */
    private val mMillisInterval = 500

    final override fun onClick(v: View?) {
        var currentMillis = System.currentTimeMillis()
        if (currentMillis - mLastMillis < mMillisInterval) {
            return
        }
        mLastMillis = currentMillis
        click(v)
    }

    abstract fun click(v: View?)

}