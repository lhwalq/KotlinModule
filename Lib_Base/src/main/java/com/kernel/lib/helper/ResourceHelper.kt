package com.kernel.lib.helper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kernel.lib.base.BaseApplication

/**
 * function: 与resource资源、R相关
 *
 * @author:linhuan
 */
object ResourceHelper {

    // 获取状态栏高度
    fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = BaseApplication.instance.getResources().getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = BaseApplication.instance.getResources().getDimensionPixelSize(resourceId)
        }
        return result
    }

    /**
     * sp转成px
     * @param spValue
     * @return
     * @author dengxiumao
     */
    fun Sp2Px(spValue: Float): Int {
        val fontScale = BaseApplication.instance.getResources().getDisplayMetrics().scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    /**
     * function:dp转成px
     *
     * @param dp
     * @return
     *
     * @author:linhuan 2015-5-12 下午9:10:33
     */
    fun Dp2Px(dp: Float): Int {
        val scale = BaseApplication.instance.getResources().getDisplayMetrics().density
        return (dp * scale + 0.5f).toInt()
    }

    /**
     * function: dip转成px
     *
     * @param dip
     * @return
     *
     * @author:linhuan 2014年8月28日 上午9:40:05
     */
    fun getPxFromDip(dip: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, BaseApplication.instance.getResources().getDisplayMetrics()).toInt()
    }

    /**
     * function: sp转成px
     *
     * @param sp
     * @return
     *
     * @author:linhuan 2014年8月28日 上午9:39:40
     */
    fun getPxFromSp(sp: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, BaseApplication.instance.getResources().getDisplayMetrics()).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(pxValue: Float): Int {
        val scale = BaseApplication.instance.getResources().getDisplayMetrics().density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * function: 取得资源字符串
     *
     * @param resId 资源id
     * @return 字符串
     *
     * @author:linhuan 2014年7月15日 下午4:21:38
     */
    fun getString(resId: Int): String {
        return BaseApplication.instance.getString(resId)
    }

    /**
     * function: 取得资源字符串
     *
     * @param resId 资源id
     * @return 字符串
     *
     * @author:linhuan 2014年7月15日 下午4:21:38
     */
    fun getStringArray(resId: Int): Array<String> {
        return BaseApplication.instance.getResources().getStringArray(resId)
    }

    /**
     * function: 取得资源字符串(格式化字符串)
     *
     * @param resId 资源id
     * @param formatArgs 格式化参数
     * @return
     *
     * @author:linhuan 2014年7月15日 下午4:21:53
     */
    fun getString(resId: Int, vararg formatArgs: Any): String {
        return BaseApplication.instance.getString(resId, formatArgs)
    }

    /**
     * function: 取得资源字符串(格式化字符串)
     *
     * @param resId 资源id
     * @return
     *
     * @author:linhuan 2014年7月15日 下午4:21:53
     */
    fun getInterger(resId: Int): Int {
        return BaseApplication.instance.getResources().getInteger(resId)
    }

    /**
     * function: 取得指定名称的bitmap
     *
     * @param imageName 图片名称
     * @return 图片对象
     *
     * @author:linhuan 2014年7月15日 下午4:24:08
     */
    fun getBitmapByName(imageName: String): Bitmap? {
        var result: Bitmap? = null
        try {
            val resId = getImageResId(imageName)
            if (resId > 0) {
                result = BitmapFactory.decodeResource(BaseApplication.instance.getResources(), resId)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return result
    }

    /**
     * function: 取得指定名称的Drawable
     *
     * @param imageName 图片名称
     * @return
     *
     * @author:linhuan 2014年7月15日 下午4:24:54
     */
    fun getDrawableByName(imageName: String): Drawable? {
        var result: Drawable? = null
        try {
            val resId = getImageResId(imageName)
            if (resId > 0) {
                result = BaseApplication.instance.getResources().getDrawable(resId)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return result
    }

    /**
     * function: 获取平铺背景
     *
     * @param resId 资源id
     * @return
     *
     * see: http://stackoverflow.com/questions/7586209/xml-drawable-bitmap-tilemode-bug
     *
     * @author:linhuan 2014年7月15日 下午4:30:42
     */
    fun getRepeatBackground(resId: Int): Drawable {
        val bitmap = BitmapFactory.decodeResource(BaseApplication.instance.getResources(), resId)
        val drawable = BitmapDrawable(BaseApplication.instance.getResources(), bitmap)
        drawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
        drawable.setDither(true)
        return drawable
    }

    /**
     * function: 取得指定名称的资源id
     *
     * @param idName 资源名
     * @return
     *
     * @author:linhuan 2014年7月15日 下午4:36:03
     */
    fun getIdResId(idName: String): Int {
        return getResId(idName, "id")
    }

    /**
     * function: 取得指定名称的字符串资源ID
     *
     * @param stringName 字符串名称
     * @return
     *
     * @author:linhuan 2014年7月15日 下午4:34:48
     */
    fun getStringResId(stringName: String): Int {
        return getResId(stringName, "string")
    }

    /**
     * function: 取得指定名称的图片资源ID
     *
     * @param imageName 图片名称
     * @return
     *
     * @author:linhuan 2014年7月15日 下午4:23:21
     */
    fun getImageResId(imageName: String): Int {
        return getResId(imageName, "drawable")
    }

    /**
     * function: 取得指定名称的布局资源id
     *
     * @param layoutName 布局名字
     * @return
     *
     * @author:linhuan 2014年7月15日 下午4:34:59
     */
    fun getLayoutResId(layoutName: String): Int {
        return getResId(layoutName, "layout")
    }

    /**
     * function: 取得指定名称的动画资源id
     *
     * @param animName 动画名字
     * @return
     *
     * @author:linhuan 2014年7月15日 下午4:35:17
     */
    fun getAnimResId(animName: String): Int {
        return getResId(animName, "anim")
    }

    /**
     * function: 取得指定名称的样式id
     *
     * @param styleableString 样式名
     * @return
     *
     * @author:linhuan 2014年7月15日 下午4:36:26
     */
    fun getStyleableResId(styleableString: String): Int {
        return getResId(styleableString, "styleable")
    }

    /**
     * function: 通过名字和类型取得res id
     *
     * @param resName
     * @param type
     * @return
     *
     * @author:linhuan 2014年7月15日 下午4:34:12
     */
    private fun getResId(resName: String, type: String): Int {
        return BaseApplication.instance.getResources()
                .getIdentifier(resName, type, BaseApplication.instance.getPackageName())
    }

    /**
     * function: 取得指定名称的数组资源
     *
     * @param arrayName 数组名称
     * @return 数组
     *
     * @author:linhuan 2014年7月16日 上午10:34:00
     */
    fun getStringArray(arrayName: String): Array<String> {
        val r = BaseApplication.instance.getResources()
        return r.getStringArray(r.getIdentifier(arrayName, "array", BaseApplication.instance.getPackageName()))
    }

    /**
     * 从名称获取id，没有该资源返回-1
     *
     * @param name 资源名
     * @param clazz 资源类型  R.drawable.class
     * @return
     *
     * @author:linhuan on 2014-6-6上午10:54:57
     */
    fun getId(name: String, clazz: Class<*>): Int {
        var id: Int
        try {
            val field = clazz.getField(name)
            id = field.get(null) as Int
        } catch (e: Exception) {
            e.printStackTrace()
            id = -1
        }

        return id
    }

    /**
     * 获取本地资源
     *
     * @param imgId
     * @return
     */
    fun getDrawable(imgId: Int): String {
        return "drawable://$imgId"
    }

    /**
     * 加载布局
     *
     * @param context
     * @param layoutId
     * @return
     *
     * @author:linhuan on 2014-6-9上午11:17:08
     */
    fun loadLayout(context: Context, layoutId: Int, gp: ViewGroup? = null, attach: Boolean = Helper.isNotNull(gp!!)): View {
        return LayoutInflater.from(context).inflate(layoutId, gp, attach)
    }

}
