package com.pownpon.hua.global

import android.app.Activity
import android.app.Application
import android.graphics.Color
import android.view.View
import com.pownpon.hua.activity.base.TopBaseActivity
import java.util.*

object App {

    /*           ————————————————————————————————全局变量区域——————————————————————————————————————————               */

    /**
     * app当前的登录状态
     */
    private lateinit var mApp: Application

    /**
     * app当前的登录状态
     */
    private var mLoginStatus = false

    /**
     * 页面集合
     */
    private val acitivitys: LinkedList<TopBaseActivity> = LinkedList()



    /*           ————————————————————————————————以下为方法区域——————————————————————————————————————————               */
    /**
     * APP初始化调用的方法
     */
    fun appInit(app: Application) {
        //对象赋值
        mApp = app
    }

    /**
     * 获取APP当前的登录状态
     */
    fun isLogin(): Boolean {
        return mLoginStatus
    }

    /**
     * 获取当前Application
     */
    fun getApp(): Application {
        return mApp
    }

    /**
     * 当activity创建
     */
    fun actIn(activity: TopBaseActivity) {
            acitivitys.addLast(activity)
    }

    /**
     * 当activity销毁
     */
    fun actOut(activity: TopBaseActivity) {
            acitivitys.remove(activity)
    }

    /**
     * 判断APP是否有存活的页面
     */
    fun isAlivePage(): Boolean {
        if (acitivitys.size > 0) {
            for (act in acitivitys) {
                if (act.showTag != Constans.actShowNothing && act.showTag != Constans.actShowDestory) {
                    return true
                }
            }
        }
        return false
    }

    /**
     * 判断APP是否前台可见
     */
    fun isView(): Boolean {
        if (acitivitys.size > 0) {
            for (act in acitivitys) {
                if (act.showTag == Constans.actShowResume) {
                    return true
                }
            }
        }
        return false
    }

    /**
     * 获取资源字符串
     */
    fun getResString(resId: Int): String = mApp.resources.getString(resId)

    /**
     * 获取资源颜色值
     */
    fun getResColor(resId: Int): Int = mApp.resources.getColor(resId, null)

}