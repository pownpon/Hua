package com.pownpon.hua.web

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Message
import android.view.KeyEvent
import android.webkit.*
import com.pownpon.hua.global.lc
import com.pownpon.hua.global.showToast

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: WebClient
 * Author: HUA
 * Date: 2021/6/10 14:42
 * Description:
 * History:
 */
class WebClient(private val webCallBack: WebCallBack) : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        lc("shouldOverrideUrlLoading__1__${url}")
        //链接为空，则拦截，不加载
        if(url.isNullOrEmpty()){
           return true
        }
        //如果当前链接不是网站的链接，则拦截，跳到其他网页加载
        if(!url.contains("hmlan",true)){
            view?.context?.showToast("当前网页不是网站安全页面")
            return true
        }
        return super.shouldOverrideUrlLoading(view, url)

    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        lc("shouldOverrideUrlLoading__2__${request?.url}")
        //链接为空，则拦截，不加载
        if(null == request || request.url.toString().isNullOrEmpty()){
            return true
        }
        //如果当前链接不是网站的链接，则拦截，跳到其他网页加载
        if(true != request.url.host?.contains("hmlan",true)){
            view?.context?.showToast("当前网页不是网站安全页面")
            return true
        }
        return super.shouldOverrideUrlLoading(view, request)
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        lc("onPageStarted__${url}")
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        lc("onPageFinished__${url}")
        super.onPageFinished(view, url)
    }

    override fun onLoadResource(view: WebView?, url: String?) {
        lc("onLoadResource__${url}")
        super.onLoadResource(view, url)
    }

    override fun onPageCommitVisible(view: WebView?, url: String?) {
        lc("onPageCommitVisible__${url}")
        super.onPageCommitVisible(view, url)
    }

    override fun shouldInterceptRequest(view: WebView?, url: String?): WebResourceResponse? {
        lc("shouldInterceptRequest__1__${url}")
        return super.shouldInterceptRequest(view, url)
    }

    override fun shouldInterceptRequest(
        view: WebView?,
        request: WebResourceRequest?
    ): WebResourceResponse? {
        lc("shouldInterceptRequest__2__${request?.url?.javaClass?.name}")
        return super.shouldInterceptRequest(view, request)
    }

    override fun onTooManyRedirects(view: WebView?, cancelMsg: Message?, continueMsg: Message?) {
        lc("onTooManyRedirects")
        super.onTooManyRedirects(view, cancelMsg, continueMsg)
    }

    /**
     * 当接受到错误，同下会一起执行
     */
    override fun onReceivedError(
        view: WebView?,
        errorCode: Int,
        description: String?,
        failingUrl: String?
    ) {
        lc("onReceivedError————1__${failingUrl}__${description}")
        super.onReceivedError(view, errorCode, description, failingUrl)
    }

    /**
     * 当接受到错误，同上会一起执行
     */
    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        lc("onReceivedError————2__${request?.url}__${error?.errorCode}__${error?.description}")
        super.onReceivedError(view, request, error)
    }

    override fun onReceivedHttpError(
        view: WebView?,
        request: WebResourceRequest?,
        errorResponse: WebResourceResponse?
    ) {
        lc("onReceivedHttpError__${request?.url}__${errorResponse?.encoding}")
        super.onReceivedHttpError(view, request, errorResponse)
    }

    override fun onFormResubmission(view: WebView?, dontResend: Message?, resend: Message?) {
        lc("onFormResubmission_")
        super.onFormResubmission(view, dontResend, resend)
    }

    override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
        lc("doUpdateVisitedHistory__${isReload}__${url}")
        super.doUpdateVisitedHistory(view, url, isReload)
    }

    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        lc("onReceivedSslError__${error?.url}")
        super.onReceivedSslError(view, handler, error)
    }

    override fun onReceivedClientCertRequest(view: WebView?, request: ClientCertRequest?) {
        lc("onReceivedClientCertRequest__${request?.host}")
        super.onReceivedClientCertRequest(view, request)
    }

    override fun onReceivedHttpAuthRequest(
        view: WebView?,
        handler: HttpAuthHandler?,
        host: String?,
        realm: String?
    ) {
        lc("onReceivedHttpAuthRequest__${host}__${realm}")
        super.onReceivedHttpAuthRequest(view, handler, host, realm)
    }

    override fun shouldOverrideKeyEvent(view: WebView?, event: KeyEvent?): Boolean {
        lc("shouldOverrideKeyEvent__${event?.keyCode}__${event?.action}")
        return super.shouldOverrideKeyEvent(view, event)
    }

    override fun onUnhandledKeyEvent(view: WebView?, event: KeyEvent?) {
        lc("onUnhandledKeyEvent__${event?.keyCode}__${event?.action}")
        super.onUnhandledKeyEvent(view, event)
    }

    override fun onScaleChanged(view: WebView?, oldScale: Float, newScale: Float) {
        lc("onScaleChanged__${oldScale}__${newScale}")
        super.onScaleChanged(view, oldScale, newScale)
    }

    override fun onReceivedLoginRequest(
        view: WebView?,
        realm: String?,
        account: String?,
        args: String?
    ) {
        lc("onReceivedLoginRequest__${realm}__${account}__${args}")
        super.onReceivedLoginRequest(view, realm, account, args)
    }

    override fun onRenderProcessGone(view: WebView?, detail: RenderProcessGoneDetail?): Boolean {
        lc("onRenderProcessGone")
        return super.onRenderProcessGone(view, detail)
    }

    override fun onSafeBrowsingHit(
        view: WebView?,
        request: WebResourceRequest?,
        threatType: Int,
        callback: SafeBrowsingResponse?
    ) {
        lc("onSafeBrowsingHit__${threatType}__${request?.url}")
        super.onSafeBrowsingHit(view, request, threatType, callback)
    }
}