package lib.com.base

import android.os.Bundle
import com.kernel.app.base.AppBaseCompatActivity
import com.kernel.app.helper.ARouteUrlHelper
import com.kernel.app.helper.ARouterHelper
import com.kernel.lib.base.mvp.BasePresenter
import com.kernel.lib.base.mvp.BaseView
import kotlinx.android.synthetic.main.main_activity_main.*

class MainActivity : AppBaseCompatActivity<BaseView, BasePresenter<BaseView>>() {

    override fun onCreateView(savedInstanceState: Bundle?) {
        setContentView(R.layout.main_activity_main)
        tv_hello.text = "不错"

        tv_hello!!.setOnClickListener {
            ARouterHelper.slideActivity(this, ARouteUrlHelper.Module.MODULE, null, false)
        }
    }

    override fun isSwipeBack(): Boolean {
        return false
    }

}
