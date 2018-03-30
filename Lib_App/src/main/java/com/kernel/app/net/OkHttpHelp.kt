package com.kernel.lib.base.net

import com.kernel.lib.base.BaseApplication
import com.kernel.lib.helper.Helper
import com.kernel.lib.helper.NetWorkHelper
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.FileCallback
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.cookie.CookieJarImpl
import com.lzy.okgo.cookie.store.MemoryCookieStore
import okhttp3.MediaType
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by hp on 2017/9/22.
 */

object OkHttpHelp {

    private val TIME = (5 * 1000).toLong()

    val httpClient: OkHttpClient
        get() = OkGo.getInstance().okHttpClient

    fun initOkHttp() {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(TIME, TimeUnit.MILLISECONDS)
        builder.writeTimeout(TIME, TimeUnit.MILLISECONDS)
        builder.connectTimeout(TIME, TimeUnit.MILLISECONDS)
        builder.cookieJar(CookieJarImpl(MemoryCookieStore()))
        OkGo.getInstance()
                .init(BaseApplication.instance).okHttpClient = builder.build()
    }

    fun postOkHttp(url: String, content: String, isUser: Boolean, callback: BaseCallback) {
        if (isUser) {
            postOkUserHttp(url, content, callback)
        } else {
            postOkHttp(url, content, callback)
        }
    }

    fun postOkHttp(url: String, content: String, callback: BaseCallback) {
        OkGo.post<String>(url)
                .retryCount(2)
                .upString(content)
                .execute(callback)
    }

    fun postOkUserHttp(url: String, content: String, callback: BaseCallback) {
        OkGo.post<String>(url)
                .retryCount(2)
                .upString(content)
                .execute(callback)
    }

    fun getNoDealUrl(url: String, stringCallback: StringCallback) {
        OkGo.post<String>(url)
                .retryCount(2)
                .execute(stringCallback)
    }

    fun postNoDealUrl(url: String, content: String, mediaType: String, stringCallback: StringCallback) {
        OkGo.post<String>(url)
                .retryCount(2)
                .upString(content, MediaType.parse(mediaType))
                .execute(stringCallback)
    }

    fun getOkHttp(url: String, callback: BaseCallback) {
        if (!hasNet(callback)) {
            return
        }
        OkGo.get<String>(url).retryCount(2).execute(callback)
    }

    fun downloadFile(downloadPath: String, fileCallback: FileCallback) {
        OkGo.get<File>(downloadPath).retryCount(2).execute(fileCallback)
    }

    fun hasNet(callback: BaseCallback): Boolean {
        if (NetWorkHelper.isNetworkAvailable()) {
            return true
        } else {
            if (Helper.isNotNull(callback)) {
                callback.onError(null)
            }
            return false
        }
    }

}
