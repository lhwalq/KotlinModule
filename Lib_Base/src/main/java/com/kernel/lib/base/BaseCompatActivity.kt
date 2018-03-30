package com.kernel.lib.base

import android.app.Activity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.view.ViewStub
import com.jaeger.library.StatusBarUtil
import com.kernel.lib.R
import com.kernel.lib.base.mvp.BasePresenter
import com.kernel.lib.base.mvp.BaseView
import com.kernel.lib.helper.AppManager
import com.kernel.lib.helper.Helper
import com.kernel.lib.helper.NavigationHelper
import me.imid.swipebacklayout.lib.SwipeBackLayout
import me.imid.swipebacklayout.lib.Utils
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper

/**
 * 项目基类
 *
 * Created by Administrator on 2016/3/22.
 */
abstract class BaseCompatActivity<V : BaseView, P : BasePresenter<V>> : AppCompatActivity(), SwipeBackActivityBase, SwipeBackLayout.SwipeProcess {

    private var backEnterAnim = 0
    private var backExitAnim = 0
    private var activityAnimType = ""

    // 手势后退layout
    private var mHelper: SwipeBackActivityHelper? = null

    var basePresenter: P? = null

    val mvpView: V
        get() = this as V

    override fun getSwipeBackLayout(): SwipeBackLayout {
        return mHelper!!.swipeBackLayout
    }

    override fun scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this@BaseCompatActivity)
        swipeBackLayout.scrollToFinishActivity()
    }

    override fun setSwipeBackEnable(enable: Boolean) {
        swipeBackLayout.setEnableGesture(enable)
    }

    override fun beforeFinishActivity(act: Activity) {
        setResultForSwipeBack(act)
    }


    /**
     * 是否开启手势返回，默认true
     *
     * @return
     */
    open protected fun isSwipeBack(): Boolean {
       return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.appManager.addActivity(this)

        if (isSwipeBack()) {
            mHelper = SwipeBackActivityHelper(this)
            mHelper!!.onActivityCreate()

            // 添加手势返回View
            //        getSwipeBackLayout().setEdgeSize(DeviceHelper.getScreenWidth());
            swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT)
            getSwipeBackLayout().swipeProcess = this
        }

        onCreateView(savedInstanceState)

        basePresenter = setPresenter()
        basePresenter?.attachView(mvpView)

        getAnimParams()
        initView()
        initData()
    }

    fun showViewStub(rootId: Int) {
        val stub = findViewById(rootId) as ViewStub
        stub.inflate()
    }

    fun getViewStub(rootId: Int, childId: Int): View {
        val stub = findViewById(rootId) as ViewStub
        return stub.inflate().findViewById(childId)
    }

    open protected fun setPresenter(): P? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()

        basePresenter?.detachView()
        basePresenter = null
        mHelper = null
        AppManager.appManager.finishActivity(this)
    }

    protected fun setStatusBar(colorId: Int) {
        StatusBarUtil.setColor(this, resources.getColor(colorId))
    }

    protected fun setStatusBarTransparent() {
        StatusBarUtil.setTransparent(this)
    }

    protected fun setStatusBarTranslucent(statusBarAlpha: Int) {
        StatusBarUtil.setTranslucent(this, statusBarAlpha)
    }

    protected fun setColorForDrawerLayout(drawerLayout: DrawerLayout, colorId: Int) {
        StatusBarUtil.setColorForDrawerLayout(this, drawerLayout, resources.getColor(colorId))
    }

    protected fun setTranslucentForImageView(statusBarAlpha: Int, needOffsetView: View) {
        StatusBarUtil.setTranslucentForImageView(this, statusBarAlpha, needOffsetView)
    }

    protected fun setColorForSwipeBack(colorId: Int, statusBarAlpha: Int) {
        StatusBarUtil.setColorForSwipeBack(this, resources.getColor(colorId), statusBarAlpha)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        if (isSwipeBack()) {
            mHelper?.onPostCreate()
        }
    }

    /**
     * function: 获取动画参数
     *
     * @author linhuan 2014年7月18日 上午12:01:46
     */
    private fun getAnimParams() {
        val intent = intent
        if (Helper.isNotNull(intent)) {
            if (intent.hasExtra(BaseConstants.ActivityInfo.BUNDLEKEY_ACTIVITYANIMTYPE)) {
                activityAnimType = intent.getStringExtra(BaseConstants.ActivityInfo.BUNDLEKEY_ACTIVITYANIMTYPE)
            }
            if (intent.hasExtra(BaseConstants.ActivityInfo.BUNDLEKEY_BACKENTERANIM)) {
                backEnterAnim = intent.getIntExtra(BaseConstants.ActivityInfo.BUNDLEKEY_BACKENTERANIM, 0)
            }
            if (intent.hasExtra(BaseConstants.ActivityInfo.BUNDLEKEY_BACKEXITANIM)) {
                backExitAnim = intent.getIntExtra(BaseConstants.ActivityInfo.BUNDLEKEY_BACKEXITANIM, 0)
            }
        }
    }

    /**
     * function: 设置后退动画(finish时调用)
     *
     * @author linhuan 2014年7月18日 上午1:24:17
     */
    fun setBackAnim() {
        if (BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_SLIDE.equals(activityAnimType)) {
            overridePendingTransition(R.anim.base_pull_right_in, R.anim.base_pull_right_out)
        } else if (BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_PUSHUP.equals(activityAnimType)) {
            overridePendingTransition(R.anim.base_pull_bottom_in, R.anim.base_pull_bottom_out)
        } else if (BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_CENTER.equals(activityAnimType)) {
            overridePendingTransition(R.anim.base_pull_center_in, R.anim.base_pull_center_out)
        } else if (BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_ALPHA.equals(activityAnimType)) {
            overridePendingTransition(R.anim.base_pull_alpha_in, R.anim.base_pull_alpha_out)
        } else {
            overridePendingTransition(backEnterAnim, backExitAnim)
        }
    }

    /**
     * onCreateView:初始化界面
     *
     * @author linhuan 2016/1/27 0027 11:27
     */
    protected abstract fun onCreateView(savedInstanceState: Bundle?)

    override fun onBackPressed() {
        doBack(-1, null)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (KeyEvent.KEYCODE_BACK == keyCode && 0 == event.repeatCount) { // 按下的如果是BACK，同时没有重复
            doBack(keyCode, event)
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    /**
     * function: 设置界面
     *
     * @author:linhuan 2015-1-14 上午9:05:01
     */
    protected fun initView() {

    }

    /**
     * function: 设置数据
     *
     * @author:linhuan 2015-1-14 上午9:05:01
     */
    protected fun initData() {

    }

    /**
     * function: 后退处理
     *
     * @param keyCode
     * @param event
     *
     * @author:linhuan 2014年8月5日 下午7:59:01
     */
    fun doBack(keyCode: Int, event: KeyEvent?) {
        toFinish()
        NavigationHelper.finish(this, Activity.RESULT_OK, null)
    }

    protected fun toFinish() {}

    /**
     *
     * function: 后势滑动前设置结果(NavigationHelper.setResult)，与doBack不同的是不用自己调finish
     *
     *
     * @ author:linjunying 2014年8月13日 下午3:47:02
     */
    protected fun setResultForSwipeBack(act: Activity) {}

}
