package com.pownpon.hua.web

import android.annotation.TargetApi
import android.os.Build
import android.webkit.WebView
import android.webkit.WebViewRenderProcess
import android.webkit.WebViewRenderProcessClient
import androidx.annotation.RequiresApi
import com.pownpon.hua.global.lc

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: WebRenderProcessClient
 * Author: HUA
 * Date: 2021/6/10 14:52
 * Description:
 * History:
 */

@RequiresApi(Build.VERSION_CODES.Q)
class WebRenderProcessClient:WebViewRenderProcessClient() {
    override fun onRenderProcessUnresponsive(view: WebView, renderer: WebViewRenderProcess?) {
        lc("onRenderProcessUnresponsive__${renderer?.javaClass?.name}")
    }

    override fun onRenderProcessResponsive(view: WebView, renderer: WebViewRenderProcess?) {
        lc("onRenderProcessUnresponsive__${renderer?.javaClass?.name}")
    }
}