package com.kernel.lib.helper

import org.json.JSONObject

import java.util.ArrayList
import java.util.Date
import java.util.Random

object Helper {

    /**
     * function: 判断对象是否为Null
     *
     * @param obj
     * @return
     *
     * @author:linhuan 2014年7月15日 下午2:28:26
     */
    fun isNull(obj: Any?): Boolean {
        return null == obj
    }

    /**
     * function: 判断对象不为Null
     *
     * @param obj
     * @return
     *
     * @author:linhuan 2014年7月15日 下午2:29:36
     */
    fun isNotNull(obj: Any): Boolean {
        return !isNull(obj)
    }

    /**
     * function: 若判断对象为null输出""
     *
     * @param obj
     * @return
     *
     * @author:linhuan 2014年7月15日 下午3:45:11
     */
    fun ifNull(obj: Any): String {
        return Helper.ifNull(obj, "")
    }

    /**
     * function: 若判断对象为null输出默认值
     *
     * @param obj
     * @param defaultValue
     * @return
     *
     * @author:linhuan 2014年7月15日 下午3:45:06
     */
    fun ifNull(obj: Any, defaultValue: String): String {
        return if (isNull(obj)) {
            defaultValue
        } else {
            obj.toString()
        }
    }

    /**
     * function: 判断对象是否为Null或空，注意：不能判断包含数组
     *
     * @param obj
     * @return
     *
     * @author:linhuan 2014年7月15日 下午2:30:38
     */

    fun isEmpty(obj: Any): Boolean {
        var result = false
        if (isNull(obj)) {
            result = true
        } else {
            if (obj is String) {
                result = obj == ""
            } else if (obj is Date) {
                result = obj.time == 0L
            } else if (obj is Long) {
                result = obj.toLong() == java.lang.Long.MIN_VALUE
            } else if (obj is Int) {
                result = obj.toInt() == Integer.MIN_VALUE
            } else if (obj is Collection<*>) {
                result = obj.size == 0
            } else if (obj is Map<*, *>) {
                result = obj.size == 0
            } else if (obj is JSONObject) {
                result = !obj.keys().hasNext()
            } else {
                result = obj.toString() == ""
            }// 无法判断基本类型
            //			else if(obj.getClass().isArray()) {
            //				result = ((Object[])obj).length == 0;
            //			}
        }

        return result
    }

    /**
     * function: 判断对象不为Null或空，注意：不能判断包含数组
     *
     * @param obj
     * @return
     *
     * @author:linhuan 2014年7月15日 下午2:30:38
     */
    fun isNotEmpty(obj: Any): Boolean {
        return !isEmpty(obj)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // 分隔线：各种类型转换
    ////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * function: 字符串转换为int
     *
     * @param str
     * @return
     *
     * @author:linhuan 2014年7月15日 下午3:40:50
     */
    fun str2Int(str: String): Int {
        var result = 0
        try {
            result = Integer.valueOf(str)
        } catch (ex: Exception) {
            result = Integer.MIN_VALUE
        }

        return result
    }

    /**
     * function: 字符串转换int
     *
     * @param str
     * @param defaultValue 默认值
     * @return
     *
     * @author:linhuan 2014年7月15日 下午3:41:34
     */
    fun str2Int(str: String, defaultValue: Int): Int {
        var result = 0
        try {
            result = Integer.valueOf(str)
        } catch (ex: Exception) {
            result = defaultValue
        }

        return result
    }

    /**
     * function: 字符串转换为long
     *
     * @param str
     * @return
     *
     * @author:linhuan 2014年7月15日 下午3:42:42
     */
    fun str2Long(str: String): Long {
        var result: Long = 0
        try {
            result = java.lang.Long.valueOf(str)
        } catch (ex: Exception) {
            result = java.lang.Long.MIN_VALUE
        }

        return result
    }

    /**
     * function: 字符串转换为float
     *
     * @param str
     * @return
     *
     * @author:linhuan 2014年7月15日 下午3:43:09
     */
    fun str2Float(str: String): Float {
        var result = 0f
        try {
            result = java.lang.Float.valueOf(str)!!
        } catch (ex: Exception) {
            result = java.lang.Float.MIN_VALUE
        }

        return result
    }

    /**
     * function: 字符串转换为double
     *
     * @param str
     * @return
     *
     * @author:linhuan 2014年7月15日 下午3:43:38
     */
    fun str2Double(str: String): Double {
        var result = 0.0
        try {
            result = java.lang.Double.valueOf(str)!!
        } catch (ex: Exception) {
            result = java.lang.Double.MIN_VALUE
        }

        return result
    }

    /**
     * function: 浮点数转换整数
     *
     * @param f
     * @return
     *
     * @author:linhuan 2014年7月15日 下午3:46:29
     */
    fun float2Int(f: Float): Int {
        return Math.round(f)
    }

    /**
     * function: double转换整数
     *
     * @param d
     * @return
     *
     * @author:linhuan 2014年7月15日 下午3:46:36
     */
    fun double2Int(d: Double): Int {
        return java.lang.Long.valueOf(Math.round(d)).toInt()
    }


    ////////////////////////////////////////////////////////////////////////////////////////////
    // 分隔线：equal判断
    ////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * function: 判断两字符串是否相等(区分大小写)
     *
     * @param str1
     * @param str2
     * @param ignorSpace 是否忽略空格
     * @return
     *
     * @author:linhuan 2014年7月15日 下午4:02:40
     */
    fun equalString(str1: String, str2: String, ignorSpace: Boolean): Boolean {
        return equalString(str1, str2, ignorSpace, false)
    }

    /**
     * function: 判断两字符串是否相等(包含对null的判断)
     *
     * @param str1
     * @param str2
     * @param ignorSpace 是否忽略空格
     * @param ignorCase 是否忽略大小写
     * @return
     *
     * @author:linhuan 2014年7月15日 下午4:01:31
     */
    fun equalString(str1: String, str2: String, ignorSpace: Boolean, ignorCase: Boolean): Boolean {
        if (isNull(str1) && isNull(str2)) {
            return true
        }
        if (isNull(str1) && isNotNull(str2) || isNull(str2) && isNotNull(str1)) {
            return false
        }
        return if (ignorSpace) {
            if (ignorCase) {
                str1.trim { it <= ' ' }.toLowerCase() == str2.trim { it <= ' ' }.toLowerCase()
            } else {
                str1.trim { it <= ' ' } == str2.trim { it <= ' ' }
            }
        } else {
            if (ignorCase) {
                str1.toLowerCase() == str2.toLowerCase()
            } else {
                str1 == str2
            }
        }
    }

}
