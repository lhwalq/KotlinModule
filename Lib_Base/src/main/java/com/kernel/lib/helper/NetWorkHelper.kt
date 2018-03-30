package com.kernel.lib.helper

import android.content.Context
import android.location.LocationManager
import android.net.ConnectivityManager
import android.telephony.TelephonyManager
import com.kernel.lib.base.BaseApplication
import java.net.Inet4Address
import java.net.Inet6Address
import java.net.NetworkInterface
import java.net.SocketException

/**
 *
 * function: 网络助手类
 *
 * @author:linhuan
 */
object NetWorkHelper {

    /**
     * KEY:网络传输用,user-agent1
     */
    val NETWORK_KEY_USER_AGENT1 = "User-Agent1"

    val TYPE_IP_V4: Short = 4
    val TYPE_IP_V6: Short = 6

    /**
     * 检查网络连接是否可用
     *
     * @return
     */
    fun isNetworkAvailable(): Boolean {
        try {
            val cm = BaseApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Helper.isNull(cm)) {
                return false
            }
            val netinfo = cm.allNetworkInfo
            if (Helper.isNull(netinfo)) {
                return false
            }
            val size = netinfo.size
            for (i in 0 until size) {
                if (netinfo[i].isConnected) {
                    return true
                }
            }
        } catch (e : Exception) {

        }
        return false
    }

    /**
     * 获取当前的网络状态 ：没有网络-0：WIFI网络1：4G网络-4：3G网络-3：2G网络-2
     * 自定义
     *
     * @return
     */
    //结果返回值
    //获取手机所有连接管理对象
    //获取NetworkInfo对象
    //NetworkInfo对象为空 则代表没有网络
    //否则 NetworkInfo对象不为空 则获取该networkInfo的类型
    //WIFI
    //3G   联通的3G为UMTS或HSDPA 电信的3G为EVDO
    //2G 移动和联通的2G为GPRS或EGDE，电信的2G为CDMA
    fun apnType(): Int {
        var netType = 0
        try {
            val manager = BaseApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = manager.activeNetworkInfo ?: return netType
            val nType = networkInfo.type
            if (nType == ConnectivityManager.TYPE_WIFI) {
                netType = 1
            } else if (nType == ConnectivityManager.TYPE_MOBILE) {
                val nSubType = networkInfo.subtype
                val telephonyManager = BaseApplication.instance.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                if (nSubType == TelephonyManager.NETWORK_TYPE_LTE && !telephonyManager.isNetworkRoaming) {
                    netType = 4
                } else if (nSubType == TelephonyManager.NETWORK_TYPE_UMTS
                        || nSubType == TelephonyManager.NETWORK_TYPE_HSDPA
                        || nSubType == TelephonyManager.NETWORK_TYPE_EVDO_0 && !telephonyManager.isNetworkRoaming) {
                    netType = 3
                } else if (nSubType == TelephonyManager.NETWORK_TYPE_GPRS
                        || nSubType == TelephonyManager.NETWORK_TYPE_EDGE
                        || nSubType == TelephonyManager.NETWORK_TYPE_CDMA && !telephonyManager.isNetworkRoaming) {
                    netType = 2
                } else {
                    netType = 2
                }
            }
        } catch (e : Exception) {

        }
        return netType
    }

    /**
     * 获取当前ip
     * @param ipType ipv4或者ipv6，请使用[.TYPE_IP_V4]或者[.TYPE_IP_V6]
     * @return 当前ip
     */
    fun getLocalIpAddress(ipType: Short): String? {
        try {
            val en = NetworkInterface.getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val intf = en.nextElement()
                val enumIpAddr = intf.inetAddresses
                while (enumIpAddr.hasMoreElements()) {
                    val inetAddress = enumIpAddr.nextElement()
                    if (!inetAddress.isLoopbackAddress) {
                        when (ipType) {

                            TYPE_IP_V4 -> if (inetAddress is Inet4Address) {
                                return inetAddress.getHostAddress().toString()
                            }

                            TYPE_IP_V6 -> if (inetAddress is Inet6Address) {
                                return inetAddress.getHostAddress().toString()
                            }

                            else -> {
                            }
                        }
                    }
                }
            }
        } catch (ex: SocketException) {
            ex.printStackTrace()
        }

        return null
    }

    /**
     * 判断WIFI网络是否可用
     *
     * @param context
     * @return
     */
    fun isWifiConnected(context: Context): Boolean {
        try {
            // 获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
            val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            // 获取NetworkInfo对象
            val networkInfo = manager.activeNetworkInfo
            //判断NetworkInfo对象是否为空 并且类型是否为WIFI
            if (networkInfo != null && networkInfo.type == ConnectivityManager.TYPE_WIFI) {
                return networkInfo.isAvailable
            }
        } catch (e : Exception) {

        }
        return false
    }

    /**
     * 判断MOBILE网络是否可用
     *
     * @param context
     * @return
     */
    fun isMobileConnected(context: Context): Boolean {
        try {
            //获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
            val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            //获取NetworkInfo对象
            val networkInfo = manager.activeNetworkInfo
            //判断NetworkInfo对象是否为空 并且类型是否为MOBILE
            if (networkInfo != null && networkInfo.type == ConnectivityManager.TYPE_MOBILE)
                return networkInfo.isAvailable
        } catch (e : Exception) {

        }
        return false
    }

    /**
     * 获取当前网络连接的类型信息
     * 原生
     *
     * @param context
     * @return
     */
    fun getConnectedType(context: Context): Int {
        try {
            //获取手机所有连接管理对象
            val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            //获取NetworkInfo对象
            val networkInfo = manager.activeNetworkInfo
            if (networkInfo != null && networkInfo.isAvailable) {
                //返回NetworkInfo的类型
                return networkInfo.type
            }
        } catch (e : Exception) {

        }
        return -1
    }

    /**
     * 判断GPS是否打开
     * ACCESS_FINE_LOCATION权限
     * @param context
     * @return
     */
    fun isGPSEnabled(context: Context): Boolean {
        //获取手机所有连接LOCATION_SERVICE对象
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

}
