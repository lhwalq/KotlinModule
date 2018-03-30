package com.kernel.lib.base

import android.support.multidex.MultiDexApplication

import com.kernel.lib.helper.Helper

/**
 * function: Application的基类
 *
 * @author:linhuan
 */
open class BaseApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        sInstance = this
    }

    companion object {

        lateinit var sInstance: BaseApplication
            private set

        val instance: BaseApplication
            get() {
                if (Helper.isNull(sInstance)) {
                }
                return sInstance
            }
    }

}
