package com.kernel.lib.base.mvp

/**
 * @author linhuan on 2017/8/3 下午10:36
 */
interface BasePresenter<V : BaseView> {

    fun attachView(view: V)

    fun detachView()

}
