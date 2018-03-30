package com.kernel.lib.helper

import java.text.DateFormat
import java.text.DateFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * TimeHelper:时间帮助类
 *
 * @author linhuan 2015年7月15日上午9:18:06
 */
object TimeHelper {

    val YYYYMMDD = "yyyy年MM月dd日"
    val MMDD = "MM-dd"
    val YYYYMMDDHHMMSSSSSS = "yyyy-MM-dd'T'HH:mm:ss.SSS"
    val YYYYMMDDTWO = "yyyy-MM-dd"
    val YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss"
    val HHMM = "HH:mm"
    val YYYYMMDDHHMM = "yyyy-MM-dd HH:mm"
    val YYYYMMDDHHMMWRITING = "yyyy年MM月dd日 HH:mm"
    val YYYY = "yyyy"
    val MONTY = "MM"
    val DD = "dd"
    val HH = "HH"
    val MM = "mm"
    val YYYYMMDD_NEW = "yyyyMMdd"
    val MMDD_NEW = "MM.dd"

    /**
     * getTimestamp:获取时间戳
     *
     * @return
     *
     * @author linhuan 2015年7月15日上午9:18:47
     */
    fun getTimestampLong(): Long {
        return System.currentTimeMillis()
    }

    /**
     * getTimestamp:获取时间戳
     *
     * @return
     *
     * @author linhuan 2015年7月15日上午9:18:47
     */
    fun getTimestamp(): Long {
        return System.currentTimeMillis() / 1000
    }

    /**
     * 获取当天0点时间戳
     *
     * @return
     */
    fun getTimesmorning(): Int {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return (cal.timeInMillis / 1000).toInt()
    }

    /**
     * 获取当天0点时间戳
     *
     * @return
     */
    fun getTimesThisYear(): Int {
        val cal = Calendar.getInstance()
        cal.set(Calendar.DAY_OF_MONTH, 1)
        cal.set(Calendar.MONTH, 0)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return (cal.timeInMillis / 1000).toInt()
    }

    /**
     * 时间转换
     *
     * @param time
     * @param oldTime
     * @param newTime
     * @return
     */
    fun timeConversion(time: String, oldTime: String, newTime: String): String {
        try {
            val dff = SimpleDateFormat(oldTime)// 输入的被转化的时间格式
            val df1 = SimpleDateFormat(newTime)// 需要转化成的时间格式
            val date = dff.parse(time)
            return df1.format(date)
        } catch (e: Exception) {
            return ""
        }

    }

    /**
     * date转GMT
     *
     * @param date
     * @return
     */
    fun dateToGMT8(date: Date): String {
        val aLocale = Locale.US
        val fmt = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", DateFormatSymbols(aLocale))
        fmt.timeZone = TimeZone.getTimeZone("GMT")
        return fmt.format(date)
    }

    /**
     * date转GMT
     *
     * @param date
     * @return
     */
    fun dateToGMT(date: Date): String {
        val aLocale = Locale.US
        val fmt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", DateFormatSymbols(aLocale))
        fmt.timeZone = TimeZone.getTimeZone("GMT")
        return fmt.format(date)
    }

    /**
     * date转time
     *
     * @param date
     * @return
     */
    fun dateToTime(date: Date, timeFormat: String): String {
        val format = SimpleDateFormat(timeFormat)
        return format.format(date)
    }

    /**
     * timeToData:时间戳转时间字符串
     *
     * @param timeFormat
     * @return
     *
     * @author linhuan 2015年7月16日上午11:46:21
     */
    fun timeToData(timeFormat: String): String {
        val format = SimpleDateFormat(timeFormat)
        return format.format(Date(System.currentTimeMillis()))
    }

    /**
     * timeToData:时间戳转时间字符串
     *
     * @param time
     * @param timeFormat
     * @return
     *
     * @author linhuan 2015年7月16日上午11:46:21
     */
    fun timeToData(time: Long, timeFormat: String): String {
        val format = SimpleDateFormat(timeFormat)
        return format.format(Date(time * 1000L))
    }

    /**
     * timeToData:时间戳转时间字符串
     *
     * @param time
     * @param timeFormat
     * @return
     *
     * @author linhuan 2015年7月16日上午11:46:21
     */
    fun timeToInt(time: Long, timeFormat: String): Int {
        val format = SimpleDateFormat(timeFormat)
        return Integer.valueOf(format.format(Date(time * 1000L)))
    }

    /**
     * getTime:字符串转时间戳
     *
     * @param timeFormat
     * @return
     *
     * @author linhuan 2015-11-26下午3:25:11
     */
    fun getTime(userTime: String, timeFormat: String): String {
        var re_time = ""
        try {
            val sdf = SimpleDateFormat(timeFormat)
            val d = sdf.parse(userTime)
            val l = d.time
            val str = l.toString()
            re_time = str.substring(0, 10)
        } catch (e: ParseException) {
        }

        return re_time
    }

    /**
     * getTime:字符串转时间戳
     *
     * @param timeFormat
     * @return
     *
     * @author linhuan 2015-11-26下午3:25:11
     */
    fun getTimeLong(userTime: String, timeFormat: String): Long {
        var l: Long = 0
        try {
            val sdf = SimpleDateFormat(timeFormat)
            val d = sdf.parse(userTime)
            l = d.time / 1000
        } catch (e: ParseException) {
        }

        return l
    }

    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    fun strToDate(str: String, timeForma: String): Date? {

        val format = SimpleDateFormat(timeForma)
        var date: Date? = null
        try {
            date = format.parse(str)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return date
    }

    /**
     * 获取周几
     *
     * @param date
     * @return
     */
    fun getWeek(date: Date): String {
        val weeks = arrayOf("周日", "周一", "周二", "周三", "周四", "周五", "周六")
        val cal = Calendar.getInstance()
        cal.time = date
        var week_index = cal.get(Calendar.DAY_OF_WEEK) - 1
        if (week_index < 0) {
            week_index = 0
        }
        return weeks[week_index]
    }

    fun getDatePoor(startDate: Date, endDate: Date): String {

        val nd = (1000 * 24 * 60 * 60).toLong()
        val nh = (1000 * 60 * 60).toLong()
        val nm = (1000 * 60).toLong()
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        val diff = endDate.time - startDate.time
        // 计算差多少天
        val day = diff / nd
        // 计算差多少小时
        val hour = diff % nd / nh
        // 计算差多少分钟
        val min = diff % nd % nh / nm
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;

        return if (0 < day) {
            if (0 < hour) {
                day.toString() + "d" + hour + "h"
            } else {
                day.toString() + "d"
            }
        } else {
            if (0 < hour) {
                if (0 < min) {
                    hour.toString() + "h" + min + "m"
                } else {
                    hour.toString() + "h"
                }
            } else {
                min.toString() + "m"
            }
        }
    }

    fun timeAddMoreDay(time: String, day: Int, timeFormat: String): String {
        try {
            val sdf = SimpleDateFormat(timeFormat)

            val cd = Calendar.getInstance()
            cd.time = sdf.parse(time)
            cd.add(Calendar.DATE, day)                        // 增加一天
            //cd.add(Calendar.MONTH, n);					// 增加一个月

            return sdf.format(cd.time)

        } catch (e: Exception) {
            return time
        }

    }

    fun spitCurrent(endDate: Date, spitDay: Int): Boolean {

        val nd = (1000 * 24 * 60 * 60).toLong()
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        val diff = endDate.time - Date().time
        // 计算差多少天
        val day = diff / nd

        return spitDay > day
    }

    fun spitCurrent(startDate: Date, endDate: String, timeFormat: String): Boolean {
        return spitCurrent(startDate, strToDate(endDate, timeFormat)!!)
    }

    fun spitCurrent(startDate: Date, endDate: Date): Boolean {
        return 0 < endDate.time - startDate.time
    }

}
