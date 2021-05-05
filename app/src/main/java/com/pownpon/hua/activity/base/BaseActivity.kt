package com.pownpon.hua.activity.base

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.pownpon.hua.activity.LoginActivity
import com.pownpon.hua.global.App

abstract class BaseActivity<VDB : ViewDataBinding> : AppCompatActivity() {

    /* -------------------    成员变量  -------------- */

    /**
     * 绑定视图数据
     */
    protected lateinit var mVDB: VDB

    /* -------------------    重写父类的方法区域  -------------- */
    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mVDB = DataBindingUtil.setContentView(this, getLayoutId())

        //先进行不需要登录就可进行的初始化
        initBeforeLogin()
        //在需要进行登录，并且已经登录的前提下进行需要登录后的初始化
        if (needLogin() ) {
            if(App.isLogin()){
                initAfterLogin()
            }else{
                goActivity(LoginActivity::class.java)
            }
        }
    }

    /* -------------------    自有方法区域，不需要被子类重写  -------------- */

    /**
     * 启动另一个Activity，可用于以后在启动时统一加入操作
     */
    fun goActivity(cls: Class<*>, vararg params: String) {
        var targetIntent = Intent(BaseActivity@ this, cls)
        if (null != params && params.isNotEmpty()) {
            for (param in params) {
                var kv = param.split(':')
                if (null != kv && kv.size == 2) {
                    targetIntent.putExtra(kv[0], kv[1])
                }
            }
        }
        startActivity(targetIntent)
    }

    /**
     * 启动另一个Activity，可用于以后在启动时统一加入操作
     */
    fun goActivity(intent: Intent) {
        startActivity(intent)
    }

    /**
     * 启动另一个Activity，可用于以后在启动时统一加入操作
     */
    fun goActivityForResult(intent: Intent, reqCode: Int) {
        startActivityForResult(intent, reqCode)
    }

    /**
     * 启动另一个Activity，可用于以后在启动时统一加入操作
     */
    fun goActivityForResult(cls: Class<*>, reqCode: Int,vararg params: String) {
        var targetIntent = Intent(BaseActivity@ this, cls)
        if (null != params && params.isNotEmpty()) {
            for (param in params) {
                var kv = param.split(':')
                if (null != kv && kv.size == 2) {
                    targetIntent.putExtra(kv[0], kv[1])
                }
            }
        }
        startActivityForResult(targetIntent, reqCode)
    }

    /* -------------------    自有方法区域，子类根据需要重写  -------------- */

    /**
     * 当前Acvitity是否是需要登录才能进行展示的
     */
    open fun needLogin(): Boolean {
        return false
    }

    /* -------------------    子类必须重写的方法  -------------- */
    /**
     * 获取布局id
     */
    abstract fun getLayoutId(): Int

    /**
     * 不需要登录就可以进行的初始化工作
     */
    abstract fun initBeforeLogin()

    /**
     * 必须在登录后才能进行的初始化工作
     */
    abstract fun initAfterLogin()

}