package com.kernel.app.base

import com.kernel.lib.base.mvp.BasePresenter
import com.kernel.lib.base.mvp.BaseView

/**
 * Created by hp on 2017/10/10.
 */

class AppPresenter<V : BaseView> : BasePresenter<V> {
    override fun attachView(view: V) {

    }

    override fun detachView() {

    }
}
