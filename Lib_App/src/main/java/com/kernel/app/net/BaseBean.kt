package com.kernel.lib.base.net

/**
 * Created by hp on 2017/9/22.
 */

class BaseBean : BaseJson() {

    var error: Int = 0
    var message: String? = null
    var data: String? = null

    override fun toString(): String {
        return "BaseBean{" +
                "error=" + error +
                ", message='" + message + '\''.toString() +
                ", data='" + data + '\''.toString() +
                '}'.toString()
    }
}
