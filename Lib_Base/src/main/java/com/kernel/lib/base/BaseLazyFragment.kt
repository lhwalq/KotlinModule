package com.kernel.lib.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.kernel.lib.R
import com.kernel.lib.base.mvp.BasePresenter
import com.kernel.lib.base.mvp.BaseView

/**
 * @author linhuan on 2017/4/22 上午10:57
 */
abstract class BaseLazyFragment<V : BaseView, P : BasePresenter<V>> : BaseFragment<V, P>() {

    private var isInit = false// 真正要显示的View是否已经被初始化（正常加载）
    private var savedInstanceState: Bundle? = null
    protected var isLazyLoad = false
    protected var layout: FrameLayout? = null
    private var isStart = false// 是否处于可见状态，in the screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = getArguments()
        isLazyLoad = bundle!!.getBoolean(INTENT_BOOLEAN_LAZYLOAD, isLazyLoad)
    }

    override fun onCreateView(savedInstanceState: Bundle?) {
        super.onCreateView(savedInstanceState)

        // 判断是否懒加载
        if (!isInit) {
            if (isLazyLoad) {
                // 一旦isVisibleToUser==true即可对真正需要的显示内容进行加载
                if (userVisibleHint && !isInit) {
                    this.savedInstanceState = savedInstanceState
                    onCreateViewLazy(savedInstanceState)
                    init()
                    isInit = true
                } else {
                    // 进行懒加载
                    layout = FrameLayout(context!!)
                    layout!!.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
                    val view = LayoutInflater.from(context).inflate(R.layout.base_fragment_lazy_loading, null)
                    showLazy(view.findViewById(R.id.ll_lazy_bg))
                    layout!!.addView(view)
                    super.setContentView(layout!!)
                }
            } else {
                // 不需要懒加载，开门江山，调用onCreateViewLazy正常加载显示内容即可
                onCreateViewLazy(savedInstanceState)
                init()
                isInit = true
            }
        }
    }

    protected fun showLazy(view: View) {
        //        view.setBackgroundResource(R.drawable.game_list_bg);
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        // 一旦isVisibleToUser==true即可进行对真正需要的显示内容的加载

        // 可见，但还没被初始化
        if (isVisibleToUser && !isInit && null != getContentView()) {
            onCreateViewLazy(savedInstanceState)
            init()
            isInit = true
            onResumeLazy()
        }
        // 已经被初始化（正常加载）过了
        if (isInit && null != getContentView()) {
            if (isVisibleToUser) {
                isStart = true
                onFragmentStartLazy()
            } else {
                isStart = false
                onFragmentStopLazy()
            }
        }
    }

    override fun setContentView(layoutResID: Int) {
        // 判断若isLazyLoad==true,移除所有lazy view，加载真正要显示的view
        if (isLazyLoad && getContentView() != null && getContentView()!!.parent != null) {
            layout!!.removeAllViews()
            val view = LayoutInflater.from(context).inflate(layoutResID, layout, false)
            layout!!.addView(view)
        } else {
            super.setContentView(layoutResID)
        }// 否则，开门见山，直接加载
    }

    override fun setContentView(view: View) {
        // 判断若isLazyLoad==true,移除所有lazy view，加载真正要显示的view
        if (isLazyLoad && getContentView() != null && getContentView()!!.parent != null) {
            layout!!.removeAllViews()
            layout!!.addView(view)
        } else {
            super.setContentView(view)
        }// 否则，开门见山，直接加载
    }

    @Deprecated("")
    override fun onStart() {
        super.onStart()
        if (isInit && !isStart && userVisibleHint) {
            isStart = true
            onFragmentStartLazy()
        }
    }

    @Deprecated("")
    override fun onStop() {
        super.onStop()
        if (isInit && isStart && userVisibleHint) {
            isStart = false
            onFragmentStopLazy()
        }
    }

    private fun init() {
        initView()
        initData()
    }

    // 当Fragment被滑到可见的位置时，调用
    protected fun onFragmentStartLazy() {}

    // 当Fragment被滑到不可见的位置，offScreen时，调用
    protected fun onFragmentStopLazy() {}

    protected fun onCreateViewLazy(savedInstanceState: Bundle?) {}

    protected fun onResumeLazy() {}

    protected fun onPauseLazy() {}

    protected fun onDestroyViewLazy() {}

    override fun onResume() {
        super.onResume()
        if (isInit) {
            onResumeLazy()
        }
    }

    override fun onPause() {
        super.onPause()
        if (isInit) {
            onPauseLazy()
        }
    }

    @Deprecated("")
    override fun onDestroyView() {
        super.onDestroyView()
        if (isInit) {
            onDestroyViewLazy()
        }
        isInit = false
    }

    companion object {
        val INTENT_BOOLEAN_LAZYLOAD = "intent_boolean_lazyLoad"
    }

}
