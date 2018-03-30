package com.kernel.lib.base

import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * BaseWebViewClient 基类
 *
 * @author linhuan 2015年7月11日 上午10:44:36
 */
class BaseWebViewClient(internal var webview: WebView) : WebViewClient() {

    override fun onPageFinished(view: WebView, url: String) {
        super.onPageFinished(view, url)

        webview.settings.loadsImagesAutomatically = true
    }

    // 在本页面跳转
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        //		webview.loadUrl(url, null);
        return super.shouldOverrideUrlLoading(view, url)
    }

}
