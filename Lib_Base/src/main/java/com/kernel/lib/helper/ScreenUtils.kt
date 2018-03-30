package com.kernel.lib.helper

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.Rect
import android.provider.Settings
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager

/**
 * 获得屏幕相关的辅助类
 *
 * @ClassName ScreenUtils
 * @Description TODO(这里用一句话描述这个类的作用)
 * @date 2015年1月5日
 */
object ScreenUtils {

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    fun getScreenWidth(context: Context): Int {
        val outMetrics = DisplayMetrics()
        getDisplay(context).getMetrics(outMetrics)
        return outMetrics.widthPixels
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    fun getScreenHeight(context: Context): Int {
        val outMetrics = DisplayMetrics()
        getDisplay(context).getMetrics(outMetrics)
        return outMetrics.heightPixels
    }

    /**
     * 获取Display
     *
     * @param context
     * @return
     */
    fun getDisplay(context: Context): Display {
        val manager = context
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return manager.defaultDisplay
    }

    /**
     * 设置为竖屏
     * @param activity
     */
    fun setVerticalScreen(activity: Activity) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    /**
     * 设置为横屏
     * @param activity
     */
    fun setHorizontalScreen(activity: Activity) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    /**
     * 获取当前屏幕亮度值（0~255）
     *
     * @param activity
     */
    fun getScreenBrightness(activity: Activity): Int {
        var screenBrightness = 50
        try {
            screenBrightness = Settings.System.getInt(activity.contentResolver, Settings.System.SCREEN_BRIGHTNESS)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return screenBrightness
    }

    /**
     * 保存当前的屏幕亮度值，并使之生效
     *
     * @param activity
     * @param brightnessValue
     * 亮度值（0~255）
     */
    fun setScreenBrightness(activity: Activity, brightnessValue: Int) {
        val localWindow = activity.window
        val localLayoutParams = localWindow.attributes
        if (brightnessValue > 15 && brightnessValue <= 255) {
            val f = brightnessValue / 255.0f
            localLayoutParams.screenBrightness = f// 0-1 -> 暗-亮
            localWindow.attributes = localLayoutParams
        } else if (brightnessValue >= 0 && brightnessValue <= 15) {// 预留6%不会全部变暗
            val f = 15 / 255.0f
            localLayoutParams.screenBrightness = f
            localWindow.attributes = localLayoutParams
        }
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    fun getStatusHeight(context: Context): Int {

        var statusHeight = -1
        try {
            val clazz = Class.forName("com.android.internal.R\$dimen")
            val `object` = clazz.newInstance()
            val height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(`object`).toString())
            statusHeight = context.resources.getDimensionPixelSize(height)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return statusHeight
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity
     * @return
     */
    fun snapShotWithStatusBar(activity: Activity): Bitmap? {
        val view = activity.window.decorView
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        val bmp = view.drawingCache
        val width = getScreenWidth(activity)
        val height = getScreenHeight(activity)
        var bp: Bitmap? = Bitmap.createBitmap(bmp, 0, 0, width, height)
        view.destroyDrawingCache()
        return bp

    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity
     * @return
     */
    fun snapShotWithoutStatusBar(activity: Activity): Bitmap? {
        try {
            val view = activity.window.decorView
            view.isDrawingCacheEnabled = true
            view.buildDrawingCache()
            val bmp = view.drawingCache
            val frame = Rect()
            activity.window.decorView.getWindowVisibleDisplayFrame(frame)
            val statusBarHeight = frame.top
            val width = getScreenWidth(activity)
            val height = getScreenHeight(activity)
            var bp: Bitmap? = Bitmap.createBitmap(bmp, 0, statusBarHeight, width / 2, (height - statusBarHeight) / 2)
            view.destroyDrawingCache()
            return bp
        } catch (e: Exception) {
            return null
        }

    }

}
