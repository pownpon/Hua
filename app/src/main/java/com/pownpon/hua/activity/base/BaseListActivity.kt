package com.pownpon.hua.activity.base

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.pownpon.hua.R
import com.pownpon.hua.databinding.ActivityBaseListBinding
import com.pownpon.ui.smartrefresh.api.RefreshLayout
import com.pownpon.ui.smartrefresh.listener.OnRefreshLoadMoreListener

abstract class BaseListActivity<VDB : ViewDataBinding> : BaseActivity<ActivityBaseListBinding>(),
    OnRefreshLoadMoreListener {

    /**
     * 上层布局数据绑定
     */
    protected lateinit var mVDBList: VDB

    /**
     * 是否正在刷新
     */
    private var mIsRefreshing = false

    /**
     * 是否正在加载更多
     */
    private var mIsLoading = false

    /**
     * 当前页面
     */
    private var mPage = 1



    final override fun getLayoutId(): Int {
        return R.layout.activity_base_list
    }

    final override fun initBeforeLogin() {
        //将上层布局载入到视图中
        mVDBList = DataBindingUtil.inflate(
            LayoutInflater.from(BaseListActivity@ this),
            getTopLayoutId(),
            mVDB.flContentActBaseList,
            true
        )
        //刷新监听
        mVDB.smartRefreshActBaseList.setOnRefreshLoadMoreListener(BaseListActivity@ this)
        //防触摸层设置
        mVDB.flTouchActBaseList.isEnabled =false
        mVDB.flTouchActBaseList.visibility = View.GONE

        //加载子类的最上层布局
        initTopLayout()
        //加载一次数据
        refreshData()
    }

    /*————————————————————————————————刷新监听——————————————————————————————————————————*/
    final override fun onRefresh(refreshLayout: RefreshLayout) {
        //防触摸
        mVDB.flTouchActBaseList.visibility = View.VISIBLE
        //正在刷新中
        mIsRefreshing =true
        //加载数据
        mPage = 1
        onRefreshData(mPage)
    }

    final override fun onLoadMore(refreshLayout: RefreshLayout) {
        //防触摸
        mVDB.flTouchActBaseList.visibility = View.VISIBLE
        //正在刷新中
        mIsLoading =true
        //加载数据
        onLoadMoreData((++mPage))
    }
    /*————————————————————————————————给子类调用的方法——————————————————————————————————————————*/

    /**
     * 当加载数据完成
     */
    protected fun onRefreshLoadMoreComplete() {
        mVDB.smartRefreshActBaseList.finishRefresh()
        mVDB.smartRefreshActBaseList.finishLoadMore()
        //刷新加载完成
        mIsRefreshing =false
        mIsLoading = false
        //可触摸
        mVDB.flTouchActBaseList.visibility = View.GONE
    }

    /**
     * 刷新数据
     */
    protected fun refreshData(){
        mVDB.smartRefreshActBaseList.autoRefresh()
    }

    /**
     * 加载更多数据
     */
    protected fun loadMoreData(){
        mVDB.smartRefreshActBaseList.autoLoadMore()
    }

    /**
     * 获取是否正在刷新
     */
    protected fun isRefreshing():Boolean{
        return mIsRefreshing
    }

    /**
     * 获取是否正在加载更多
     */
    protected fun isLoading():Boolean{
        return mIsLoading
    }

    /*———————————————————————————————必须重写的方法——————————————————————————————————————————*/

    /**
     * 获取列表上层的视图布局
     */
    abstract fun getTopLayoutId(): Int

    /**
     * 加载上层布局
     */
    abstract fun initTopLayout()

    /**
     * 刷新数据
     */
    abstract fun onRefreshData(page:Int)

    /**
     * 加载更多数据
     */
    abstract fun onLoadMoreData(page: Int)
}