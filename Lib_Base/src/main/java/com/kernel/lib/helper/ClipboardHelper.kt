package com.kernel.lib.helper

import android.content.ClipboardManager
import android.content.Context

/**
 * @author linhuan on 2016/10/21 上午11:07
 */
object ClipboardHelper {

    /**
     * 实现文本复制功能
     * add by wangqianzhou
     * @param content
     */
    fun copy(content: String, context: Context) {
        // 得到剪贴板管理器
        val cmb = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        cmb.text = content.trim { it <= ' ' }
    }

    /**
     * 实现粘贴功能
     * add by wangqianzhou
     * @param context
     * @return
     */
    fun paste(context: Context): String {
        // 得到剪贴板管理器
        val cmb = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        return if (Helper.isNotEmpty(cmb) && Helper.isNotEmpty(cmb.text) && Helper.isNotEmpty(cmb.text.toString())) {
            cmb.text.toString().trim { it <= ' ' }
        } else {
            ""
        }
    }

}
