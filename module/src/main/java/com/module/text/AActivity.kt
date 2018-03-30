package com.module.text

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.kernel.app.base.AppBaseCompatActivity
import com.kernel.app.helper.ARouteUrlHelper
import com.kernel.lib.base.mvp.BasePresenter
import com.kernel.lib.base.mvp.BaseView

import kotlinx.android.synthetic.main.module_activity_main.*

@Route(path = ARouteUrlHelper.Module.MODULE)
class AActivity : AppBaseCompatActivity<BaseView, BasePresenter<BaseView>>() {

    override fun onCreateView(savedInstanceState: Bundle?) {
        setContentView(R.layout.module_activity_main)
        tv_hello.text = "这个"
    }

}
