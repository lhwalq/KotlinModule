package com.kernel.lib.base

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import com.kernel.lib.base.mvp.BasePresenter
import com.kernel.lib.base.mvp.BaseView
import com.kernel.lib.helper.ResourceHelper

/**
 * BaseFragment 项目基类
 *
 * @author linhuan 2015年7月11日 上午9:08:09
 */
abstract class BaseFragment<V : BaseView, P : BasePresenter<V>> : Fragment() {

    private var contentView: View? = null                                            // 内容
    protected var act: Activity? = null

    var basePresenter: P? = null

    val mvpView: V
        get() = this as V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentView = View(act)

        basePresenter = setPresenter()
        basePresenter?.attachView(mvpView)
    }

    protected abstract fun setPresenter(): P

    fun showViewStub(rootId: Int) {
        val stub = findViewById(rootId) as ViewStub
        stub.inflate()
    }

    fun getViewStub(rootId: Int, childId: Int): View {
        val stub = findViewById(rootId) as ViewStub
        return stub.inflate().findViewById(childId)
    }

    /**
     * setContentView 设置界面
     *
     * @param id 界面ID
     *
     * @author linhuan 2015年7月11日 上午9:17:29
     */
    open fun setContentView(id: Int) {
        setContentView(ResourceHelper.loadLayout(activity!!, id))
    }

    fun getContentView(): View? {
        return contentView
    }

    /**
     * setContentView 设置界面
     *
     * @param view 界面
     *
     * @author linhuan 2015年7月11日 上午9:17:51
     */
    open fun setContentView(view: View) {
        contentView = view
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        onCreateView(savedInstanceState)
        return if (null == contentView) super.onCreateView(inflater, container, savedInstanceState) else contentView
    }

    open protected fun onCreateView(savedInstanceState: Bundle?) {}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        act = activity
    }

    override fun onDestroyView() {
        super.onDestroyView()

        (contentView?.parent as ViewGroup)?.removeView(contentView)
        contentView = null
        act = null
    }

    override fun onDestroy() {
        basePresenter?.detachView()
        super.onDestroy()
    }

    /**
     * findView 读取布局
     *
     * @param id 控件ID
     * @return
     *
     * @author linhuan 2015年7月11日 上午9:20:38
     */
    fun <T : View> findView(id: Int): T {
        return contentView?.findViewById<View>(id) as T
    }

    /**
     * findViewById 读取布局
     *
     * @param id 控件ID
     * @return
     *
     * @author linhuan 2015年7月11日 上午9:21:28
     */
    fun findViewById(id: Int): View {
        return contentView!!.findViewById(id)
    }

    /**
     * function: 设置界面
     *
     * @author:linhuan 2015-1-14 上午9:05:01
     */
    protected fun initView() {}

    /**
     * function: 设置数据
     *
     * @author:linhuan 2015-1-14 上午9:05:01
     */
    protected fun initData() {}

    override fun onDetach() {
        super.onDetach()
        try {
            val childFragmentManager = Fragment::class.java.getDeclaredField("mChildFragmentManager")
            childFragmentManager.isAccessible = true
            childFragmentManager.set(this, null)

        } catch (e: NoSuchFieldException) {
//            throw RuntimeException(e)
        } catch (e: IllegalAccessException) {
//            throw RuntimeException(e)
        }

    }

}
