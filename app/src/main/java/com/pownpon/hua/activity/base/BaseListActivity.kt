package com.pownpon.hua.activity.base

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.pownpon.hua.R
import com.pownpon.hua.adapter.LoadStateFootAdapter
import com.pownpon.hua.adapter.base.BasePageDataAdapter
import com.pownpon.hua.model.bean.base.BaseEntity
import com.pownpon.hua.databinding.BaseListBinding
import com.pownpon.hua.global.lc

abstract class BaseListActivity<T : BaseEntity, VDB : ViewDataBinding, ItemVDB : ViewDataBinding> :
    BaseActivity<BaseListBinding>(),
    SwipeRefreshLayout.OnRefreshListener {

    /**
     * 上层布局数据绑定
     */
    protected var mVDBList: VDB? = null

    /**
     * 是否正在加载数据中
     */
    private var mIsDataLoading = false

    protected lateinit var mAdapter: BasePageDataAdapter<T, ItemVDB>

    protected lateinit var mManager: RecyclerView.LayoutManager


    final override fun getLayoutId(): Int {
        return R.layout.base_list
    }

    final override fun initBeforeLogin() {
        //将上层布局载入到视图中
        var topLayoutId = getTopLayoutId()
        if (null != topLayoutId) {
            mVDBList = DataBindingUtil.inflate(
                layoutInflater,
                topLayoutId,
                mVDB.root as ViewGroup,
                true
            )
            mVDBList?.lifecycleOwner = BaseListActivity@ this
        }

        //recyclerview设置
        mManager = initManager()
        mVDB.rvBaseList.layoutManager = mManager
        mAdapter = initAdapter()
        var concatAdapter: ConcatAdapter =
            mAdapter.withLoadStateFooter(LoadStateFootAdapter(BaseListActivity@ this) { mAdapter.retry() })
        concatTopAdadpter(concatAdapter)
        mVDB.rvBaseList.adapter = concatAdapter

        initUI()

        registListener()

        //加载子类的最上层布局
        initTopLayout()
    }

    override fun initAfterLayout() {
        //重新设置列表控件的高度
        var lpSmartRefresh: FrameLayout.LayoutParams =
            mVDB.srlBaseList.layoutParams as FrameLayout.LayoutParams
        lpSmartRefresh.topMargin = getTitleHeight()
        mVDB.srlBaseList.layoutParams = lpSmartRefresh

        var lpTvNoData: FrameLayout.LayoutParams =
            mVDB.tvNodataBaseList.layoutParams as FrameLayout.LayoutParams
        lpTvNoData.topMargin = getTitleHeight()
        mVDB.tvNodataBaseList.layoutParams = lpTvNoData
    }

    /**
     * 控件初始化
     */
    private fun initUI() {
        mVDB.srlBaseList.setColorSchemeResources(R.color.topic, R.color.topic_2)
        mVDB.srlBaseList.setProgressBackgroundColorSchemeResource(R.color.white)

        /*  view的post 方法会在layout之后被执行，
        *   activity中的onResume方法执行在layout之前
        *   还有一种方式是监听整体布局发生改变时window.decorView.viewTreeObserver.addOnGlobalLayoutListener {  }
        *   此处只需要执行一次，所以用post
        */
    }


    /**
     * 设置监听
     */
    private fun registListener() {
        //刷新监听
        mVDB.srlBaseList.setOnRefreshListener(BaseListActivity@ this)
        //adapter加载状态监听
        mAdapter.addLoadStateListener {
            /*
             * CombinedLoadStates 中成员变量
             * refresh 表示刷新的状态
             * prepend 表示往前页加载的状态
             * append  表示往后页加载的状态
             * 这三个状态中的（即LoadState类中的属性）endOfPaginationReached表示是否分页结束，即是否已经加载完成
             *
             * source  当数据是从PagingSource加载过来时，不为空，并且其内部状态与上述三个状态完全一致
             * mediator 当数据是从RemoteMediator（媒体库）加载时，不为空，并且其内部状态与上述三个状态完全一致
             *
             * refresh/prepend/append 状态有三个值，
             * NotLoading   没有加载中，即什么都没干
             * Loading      正在加载数据中
             * Error        加载数据出现错误
             *
             */

            if (it.refresh is LoadState.Loading) {
                mVDB.rvBaseList.scrollToPosition(0)
                //当数据在刷新状态时，而控件没有显示刷新，则将控件显示刷新状态
                if (!mVDB.srlBaseList.isRefreshing) {
                    mVDB.srlBaseList.isRefreshing = true
                }

            } else {
                //当数据没有在刷新中时，而控件在刷新状态中时，将控件刷新状态结束
                if (mVDB.srlBaseList.isRefreshing) {
                    mVDB.srlBaseList.isRefreshing = false
                }
            }

            //当刷新/前加/后加 任一状态是加载中时，即正在加载数据中
            mIsDataLoading =
                it.refresh is LoadState.Loading || it.prepend is LoadState.Loading || it.append is LoadState.Loading
            setTouchLayout(mIsDataLoading)


            var isNoData = it.refresh is LoadState.Error
            //当不是在刷新状态时，并且和之前村粗的值不一样时，才去改变值
            if (it.refresh !is LoadState.Loading && mVDB.isNoData != isNoData) {
                mVDB.isNoData = isNoData
            }

        }

    }

    /*————————————————————————————————刷新监听——————————————————————————————————————————*/

    final override fun onRefresh() {
        //刷新数据
        mAdapter.refresh()
    }

    /*————————————————————————————————给子类调用或重写的方法——————————————————————————————————————————*/

    /**
     * 获取是否正在加载数据
     */
    protected fun isDataLoading(): Boolean {
        return mIsDataLoading
    }

    /**
     * 组合adapter
     */
    protected open fun concatTopAdadpter(concatAdapter: ConcatAdapter) {

    }


    /*———————————————————————————————必须重写的方法——————————————————————————————————————————*/

    /**
     * 获取列表上层的视图布局
     */
    abstract fun getTopLayoutId(): Int?

    /**
     * 获取recyclerview的顶部距离
     */
    abstract fun getTitleHeight(): Int

    /**
     * 初始化上层布局
     */
    abstract fun initTopLayout()

    /**
     * 初始化适配器
     */
    abstract fun initAdapter(): BasePageDataAdapter<T, ItemVDB>

    /**
     * 初始化布局管理器
     */
    abstract fun initManager(): RecyclerView.LayoutManager
}