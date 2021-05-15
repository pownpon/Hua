package com.pownpon.hua.fragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.pownpon.hua.activity.base.BaseActivity
import com.pownpon.hua.global.lc
import com.pownpon.hua.listener.NoDoubleClickListener

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: BaseFragment
 * Author: HUA
 * Date: 2021/5/13 11:33
 * Description:
 * History:
 */
abstract class BaseFragment<VDB : ViewDataBinding> : Fragment() {
    /**
     * 视图数据
     */
    protected lateinit var mVDB: VDB

    /**
     * 点击监听
     */
    private val mClickListener: ClickListener by lazy {
        ClickListener()
    }

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mVDB = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        mVDB.lifecycleOwner  = this
        initBeforeLayout()
        mVDB.root.post {
            initAfterLayout()
        }
        lc("onCreateView")
        return mVDB.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lc("onCreate")
    }

    override fun onResume() {
        super.onResume()
        lc("onResume")
    }

    override fun onPause() {
        super.onPause()
        lc("onPause")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        lc("onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        lc("onDestroy")
    }

    protected fun setClick(vararg views: View){
        for (v in views){
            v.setOnClickListener(mClickListener)
        }

    }

    /**
     * 获取布局资源
     */
    abstract fun getLayoutResId(): Int

    /**
     * 布局完成前可以进行的初始化工作
     */
    abstract fun initBeforeLayout()

    /**
     * 布局完成后可以进行的初始化工作
     */
    abstract fun initAfterLayout()

    /**
     * 当控件被点击
     */
    abstract fun onViewClick(clickView: View?)

    /**
     * 点击监听内部类
     */
    private inner class ClickListener : NoDoubleClickListener() {
        override fun click(v: View?) {
            onViewClick(v)
        }
    }
}