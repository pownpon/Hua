package com.pownpon.hua

import android.app.Application
import com.pownpon.hua.global.App

class AppHua: Application() {

    override fun onCreate() {
        super.onCreate()
        //初始化相关工作
        App.appInit(this)
    }

}