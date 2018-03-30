package com.base.download.app

import android.os.Environment
import com.kernel.app.base.KernelApplication
import com.kernel.lib.R
import com.kernel.lib.helper.Helper
import com.kernel.lib.helper.LogHelper
import com.kernel.lib.helper.ResourceHelper

/**
 * @author linhuan on 2016/10/19 上午10:20
 */
open class DownloadApplication : KernelApplication() {

    override fun onCreate() {
        sInstance = this
        super.onCreate()

        LogHelper.isDebug = false
    }

    companion object {
        lateinit var sInstance: DownloadApplication
            private set

        val instance: DownloadApplication
            get() {
                if (Helper.isNull(sInstance)) {
                }
                return sInstance
            }
    }

    override fun getSdcard(): String {
        return Environment.getExternalStorageDirectory().absolutePath + ResourceHelper.getString(R.string.base_sdcard_path)
    }

}
