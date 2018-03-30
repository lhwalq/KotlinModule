package com.kernel.lib.helper

import android.app.Activity
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.os.PowerManager
import android.telephony.TelephonyManager
import com.kernel.lib.base.BaseApplication
import java.io.File

/**
 * function: 设备信息 助手类
 *
 * @author:linhuan
 */
object DeviceHelper {

    // 牌子
    val BRAND_SUMSUNG = "samsung"   // 三星
    val BRAND_LENOVO = "LENOVO"    // 联想
    val BRAND_MEIZU = "Meizu"        // 魅族
    val BRAND_HUAWEI = "Huawei"        // 华为
    private val ROOT = "/root"                // root判断
    private var sScreenSize: IntArray? = null
    private var sWindowsSize: IntArray? = null

    /**
     * function: 取得屏幕尺寸(0:宽度; 1:高度)
     *
     * @return
     *
     * @author:linhuan 2014年7月15日 下午5:50:32
     */
    fun getScreenSize(): IntArray {
        if (Helper.isNull(sScreenSize)) {
            sScreenSize = IntArray(2)
            val dm = BaseApplication.instance.getResources().getDisplayMetrics()
            sScreenSize!![0] = dm.widthPixels
            sScreenSize!![1] = dm.heightPixels
        }
        return sScreenSize!!
    }

    /**
     * function: 取得屏幕宽度
     *
     * @return
     *
     * @author:linhuan 2014年7月15日 下午5:50:38
     */
    fun screenWidth(): Int {
        return getScreenSize()[0]
    }

    /**
     * function: 取得屏幕高度
     *
     * @return
     *
     * @author:linhuan 2014年7月15日 下午5:50:45
     */
    fun getScreenHeight(): Int {
        return getScreenSize()[1]
    }

    /**
     * function: 是否ROOT
     *
     * @return
     *
     * @author:linhuan 2014年7月15日 下午5:54:15
     */
    fun isRoot(): Boolean {
        return File(ROOT).canRead()
    }

    /**
     * function: 取得设备唯一值
     * 默认为deviceId，其次为mac地址，若都获取失败则改用OpenUDID
     *
     * @return
     *
     * @author:linhuan 2014年7月15日 下午5:57:46
     */
    //        if (Helper.isEmpty(result)) {
    //            result = getOpenUDID(context);
    //        }
    fun getDeviceUniqueString(): String {
        var result = getDeviceId()
        if (Helper.isEmpty(result)) {
            result = getMacAddress()
        }
        return result
    }

    /**
     * function: 获取设备id
     *
     * @return
     *
     * @author:linhuan 2014年7月15日 下午5:54:52
     */
    fun getDeviceId(): String {
        try {
            val tm = BaseApplication.instance.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            return tm.deviceId
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }


    /**
     * function: 取得mac地址
     *
     * @return
     *
     * @author:linhuan 2014年7月15日 下午5:56:06
     */
    fun getMacAddress(): String {
        try {
            val wifi = BaseApplication.instance!!.getSystemService(Context.WIFI_SERVICE) as WifiManager
            return wifi.connectionInfo!!.macAddress
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }

    //   /**
    //    * function: 获取OpenUDID
    //    * 确保manifest中application节点有添加OpenUDID的service信息，详情查看doc/说明.txt
    //    *
    //    * @return
    //    *
    //    * @author:linhuan 2014年7月15日 下午5:56:54
    //    */
    //    public static String getOpenUDID() {
    //        if (!OpenUDID_manager.isInitialized()) {
    //            OpenUDID_manager.sync(BaseApplication.getInstance());
    //        }
    //        return OpenUDID_manager.getOpenUDID();
    //    }

    /**
     * function: 得到sdk版本号
     * android.os.Build.VERSION.INCREMENTAL 比如4.0.3
     *
     * @return
     *
     * @author:linhuan 2014年7月16日 下午12:04:55
     */
    fun getSdkVersion(): Int {
        return Build.VERSION.SDK_INT
    }

    /**
     * function: 得到sdk版本号
     * android.os.Build.VERSION.INCREMENTAL 比如4.0.3
     *
     * @return
     *
     * @author:linhuan 2014年7月16日 下午12:04:55
     */
    fun getSdkIncremental(): String {
        return Build.VERSION.RELEASE
    }

    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    fun getSystemModel(): String {
        return Build.MODEL
    }

    /**
     * 是否锁屏
     *
     * @return
     */
    fun isScreenOn(): Boolean {
        try {
            val pm = BaseApplication.instance.getSystemService(Context.POWER_SERVICE) as PowerManager
            return !pm.isScreenOn
        } catch (e: Exception) {
            return false
        }
    }

    /**
     * 获取手机运营商
     *
     * @return
     */
    // 中国移动
    // 中国联通
    // 中国电信
    fun getOperator(): String {
        val telManager = BaseApplication.instance.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val operator = telManager.simOperator
        if (Helper.isNotNull(operator)) {
            if (operator == "46000" || operator == "46002" || operator == "46007") {
                return "ChinaMobile"
            } else if (operator == "46001") {
                return "ChinaUnicom"
            } else if (operator == "46003") {
                return "ChinaTelecom"
            }
        }
        return "ChinaMobile"
    }

    /**
     * function: 取得屏幕尺寸(0:宽度; 1:高度)
     *
     * @return
     *
     * @author:linhuan 2014年7月15日 下午5:50:32
     */
    fun getWindowsSize(act: Activity): IntArray {
        if (Helper.isNull(sWindowsSize)) {
            sWindowsSize = IntArray(2)
            val display = act.windowManager.defaultDisplay
            sWindowsSize!![0] = display.width
            sWindowsSize!![1] = display.height
        }
        return sScreenSize!!
    }

    /**
     * function: 取得屏幕宽度
     *
     * @return
     *
     * @author:linhuan 2014年7月15日 下午5:50:38
     */
    fun getWindowsWidth(act: Activity): Int {
        return getWindowsSize(act)[0]
    }

    /**
     * function: 取得屏幕高度
     *
     * @return
     *
     * @author:linhuan 2014年7月15日 下午5:50:45
     */
    fun getWindowsHeight(act: Activity): Int {
        return getWindowsSize(act)[1]
    }

    /**
     * function: 是否为某个牌子
     *
     * @return
     *
     * @author:linhuan on 2014-5-14上午11:05:04
     */
    fun isBrand(brand: String): Boolean {
        return if (brand.equals(Build.BRAND, ignoreCase = true)) {
            true
        } else {
            false
        }
    }

}
