package com.kernel.app.helper

import com.alibaba.fastjson.JSON

/**
 * @author linhuan on 2017/8/3 下午11:43
 */
object JsonHelper {

    fun <T> parseObject(text: String, clazz: Class<T>): T {
        return JSON.parseObject(text, clazz)
    }

    fun <T> parseArray(text: String, clazz: Class<T>): List<T> {
        return JSON.parseArray(text, clazz)
    }

}
