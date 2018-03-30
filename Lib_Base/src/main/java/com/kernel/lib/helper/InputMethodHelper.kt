package com.kernel.lib.helper

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.kernel.lib.base.BaseApplication

/**
 * function: 输入法控制
 *
 * @author:linhuan 2011-11-10
 */
object InputMethodHelper {

    /**
     * function: 如输入法关闭则打开
     *
     * @author:linhuan 2011-11-10 下午03:15:44
     */
    fun openInputMethod(view: View) {
        view.requestFocus()
        val imm = BaseApplication.instance.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }

    /**
     * function: 如输入法打开则关闭
     *
     * @param act
     *
     * @author:linhuan 2011-11-10 下午03:27:59
     */
    fun closeInputMethod(act: Activity) {
        if (Helper.isNotNull(act.currentFocus)) {
            val imm = act.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(act.currentFocus.windowToken, 0)
        }
    }

    /**
     * function: 切换输入法的状态
     *
     * @author:linhuan 2011-11-10 下午03:16:25
     */
    fun toggleInputMethod() {
        val imm = BaseApplication.instance.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
    }

}