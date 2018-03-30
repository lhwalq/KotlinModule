package com.kernel.app.base

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import com.kernel.lib.base.BaseLazyFragment
import com.kernel.lib.base.mvp.BasePresenter
import com.kernel.lib.base.mvp.BaseView
import com.kernel.lib.base.net.BaseJson
import com.kernel.lib.helper.Helper
import com.kw.rxbus.RxBus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

/**
 * AppFragment:项目基类
 *
 * @author linhuan 2015年7月21日上午9:17:51
 */
abstract class AppLazyFragment<V : BaseView, P : BasePresenter<V>> : BaseLazyFragment<V, P>(), BaseView {

    private var disposable: Disposable? = null
    private var currentFragment: Fragment? = null

    override fun onResume() {
        super.onResume()

//        if (openMobclick()) {
//            MobclickAgent.onPageStart(getClass().getSimpleName())
//        }
    }

    override fun onPause() {
        super.onPause()

//        if (openMobclick()) {
//            MobclickAgent.onPageEnd(getClass().getSimpleName())
//        }
    }

    protected fun openMobclick(): Boolean {
        return true
    }

    protected fun openBus() {
        disposable = RxBus.getInstance().register<BaseJson>(BaseJson::class.java, AndroidSchedulers.mainThread()) { baseJson -> rxBusAccept(baseJson) } as Nothing?
    }

    protected fun rxBusAccept(baseJson: BaseJson) {

    }

    override fun onDestroy() {
        super.onDestroy()
        if (null != disposable) {
            RxBus.getInstance().unregister(disposable)
            disposable = null
        }
    }

    protected fun switchFragment(@IdRes viewId: Int, targetFragment: Fragment) {
        val transaction = getChildFragmentManager().beginTransaction()
        if (Helper.isNull(currentFragment)) {
            transaction
                    .add(viewId, targetFragment)
                    .commit()
        } else if (!targetFragment.isAdded) {
            transaction
                    .hide(currentFragment)
                    .add(viewId, targetFragment)
                    .commit()
        } else {
            transaction
                    .hide(currentFragment)
                    .show(targetFragment)
                    .commit()
        }
        currentFragment = targetFragment
    }

}
