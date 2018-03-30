package com.kernel.app.base

import android.os.Bundle
import android.os.Environment
import com.alibaba.android.arouter.launcher.ARouter
import com.kernel.app.BuildConfig
import com.kernel.app.helper.GlideHelper
import com.kernel.lib.R
import com.kernel.lib.base.BaseApplication
import com.kernel.lib.base.net.OkHttpHelp
import com.kernel.lib.helper.Helper
import com.kernel.lib.helper.LogHelper
import com.kernel.lib.helper.ResourceHelper

//import com.inmobi.sdk.InMobiSdk;


/**
 * @author linhuan on 2016/10/19 上午10:20
 */
open class KernelApplication : BaseApplication() {

    override fun onCreate() {
        sInstance = this
        super.onCreate()

        if (BuildConfig.DEBUG) {
            ARouter.openLog()           // 打印日志
            ARouter.openDebug()         // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.printStackTrace()   // 打印日志的时候打印线程堆栈
        }
        ARouter.init(this)

        OkHttpHelp.initOkHttp()
        GlideHelper.setPlace(0,0,0,0,0,0,0,0)
    }

    companion object {
        lateinit var sInstance: KernelApplication
            private set

        val instance: KernelApplication
            get() {
                if (Helper.isNull(sInstance)) {
                }
                return sInstance
            }
    }

    open fun getSdcard(): String {
        return Environment.getExternalStorageDirectory().absolutePath + ResourceHelper.getString(R.string.base_sdcard_path)
    }

}
