package com.kernel.app.base

import android.annotation.SuppressLint
import com.kernel.lib.base.BaseWebViewActivity
import com.kernel.lib.base.mvp.BasePresenter
import com.kernel.lib.base.mvp.BaseView

/**
 * Created by hp on 2017/10/13.
 */

abstract class AppWebViewActivity<V : BaseView, P : BasePresenter<V>> : BaseWebViewActivity<V, P>() {

    @SuppressLint("MissingSuperCall")
    override fun onResume() {
        super.onResume()

//        if (openMobclick()) {
//            StatService.onResume(this)
//            StatService.trackBeginPage(this, getPackageName())
//            MobclickAgent.onResume(this)
//            MobclickAgent.onPageStart(getClass().getSimpleName())
//        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onPause() {
        super.onPause()

//        if (openMobclick()) {
//            StatService.onPause(this)
//            StatService.trackEndPage(this, getPackageName())
//            MobclickAgent.onPause(this)
//            MobclickAgent.onPageEnd(getClass().getSimpleName())
//        }
    }

    protected fun openMobclick(): Boolean {
        return true
    }

}
