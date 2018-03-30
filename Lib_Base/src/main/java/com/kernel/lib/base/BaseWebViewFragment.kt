package com.kernel.lib.base

import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import com.kernel.lib.base.mvp.BasePresenter
import com.kernel.lib.base.mvp.BaseView

import com.kernel.lib.helper.NetWorkHelper

/**
 * BaseWebViewFragment:webview页面基类
 *
 * @author linhuan 2015-11-23上午10:33:22
 */
abstract class BaseWebViewFragment<V : BaseView, P : BasePresenter<V>> : BaseFragment<V, P>() {

    var mWebView: WebView? = null

    /**
     * initWebView 初始化webview
     *
     * @author linhuan 2015-7-11 上午11:12:35
     */
    fun initWebView(webViewID: Int) {
        mWebView = findViewById(webViewID) as WebView
        if (NetWorkHelper.isNetworkAvailable()) {
            mWebView!!.settings.cacheMode = WebSettings.LOAD_DEFAULT
        } else {
            mWebView!!.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        }

        mWebView!!.settings.loadsImagesAutomatically = if (19 > Build.VERSION.SDK_INT) false else true

        mWebView!!.settings.setSupportZoom(false)
        mWebView!!.settings.builtInZoomControls = false
        mWebView!!.settings.javaScriptEnabled = true
        mWebView!!.settings.blockNetworkImage = false
        mWebView!!.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY

        mWebView!!.webChromeClient = BaseWebChromeClient(activity!!)
        mWebView!!.webViewClient = BaseWebViewClient(mWebView!!)
    }

    override fun onDestroy() {
        super.onDestroy()

        mWebView!!.removeAllViews()
        (mWebView!!.parent as ViewGroup).removeView(mWebView)
        mWebView!!.tag = null
        mWebView!!.clearHistory()
        mWebView!!.destroy()
        mWebView = null
    }

}
