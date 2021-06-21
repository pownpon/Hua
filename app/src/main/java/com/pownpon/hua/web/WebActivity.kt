package com.pownpon.hua.web

import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.lifecycle.lifecycleScope
import com.pownpon.hua.R
import com.pownpon.hua.activity.base.BaseActivity
import com.pownpon.hua.databinding.ActivityWebBinding
import com.pownpon.hua.global.It
import com.pownpon.hua.global.lc
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WebActivity : BaseActivity<ActivityWebBinding>(), WebCallBack {
    /**
     * 开始的url
     */
    private lateinit var urlStart:String

    override fun initPageData(savedInstanceState: Bundle?) {
        urlStart = intent.getStringExtra(It.keyWebUrl)?:"https://m.hmlan.com/h5"
    }

    override fun initPageView() {
        //初始化标题
        setPageTitle("中国兰花交易网", false)

        //webview相关设置
        mVDB.wvActWeb.webViewClient = WebClient(WebActivity@ this)
        mVDB.wvActWeb.webChromeClient = ChromeClient(WebActivity@ this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            mVDB.wvActWeb.webViewRenderProcessClient = WebRenderProcessClient()
        }
        val webSet: WebSettings = mVDB.wvActWeb.settings
        //设置网页内部的JS可用
        webSet.javaScriptEnabled = true
        //支持插件
        //webSet.setPluginsEnabled(true)

        //设置自适应屏幕，两者合用
        webSet.useWideViewPort = true //将图片调整到适合webview的大小
        webSet.loadWithOverviewMode = true // 缩放至屏幕的大小

        //缩放操作
        webSet.setSupportZoom(false) //不支持缩放，默认为true。是下面那个的前提。
        webSet.builtInZoomControls = false //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSet.displayZoomControls = false //隐藏原生的缩放控件

        //缓存模式：
        //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        //LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
        //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
        webSet.cacheMode = WebSettings.LOAD_NO_CACHE

        //允许https 和 http共同一起加载
        webSet.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

        webSet.domStorageEnabled = true // 开启 DOM storage API 功能
        //webSet.databaseEnabled = true   //开启 database storage API 功能

        webSet.allowFileAccess = true //设置可以访问文件
        webSet.javaScriptCanOpenWindowsAutomatically = true //支持通过JS打开新窗口
        webSet.loadsImagesAutomatically = true //支持自动加载图片
        webSet.defaultTextEncodingName = "utf-8"//设置编码格式

    }

    override fun startLoadData() {
        mVDB.wvActWeb.loadUrl(urlStart)

    }

    override fun onTitleClick(posision: Int) {
    }

    override fun onViewClick(view: View?) {
    }

    override fun getLayoutId(): Int = R.layout.activity_web


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        lc("onkeydown")
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(mVDB.wvActWeb.canGoBack()){
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        lc("onkeyup")
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(mVDB.wvActWeb.canGoBack()){
                mVDB.wvActWeb.goBack()
                return true
            }
        }
        return super.onKeyUp(keyCode, event)
    }

    override fun onPause() {
        super.onPause()
        //通知webview暂停
        mVDB.wvActWeb.onPause()
        //暂停webview 的 JS
        mVDB.wvActWeb.pauseTimers()

    }

    override fun onResume() {
        super.onResume()
        //通知webview继续
        mVDB.wvActWeb.onResume()
        //继续webview 的 JS
        mVDB.wvActWeb.resumeTimers()
    }

    override fun onDestroy() {
        super.onDestroy()
        val parent = mVDB.wvActWeb.parent
        if(null!= parent && parent is ViewGroup){
            parent.removeView(mVDB.wvActWeb)
        }
        mVDB.wvActWeb.clearHistory()
        mVDB.wvActWeb.removeAllViews()
        mVDB.wvActWeb.destroy()
    }

    //----————————————————————           以下为回调方法         ——————————————————————————————————————————————//

    override fun onRecieverTitle(title: String) {
        changePageTitle(title)
    }

    override fun onLoadProgressChange(progress: Int) {
        mVDB.pbActWeb.progress = progress
        mVDB.pbActWeb.visibility = if (progress >= 100) View.GONE else View.VISIBLE

    }
}