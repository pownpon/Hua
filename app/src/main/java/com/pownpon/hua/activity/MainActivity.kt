package com.pownpon.hua.activity

import android.os.Bundle
import com.pownpon.hua.R
import com.pownpon.hua.activity.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}