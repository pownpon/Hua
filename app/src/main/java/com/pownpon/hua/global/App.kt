package com.pownpon.hua.global

import android.app.Application

object App {

    /*           ————————————————————————————————全局变量区域——————————————————————————————————————————               */

    /**
     * app当前的登录状态
     */
    private lateinit var mApp:Application

    /**
     * app当前的登录状态
     */
    private var mLoginStatus= false


    /*           ————————————————————————————————以下为方法区域——————————————————————————————————————————               */
    /**
     * APP初始化调用的方法
     */
    fun appInit(app:Application){
        //对象赋值
        mApp = app
    }

    /**
     * 获取APP当前的登录状态
     */
    fun  isLogin():Boolean{
        return mLoginStatus
    }

    /**
     * 获取当前Application
     */
    fun getApp():Application{
        return mApp
    }
}