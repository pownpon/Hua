package com.pownpon.hua.activity.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() {

    /**
     * 绑定视图数据
     */
    protected lateinit var mDb: DB

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDb = DataBindingUtil.setContentView(this, getLayoutId())
    }

    /**
     * 获取布局id
     */
    abstract fun getLayoutId(): Int

}