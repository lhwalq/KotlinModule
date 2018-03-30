package com.kernel.app.base

import android.annotation.SuppressLint
import android.os.Build
import com.kernel.lib.base.BaseCompatActivity
import com.kernel.lib.base.mvp.BasePresenter
import com.kernel.lib.base.mvp.BaseView
import com.kernel.lib.base.net.BaseJson
import com.kw.rxbus.RxBus
import com.zhy.m.permission.MPermissions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

/**
 * Created by Administrator on 2016/3/22.
 */
abstract class AppBaseCompatActivity<V : BaseView, P : BasePresenter<V>> : BaseCompatActivity<V, P>() {

    private var hasRequestPermiss = false
    private var disposable: Disposable? = null

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

    protected fun openBus() {
        disposable = RxBus.getInstance().register<BaseJson>(BaseJson::class.java, AndroidSchedulers.mainThread()) { baseJson -> rxBusAccept(baseJson) }
    }

    override protected fun onDestroy() {
        super.onDestroy()

        if (null != disposable) {
            RxBus.getInstance().unregister(disposable)
            disposable = null
        }
    }

    protected fun rxBusAccept(baseJson: BaseJson) {

    }

    protected fun requestPermission(requestCode: Int, permission: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!MPermissions.shouldShowRequestPermissionRationale(this, permission, requestCode)) {
                hasRequestPermiss = true
                MPermissions.requestPermissions(this, requestCode, permission)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (hasRequestPermiss) {
            MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults)
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}
