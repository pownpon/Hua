package com.pownpon.hua.global

import android.app.Application
import android.graphics.Color

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

    /**
     * 获取资源字符串
     */
    fun getResString(resId:Int):String=   mApp.resources.getString(resId)

    /**
     * 获取资源颜色值
     */
    fun getResColor(resId:Int):Int=   mApp.resources.getColor(resId,null)
}