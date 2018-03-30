package com.kernel.lib.base

/**
 * function: 基础常量
 *
 * @author:linhuan
 */
class BaseConstants {

    // 切换动画
    object ActivityInfo {

        // activity切换动画类型
        val ACTIVITYANIMTYPE_ALPHA = "alpha"
        val ACTIVITYANIMTYPE_SLIDE = "slide"
        val ACTIVITYANIMTYPE_PUSHUP = "pushup"
        val ACTIVITYANIMTYPE_CENTER = "center"

        // bundle key
        val BUNDLEKEY_ACTIVITYANIMTYPE = "KEY_ACTIVITYANIMTYPE"
        val BUNDLEKEY_BACKENTERANIM = "KEY_BACKENTERANIM"
        val BUNDLEKEY_BACKEXITANIM = "KEY_BACKEXITANIM"

    }
}
