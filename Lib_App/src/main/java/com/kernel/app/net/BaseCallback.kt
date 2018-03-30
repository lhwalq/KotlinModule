package com.kernel.lib.base.net

import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response

/**
 * Created by hp on 2017/9/22.
 */

class BaseCallback(private val baseInterface: BaseInterface?) : StringCallback() {

    override fun onError(response: Response<String>?) {
        baseInterface?.onError(0, "")
    }

    override fun onSuccess(response: Response<String>?) {
        baseInterface?.onSuccess(BaseBean())
    }

    interface BaseInterface {
        fun onError(error: Int, message: String)
        fun onSuccess(baseBean: BaseBean)
    }

}
