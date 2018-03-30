package com.kernel.lib.base

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface

/**
 * Created by hp on 2017/11/30.
 */

class BaseDialog : Dialog {

    constructor(context: Activity) : super(context) {}

    constructor(context: Activity, themeResId: Int) : super(context, themeResId) {}

    constructor(context: Activity, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?) : super(context, cancelable, cancelListener) {}
}
