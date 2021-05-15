package com.pownpon.hua.activity.base

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.pownpon.hua.R
import com.pownpon.hua.activity.LoginActivity
import com.pownpon.hua.global.App
import com.pownpon.hua.global.lc
import com.pownpon.hua.listener.NoDoubleClickListener

abstract class BaseActivity<VDB : ViewDataBinding> : AppCompatActivity(),
    SlidingPaneLayout.PanelSlideListener {

    /* -------------------    成员变量  -------------- */

    /**
     * 绑定视图数据
     */
    protected lateinit var mVDB: VDB

    /**
     * activity的内容视图
     */
    protected lateinit var mContentView: ViewGroup

    /**
     * 状态栏高度
     */
    protected var mStatusHeight: Int = 0

    /**
     * 放触摸层控件
     */
    private lateinit var mTouchLayout: View

    private lateinit var mTitleImgs: Array<ImageView>
    private lateinit var mTitleView: View

    private val mClickListener: ClickListener = ClickListener()

    /* -------------------    重写父类的方法区域  -------------- */
    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_content)

        initView()

        //先进行不需要登录就可进行的初始化
        initBeforeLogin()
        //在需要进行登录，并且已经登录的前提下进行需要登录后的初始化
        if (needLogin()) {
            if (App.isLogin()) {
                initAfterLogin()
            } else {
                //启动到LoginAcvitiy
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    if (RESULT_OK != it.resultCode) {
                        finish()
                    }
                }.launch(Intent(BaseActivity@ this, LoginActivity::class.java))
            }
        }

        mContentView.post {
            //获取状态栏高度
            var rect: Rect = Rect()
            window.decorView.getWindowVisibleDisplayFrame(rect)
            mStatusHeight = rect.top

            initAfterLayout()
        }
    }

    /**
     * 当滑动时
     */
    override fun onPanelSlide(panel: View, slideOffset: Float) {
    }

    /**
     * 当滑动完全跟打开时
     */
    override fun onPanelOpened(panel: View) {
    }

    /**
     * 当滑动完全关闭时
     */
    override fun onPanelClosed(panel: View) {
        finish()
    }


    /* -------------------    自有方法区域，不需要被子类重写  -------------- */

    /**
     * 内部初始化控件
     */
    private fun initView() {
        //内容视图
        mContentView = findViewById(android.R.id.content)

        //侧滑设置
        var slidingPaneLayout: SlidingPaneLayout = findViewById(R.id.spl_root_base_content)
        if (enableSlideBack()) {
            //打开显示右侧视图
            slidingPaneLayout.openPane()
            slidingPaneLayout.addPanelSlideListener(BaseActivity@ this)
        } else {
            slidingPaneLayout.removeViewAt(0)
        }

        //标题栏设置
        var flRoot: FrameLayout = findViewById<FrameLayout>(R.id.fl_root_base_content)
        mTitleView = findViewById(R.id.rl_title_bas_content)
        mTitleImgs = arrayOf(
            findViewById<ImageView>(R.id.iv_rone_base_layout),
            findViewById<ImageView>(R.id.iv_rtwo_base_layout),
            findViewById<ImageView>(R.id.iv_rthree_base_layout),
            findViewById<ImageView>(R.id.iv_back_base_layout)
        )
        for (img in mTitleImgs) {
            img.setOnClickListener(mClickListener)
        }
        //内容布局加载
        mVDB = DataBindingUtil.inflate(
            layoutInflater,
            getLayoutId(),
            flRoot,
            true
        )
        mVDB.lifecycleOwner = this
        //加入放触摸层
        mTouchLayout = View(BaseActivity@ this)
        mTouchLayout.isClickable = true
        mTouchLayout.isEnabled = true
        mTouchLayout.setOnTouchListener { _, _ -> true }
        setTouchLayout(false)
        flRoot.addView(
            mTouchLayout,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    /**
     * 设置点击监听
     */
    protected fun setOnClick(vararg views: View) {
        for(v in views){
            v.setOnClickListener(mClickListener)
        }

    }

    /**
     * 设置页面标题
     */
    protected fun setPageTitle(title: String?, isFitSys: Boolean, vararg resIds: Int) {
        if (isFitSys) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }

        var lpContent: FrameLayout.LayoutParams =
            mVDB.root.layoutParams as FrameLayout.LayoutParams

        if (title.isNullOrEmpty()) {
            mTitleView.visibility = View.GONE
            lpContent.topMargin = 0
        } else {
            mTitleView.visibility = View.VISIBLE
            lpContent.topMargin = mTitleView.height
        }
        mVDB.root.layoutParams = lpContent
        changePageTitle(title ?: "")

        repeat(mTitleImgs.size) {
            //it从0开始计数
            if (null != resIds && resIds.size > it) {
                mTitleImgs[it].setImageResource(resIds[it])
                mTitleImgs[it].visibility = View.VISIBLE
            } else {
                mTitleImgs[it].setImageDrawable(null)
                mTitleImgs[it].visibility = View.GONE
            }
        }
    }

    /**
     * 改变页面标题
     */
    protected fun changePageTitle(title: String) {
        findViewById<TextView>(R.id.tv_title_base_layout).text = title
    }

    /***
     * 设置防触摸层是否作用
     */
    protected fun setTouchLayout(enable: Boolean) {
        mTouchLayout.visibility = if (enable) View.VISIBLE else View.GONE
    }

    /* -------------------    自有方法区域，子类根据需要重写  -------------- */

    /**
     * 当前Acvitity是否是需要登录才能进行展示的
     */
    protected open fun needLogin(): Boolean = false

    /**
     * 是否需要侧滑退出
     */
    protected open fun enableSlideBack(): Boolean = true

    /* -------------------    子类必须重写的方法  -------------- */

    /**
     * 不需要登录就可以进行的初始化工作
     */
    abstract fun initBeforeLogin()

    /**
     * 必须在登录后才能进行的初始化工作
     */
    abstract fun initAfterLogin()

    /**
     * 当布局加载完成后做的初始化工作
     */
    abstract fun initAfterLayout()

    /**
     * 当标题栏点击
     */
    abstract fun onTitleClick(posision: Int)

    /**
     * 当控件被点击
     */
    abstract fun onViewClick(view: View?)

    /**
     * 获取布局id
     */
    abstract fun getLayoutId(): Int

    /**
     * 点击监听内部类
     */
    private inner class ClickListener : NoDoubleClickListener() {
        override fun click(v: View?) {
            when (v?.id) {
                mTitleImgs[0].id -> {
                    onTitleClick(0)
                }
                mTitleImgs[1].id -> {
                    onTitleClick(1)
                }
                mTitleImgs[2].id -> {
                    onTitleClick(2)
                }
                mTitleImgs[3].id -> {
                    finish()
                }
                else -> {
                    onViewClick(v)
                }
            }
        }

    }
}