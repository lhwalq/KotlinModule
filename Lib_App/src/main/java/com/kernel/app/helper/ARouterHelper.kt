package com.kernel.app.helper

import android.app.Activity
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.kernel.lib.R
import com.kernel.lib.base.BaseConstants
import com.kernel.lib.helper.Helper

object ARouterHelper {


    /**
     * 欢迎页出现
     *
     * @param act
     * @param url
     * @param bundle
     * @param finish 是否关闭当前界面
     */
    fun fadeInActivity(act: Activity, url: String, bundle: Bundle?, finish: Boolean) {
        var bundle = bundle
        if (Helper.isNull(bundle)) {
            bundle = Bundle()
        }
        bundle!!.putString(BaseConstants.ActivityInfo.BUNDLEKEY_ACTIVITYANIMTYPE, BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_ALPHA)
        startActivity(act, url, bundle, finish, android.R.anim.fade_in, android.R.anim.fade_out, 0, 0)
    }

    /**
     * 渐变出现
     *
     * @param act
     * @param url
     * @param bundle
     * @param finish 是否关闭当前界面
     */
    fun alphaActivity(act: Activity, url: String, bundle: Bundle?, finish: Boolean) {
        var bundle = bundle
        if (Helper.isNull(bundle)) {
            bundle = Bundle()
        }
        bundle!!.putString(BaseConstants.ActivityInfo.BUNDLEKEY_ACTIVITYANIMTYPE, BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_ALPHA)
        startActivity(act, url, bundle, finish, R.anim.base_push_alpha_in, R.anim.base_push_alpha_out, 0, 0)
    }

    /**
     * 渐变出现
     *
     * @param act
     * @param url
     * @param bundle
     * @param requestCode
     */
    fun alphaActivityForResult(act: Activity, url: String, bundle: Bundle?, requestCode: Int) {
        var bundle = bundle
        if (Helper.isNull(bundle)) {
            bundle = Bundle()
        }
        bundle!!.putString(BaseConstants.ActivityInfo.BUNDLEKEY_ACTIVITYANIMTYPE, BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_ALPHA)
        startActivityForResult(act, url, bundle, requestCode, R.anim.base_push_alpha_in, R.anim.base_push_alpha_out, 0, 0)
    }

    /**
     * 从左往右返回首页
     *
     * @param act
     * @param toActivity
     */
    fun slideHomeActivity(act: Activity, url: String) {
        ARouter.getInstance()
                .build(url)
                .withTransition(R.anim.base_pull_right_in, R.anim.base_pull_right_out)
                .navigation()
        act.finish()
    }

    /**
     * 从右往左
     *
     * @param act
     * @param url
     * @param bundle
     * @param finish 是否关闭当前界面
     */
    fun slideActivity(act: Activity, url: String, bundle: Bundle?, finish: Boolean) {
        var bundle = bundle
        if (Helper.isNull(bundle)) {
            bundle = Bundle()
        }
        bundle!!.putString(BaseConstants.ActivityInfo.BUNDLEKEY_ACTIVITYANIMTYPE, BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_SLIDE)
        startActivity(act, url, bundle, finish, R.anim.base_push_left_in, R.anim.base_push_left_out, 0, 0)
    }

    /**
     * 从右往左，接收返回数据
     *
     * @param act
     * @param url
     * @param bundle
     * @param requestCode
     */
    fun slideActivityForResult(act: Activity, url: String, bundle: Bundle?, requestCode: Int) {
        var bundle = bundle
        if (Helper.isNull(bundle)) {
            bundle = Bundle()
        }
        bundle!!.putString(BaseConstants.ActivityInfo.BUNDLEKEY_ACTIVITYANIMTYPE, BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_SLIDE)
        startActivityForResult(act, url, bundle, requestCode, R.anim.base_push_left_in, R.anim.base_push_left_out, 0, 0)
    }

    /**
     * 从下往上
     *
     * @param act
     * @param url
     * @param bundle
     * @param finish
     */
    fun pushupActivity(act: Activity, url: String, bundle: Bundle?, finish: Boolean) {
        var bundle = bundle
        if (Helper.isNull(bundle)) {
            bundle = Bundle()
        }
        bundle!!.putString(BaseConstants.ActivityInfo.BUNDLEKEY_ACTIVITYANIMTYPE, BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_PUSHUP)
        startActivity(act, url, bundle, finish, R.anim.base_push_up_in, R.anim.base_push_up_out, 0, 0)
    }

    /**
     * 从上往下
     *
     * @param act
     * @param url
     * @param bundle
     * @param finish
     */
    fun pushHeadActivity(act: Activity, url: String, bundle: Bundle?, finish: Boolean) {
        var bundle = bundle
        if (Helper.isNull(bundle)) {
            bundle = Bundle()
        }
        bundle!!.putString(BaseConstants.ActivityInfo.BUNDLEKEY_ACTIVITYANIMTYPE, BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_PUSHUP)
        startActivity(act, url, bundle, finish, R.anim.base_pull_head_in, R.anim.base_pull_head_out, 0, 0)
    }


    /**
     * 从下往上，接收返回数据
     *
     * @param act
     * @param url
     * @param bundle
     * @param requestCode
     */
    fun pushupActivityForResult(act: Activity, url: String, bundle: Bundle?, requestCode: Int) {
        var bundle = bundle
        if (Helper.isNull(bundle)) {
            bundle = Bundle()
        }
        bundle!!.putString(BaseConstants.ActivityInfo.BUNDLEKEY_ACTIVITYANIMTYPE, BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_PUSHUP)
        startActivityForResult(act, url, bundle, requestCode, R.anim.base_push_up_in, R.anim.base_push_up_out, 0, 0)
    }

    /**
     * 从下往上
     *
     * @param act
     * @param url
     * @param bundle
     * @param finish
     */
    fun centerActivity(act: Activity, url: String, bundle: Bundle?, finish: Boolean) {
        var bundle = bundle
        if (Helper.isNull(bundle)) {
            bundle = Bundle()
        }
        bundle!!.putString(BaseConstants.ActivityInfo.BUNDLEKEY_ACTIVITYANIMTYPE, BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_CENTER)
        startActivity(act, url, bundle, finish, R.anim.base_push_center_in, R.anim.base_push_center_out, 0, 0)
    }

    /**
     * 从下往上，接收返回数据
     *
     * @param act
     * @param url
     * @param bundle
     * @param requestCode
     */
    fun centerActivityForResult(act: Activity, url: String, bundle: Bundle?, requestCode: Int) {
        var bundle = bundle
        if (Helper.isNull(bundle)) {
            bundle = Bundle()
        }
        bundle!!.putString(BaseConstants.ActivityInfo.BUNDLEKEY_ACTIVITYANIMTYPE, BaseConstants.ActivityInfo.ACTIVITYANIMTYPE_CENTER)
        startActivityForResult(act, url, bundle, requestCode, R.anim.base_push_center_in, R.anim.base_push_center_out, 0, 0)
    }


    fun startActivity(act: Activity, url: String, bundle: Bundle?, finish: Boolean, enterAnim: Int, exitAnim: Int, backEnterAnim: Int, backExitAnim: Int) {
        var bundle = bundle
        if (Helper.isNull(bundle)) {
            bundle = Bundle()
        }
        bundle!!.putInt(BaseConstants.ActivityInfo.BUNDLEKEY_BACKENTERANIM, backEnterAnim)
        bundle!!.putInt(BaseConstants.ActivityInfo.BUNDLEKEY_BACKEXITANIM, backExitAnim)

        ARouter.getInstance()
                .build(url)
                .with(bundle)
                .withTransition(enterAnim, exitAnim)
                .navigation(act)

        if (finish) {
            act.finish()
        }
//        act.overridePendingTransition(enterAnim, exitAnim)
    }

    fun startActivityForResult(act: Activity, url: String, bundle: Bundle?, requestCode: Int, enterAnim: Int, exitAnim: Int, backEnterAnim: Int, backExitAnim: Int) {
        var bundle = bundle
        if (Helper.isNull(bundle)) {
            bundle = Bundle()
        }
        bundle!!.putInt(BaseConstants.ActivityInfo.BUNDLEKEY_BACKENTERANIM, backEnterAnim)
        bundle!!.putInt(BaseConstants.ActivityInfo.BUNDLEKEY_BACKENTERANIM, backExitAnim)

        ARouter.getInstance()
                .build(url)
                .with(bundle)
                .withTransition(enterAnim, exitAnim)
                .navigation(act, requestCode)

//        act.overridePendingTransition(enterAnim, exitAnim)
    }

    fun getARouterAny(url: String): Any {
        return ARouter.getInstance()
                    .build(url)
                    .navigation()
    }

}