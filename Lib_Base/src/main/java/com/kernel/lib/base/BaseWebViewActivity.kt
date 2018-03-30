package com.kernel.lib.base

import android.os.Build
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import com.kernel.lib.base.mvp.BasePresenter
import com.kernel.lib.base.mvp.BaseView
import com.kernel.lib.helper.DeviceHelper
import com.kernel.lib.helper.NetWorkHelper

/**
 * function: webview页面 基类（不能刷新）
 * @param
 *
 * @author:linhuan
 */
abstract class BaseWebViewActivity<V : BaseView, P : BasePresenter<V>> : BaseCompatActivity<V, P>() {

    var mWebView: WebView? = null

    /**
     * initWebView 初始化webview
     *
     * @author linhuan 2015-7-11 上午11:12:35
     */
    fun initWebView(webViewID: Int) {
        mWebView = findViewById<View>(webViewID) as WebView
        if (NetWorkHelper.isNetworkAvailable()) {
            if (DeviceHelper.isBrand("OPPO")) {
                mWebView!!.settings.cacheMode = WebSettings.LOAD_NO_CACHE
            } else {
                mWebView!!.settings.cacheMode = WebSettings.LOAD_DEFAULT
            }
        } else {
            mWebView!!.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        }

        mWebView!!.settings.loadsImagesAutomatically = if (19 > Build.VERSION.SDK_INT) false else true

        mWebView!!.settings.setSupportZoom(false)
        mWebView!!.settings.builtInZoomControls = false
        mWebView!!.settings.javaScriptEnabled = true
        mWebView!!.settings.blockNetworkImage = false
        mWebView!!.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY

        mWebView!!.webChromeClient = BaseWebChromeClient(this)
        mWebView!!.webViewClient = BaseWebViewClient(mWebView!!)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (mWebView!!.canGoBack() && KeyEvent.KEYCODE_BACK == keyCode) {
            mWebView!!.goBack() //goBack()表示返回webView的上一页面
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
//
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
