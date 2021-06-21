package com.pownpon.hua.web

import android.graphics.Bitmap
import android.net.Uri
import android.os.Message
import android.view.View
import android.webkit.*
import com.pownpon.hua.global.lc

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: ChromeClient
 * Author: HUA
 * Date: 2021/6/10 15:08
 * Description:
 * History:
 */
class ChromeClient(private val webCallBack: WebCallBack) : WebChromeClient() {

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        lc("onProgressChanged__${newProgress}")
        super.onProgressChanged(view, newProgress)
        webCallBack.onLoadProgressChange(newProgress)
    }

    override fun onReceivedTitle(view: WebView?, title: String?) {
        lc("onReceivedTitle__${title}")
        super.onReceivedTitle(view, title)
        webCallBack.onRecieverTitle(title?:"")
    }

    override fun onReceivedIcon(view: WebView?, icon: Bitmap?) {
        lc("onReceivedIcon")
        super.onReceivedIcon(view, icon)
    }

    override fun onReceivedTouchIconUrl(view: WebView?, url: String?, precomposed: Boolean) {
        lc("onReceivedTouchIconUrl__${precomposed}__${url}")
        super.onReceivedTouchIconUrl(view, url, precomposed)
    }

    override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
        lc("onShowCustomView__1")
        super.onShowCustomView(view, callback)
    }

    override fun onShowCustomView(
        view: View?,
        requestedOrientation: Int,
        callback: CustomViewCallback?
    ) {
        lc("onShowCustomView__2")
        super.onShowCustomView(view, requestedOrientation, callback)
    }

    override fun onHideCustomView() {
        lc("onHideCustomView")
        super.onHideCustomView()
    }

    override fun onCreateWindow(
        view: WebView?,
        isDialog: Boolean,
        isUserGesture: Boolean,
        resultMsg: Message?
    ): Boolean {
        lc("onCreateWindow")
        return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg)
    }

    override fun onRequestFocus(view: WebView?) {
        lc("onRequestFocus")
        super.onRequestFocus(view)
    }

    override fun onCloseWindow(window: WebView?) {
        lc("onCloseWindow")
        super.onCloseWindow(window)
    }

    override fun onJsAlert(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {
        lc("onJsAlert____${url}")
        return super.onJsAlert(view, url, message, result)
    }

    override fun onJsConfirm(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {
        lc("onJsConfirm__${url}")
        return super.onJsConfirm(view, url, message, result)
    }

    override fun onJsPrompt(
        view: WebView?,
        url: String?,
        message: String?,
        defaultValue: String?,
        result: JsPromptResult?
    ): Boolean {
        lc("onJsPrompt__${url}")
        return super.onJsPrompt(view, url, message, defaultValue, result)
    }

    override fun onJsBeforeUnload(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {
        lc("onJsBeforeUnload__${url}")
        return super.onJsBeforeUnload(view, url, message, result)
    }

    override fun onExceededDatabaseQuota(
        url: String?,
        databaseIdentifier: String?,
        quota: Long,
        estimatedDatabaseSize: Long,
        totalQuota: Long,
        quotaUpdater: WebStorage.QuotaUpdater?
    ) {
        lc("onExceededDatabaseQuota__${url}")
        super.onExceededDatabaseQuota(
            url,
            databaseIdentifier,
            quota,
            estimatedDatabaseSize,
            totalQuota,
            quotaUpdater
        )
    }

    override fun onReachedMaxAppCacheSize(
        requiredStorage: Long,
        quota: Long,
        quotaUpdater: WebStorage.QuotaUpdater?
    ) {
        lc("onReachedMaxAppCacheSize")
        super.onReachedMaxAppCacheSize(requiredStorage, quota, quotaUpdater)
    }

    override fun onGeolocationPermissionsShowPrompt(
        origin: String?,
        callback: GeolocationPermissions.Callback?
    ) {
        lc("onGeolocationPermissionsShowPrompt")
        super.onGeolocationPermissionsShowPrompt(origin, callback)
    }

    override fun onGeolocationPermissionsHidePrompt() {
        lc("onGeolocationPermissionsHidePrompt")
        super.onGeolocationPermissionsHidePrompt()
    }

    override fun onPermissionRequest(request: PermissionRequest?) {
        lc("onPermissionRequest__${request?.resources}")
        super.onPermissionRequest(request)
    }

    override fun onPermissionRequestCanceled(request: PermissionRequest?) {
        lc("onPermissionRequestCanceled__${request?.resources}")
        super.onPermissionRequestCanceled(request)
    }

    override fun onJsTimeout(): Boolean {
        lc("onJsTimeout")
        return super.onJsTimeout()
    }

    override fun onConsoleMessage(message: String?, lineNumber: Int, sourceID: String?) {
        lc("onConsoleMessage_1__${message}")
        super.onConsoleMessage(message, lineNumber, sourceID)
    }

    override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        lc("onConsoleMessage_2__${consoleMessage?.message()}")
        return super.onConsoleMessage(consoleMessage)
    }

    override fun getDefaultVideoPoster(): Bitmap? {
        lc("getDefaultVideoPoster")
        return super.getDefaultVideoPoster()
    }

    override fun getVideoLoadingProgressView(): View? {
        lc("getVideoLoadingProgressView")
        return super.getVideoLoadingProgressView()
    }

    override fun getVisitedHistory(callback: ValueCallback<Array<String>>?) {
        lc("getVisitedHistory")
        super.getVisitedHistory(callback)
    }

    override fun onShowFileChooser(
        webView: WebView?,
        filePathCallback: ValueCallback<Array<Uri>>?,
        fileChooserParams: FileChooserParams?
    ): Boolean {
        lc("onShowFileChooser")
        return super.onShowFileChooser(webView, filePathCallback, fileChooserParams)
    }
}