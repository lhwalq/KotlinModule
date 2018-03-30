package com.kernel.lib.helper

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import com.kernel.lib.base.BaseApplication
import java.util.*

/**
 * activity管理
 *
 * @author linhuan 2016/1/27 0027 10:51
 */
class AppManager private constructor() {

    fun getActivitySize():Int {
        return activityStack!!.size
    }

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity) {
        if (Helper.isNull(activityStack)) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity? {
        return if (0 == activityStack!!.size) {
            null
        } else activityStack!!.lastElement()
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        val activity = currentActivity()
        finishActivity(activity)
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity?) {
        var activity = activity
        activityStack!!.remove(activity)
        activity!!.finish()
        activity = null
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        for (activity in activityStack!!) {
            if (activity::class == cls) {
                finishActivity(activity)
            }
        }
    }

    /**
     * 判断是否有指定类名的Activity
     */
    fun hasActivity(cls: Class<*>): Boolean {
        if (Helper.isNull(cls)) {
            return false
        }
        for (activity in activityStack!!) {
            if (activity::class == cls) {
                return true
            }
        }
        return false
    }

    /**
     * 结束指定activity外所有Activity
     */
    fun finishAllActivity(cls: Class<*>) {
        var activity: Activity? = null
        for (tempActivity in activityStack!!) {
            if (tempActivity::class == cls) {
                activity = tempActivity
            } else {
                tempActivity!!.finish()
            }
        }
        activityStack!!.clear()
        activityStack!!.add(activity)
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        for (activity in activityStack!!) {
            if (Helper.isNotNull(activity)) {
                activity.finish()
            }
        }
        activityStack!!.clear()
    }

    /**
     * 退出应用程序
     */
    @SuppressLint("MissingPermission")
    fun AppExit() {
        try {
            finishAllActivity()
            val activityMgr = BaseApplication.instance?.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            activityMgr.killBackgroundProcesses(BaseApplication.instance?.getPackageName())
            System.exit(0)
        } catch (e: Exception) {
        }

    }

    companion object {

        var activityStack: Stack<Activity>? = null                                               // Activity栈
        var instance: AppManager? = null                                                         // 单例模式

        /**
         * 单一实例
         */
        val appManager: AppManager
            get() {
                if (Helper.isNull(instance)) {
                    synchronized(AppManager::class) {
                        if (Helper.isNull(instance)) {
                            instance = AppManager()
                            activityStack = Stack()
                        }
                    }
                }
                return instance!!
            }

    }


}