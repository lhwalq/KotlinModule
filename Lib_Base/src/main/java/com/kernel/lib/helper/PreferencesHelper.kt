package com.kernel.lib.helper

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Base64
import com.kernel.lib.base.BaseApplication
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

/**
 * function: SharedPreferences辅助类
 *
 * 说明：可操作多个SharedPreferences,取得时通过getInstance方法进行选择
 *
 * @author:linhuan
 */
class PreferencesHelper {

    private var mSharePreferences: SharedPreferences? = null

    /**
     * contains:是否有数据
     *
     * @param key
     * @return
     *
     * @author linhuan 2015年7月15日上午9:22:20
     */
    operator fun contains(key: String): Boolean {
        return mSharePreferences!!.contains(key)
    }

    /**
     * function: 获取String类型
     *
     * @param key
     * @return
     *
     * @author:linhuan 2014年7月15日 下午5:16:54
     */
    fun getString(key: String): String {
        return mSharePreferences!!.getString(key, "")
    }

    /**
     * function: 获取String类型
     *
     * @param key
     * @param def
     * @return
     *
     * @author:linhuan 2014年7月15日 下午5:17:01
     */
    fun getString(key: String, def: String): String? {
        return mSharePreferences!!.getString(key, def)
    }

    /**
     * function: 获取float类型
     *
     * @param key
     * @return
     *
     * @author:linhuan 2014年7月15日 下午5:17:10
     */
    fun getFloat(key: String): Float {
        return mSharePreferences!!.getFloat(key, 0f)
    }

    /**
     * function: 获取float类型
     *
     * @param key
     * @param def
     * @return
     *
     * @author:linhuan 2014年7月15日 下午5:17:18
     */
    fun getFloat(key: String, def: Float): Float {
        return mSharePreferences!!.getFloat(key, def)
    }

    /**
     * function: 获取int类型
     *
     * @param key
     * @return
     *
     * @author:linhuan 2014年7月15日 下午5:17:27
     */
    fun getInt(key: String): Int {
        return mSharePreferences!!.getInt(key, 0)
    }

    /**
     * function: 获取int类型
     *
     * @param key
     * @param def
     * @return
     *
     * @author:linhuan 2014年7月15日 下午5:17:34
     */
    fun getInt(key: String, def: Int): Int {
        return mSharePreferences!!.getInt(key, def)
    }

    /**
     * function: 获取long类型
     *
     * @param key
     * @return
     *
     * @author:linhuan 2014年7月15日 下午5:17:42
     */
    fun getLong(key: String): Long {
        return mSharePreferences!!.getLong(key, 0)
    }

    /**
     * function: 获取long类型
     *
     * @param key
     * @param def
     * @return
     *
     * @author:linhuan 2014年7月15日 下午5:17:56
     */
    fun getLong(key: String, def: Long): Long {
        return mSharePreferences!!.getLong(key, def)
    }

    /**
     * function: 获取boolean类型
     *
     * @param key
     * @return
     *
     * @author:linhuan 2014年7月15日 下午5:18:05
     */
    fun getBoolean(key: String): Boolean {
        return mSharePreferences!!.getBoolean(key, false)
    }

    /**
     * function: 获取boolean类型
     *
     * @param key
     * @param def
     * @return
     *
     * @author:linhuan 2014年7月15日 下午5:18:13
     */
    fun getBoolean(key: String, def: Boolean): Boolean {
        return mSharePreferences!!.getBoolean(key, def)
    }

    /**
     * function: 设置String类型
     *
     * @param key
     * @param value
     *
     * @author:linhuan 2014年7月15日 下午5:18:19
     */
    fun putString(key: String, value: String) {
        val ed = mSharePreferences!!.edit()
        ed.putString(key, value)
        ed.commit()
    }

    /**
     * function: 设置Int类型
     *
     * @param key
     * @param value
     *
     * @author:linhuan 2014年7月15日 下午5:19:01
     */
    fun putInt(key: String, value: Int) {
        val ed = mSharePreferences!!.edit()
        ed.putInt(key, value)
        ed.commit()
    }

    /**
     * function: 设置Long类型
     *
     * @param key
     * @param value
     *
     * @author:linhuan 2014年7月15日 下午5:19:09
     */
    fun putFloat(key: String, value: Float) {
        val ed = mSharePreferences!!.edit()
        ed.putFloat(key, value)
        ed.commit()
    }

    /**
     * function: 设置Long类型
     *
     * @param key
     * @param value
     *
     * @author:linhuan 2014年7月15日 下午5:19:17
     */
    fun putLong(key: String, value: Long) {
        val ed = mSharePreferences!!.edit()
        ed.putLong(key, value)
        ed.commit()
    }

    /**
     * function: 设置Boolean类型
     *
     * @param key
     * @param value
     *
     * @author:linhuan 2014年7月15日 下午5:19:23
     */
    fun putBoolean(key: String, value: Boolean) {
        val ed = mSharePreferences!!.edit()
        ed.putBoolean(key, value)
        ed.commit()
    }

    /**
     * 获取用户数据
     *
     * @param key
     * @return null 说明空  每一个数据都要序列化
     *
     * @author linhuan on 2014-6-6下午3:43:42
     */
    fun getObject(key: String): Any? {
        val passwordinbase64 = mSharePreferences!!.getString(key, null)
        var `object`: Any? = null
        if (Helper.isNotNull(passwordinbase64!!)) {
            val base64Bytes = Base64.decode(passwordinbase64, Base64.DEFAULT)
            val bais = ByteArrayInputStream(base64Bytes)
            var ois: ObjectInputStream? = null

            try {
                ois = ObjectInputStream(bais)
                `object` = ois.readObject()
            } catch (e: Exception) {

            }

        }
        return `object`
    }

    /**
     * 保存本次用户信息
     *
     * @param key
     * @param object 每一个数据都要序列化
     *
     * @author linhuan on 2014-6-6下午3:44:25
     */
    fun putObject(key: String, `object`: Any) {
        val ed = mSharePreferences!!.edit()
        val toByte = ByteArrayOutputStream()
        var oos: ObjectOutputStream? = null

        try {
            oos = ObjectOutputStream(toByte)
            oos.writeObject(`object`)
        } catch (e: Exception) {

        }

        //对byte[]进行Base64编码
        val PasswordMapBase64 = String(Base64.encode(toByte.toByteArray(), Base64.DEFAULT))
        ed.putString(key, PasswordMapBase64)

        // 提交保存
        ed.commit()
    }

    /**
     * function: 清除
     *
     * @author:linhuan 2014年7月15日 下午5:19:30
     */
    fun clear() {
        if (Helper.isNull(mSharePreferences)) {
            val ed = mSharePreferences!!.edit()
            ed.clear()
            ed.commit()
        }
    }

    companion object {

        private var sInstance = PreferencesHelper()

        private var sLastSharedPreferencesName: String? = null

        /**
         * function: 取得实例
         *
         * @return
         *
         * @author:linhuan 2014年7月15日 下午5:13:00
         */
        val instance: PreferencesHelper
            get() = instance

        /**
         * function: 通过名称取得实例
         *
         * @param sharedPreferencesName 文件名称
         * @return
         *
         * @author:linhuan 2014年7月15日 下午5:13:04
         */
        fun getInstance(sharedPreferencesName: String): PreferencesHelper {
            if (Helper.isNull(sInstance)) {
                synchronized(PreferencesHelper::class.java) {
                    if (Helper.isNull(sInstance)) {
                        sInstance = PreferencesHelper()
                    }
                }
            }
            var useDefault = true

            if (Helper.isNull(sInstance.mSharePreferences) || Helper.isEmpty(sharedPreferencesName) && Helper.isEmpty(sLastSharedPreferencesName!!)) {
                useDefault = Helper.isEmpty(sharedPreferencesName)
            } else if (!Helper.equalString(sharedPreferencesName, sLastSharedPreferencesName!!, true)) {
                useDefault = Helper.isEmpty(sharedPreferencesName)
            } else {
                return sInstance
            }

            if (useDefault) {
                sInstance.mSharePreferences = PreferenceManager.getDefaultSharedPreferences(BaseApplication.instance)
                sLastSharedPreferencesName = null
            } else {
                sInstance.mSharePreferences = BaseApplication.instance.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)
                sLastSharedPreferencesName = sharedPreferencesName
            }
            return sInstance
        }
    }

}
