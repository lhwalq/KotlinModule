package com.kernel.lib.helper

import android.view.Gravity
import android.widget.Toast
import com.kernel.lib.base.BaseApplication

/**
 *
 * function: Toast助手类
 *
 * @ author:linjunying
 */
object ToastHelper {

    lateinit var mToast: Toast
        private set
    private val textviewId = 0

    private val toast: Toast
        get() {
            if (Helper.isNull(mToast)) {
                synchronized(ToastHelper::class) {
                    if (Helper.isNull(mToast)) {
                        mToast = Toast.makeText(BaseApplication.instance, "", Toast.LENGTH_SHORT)
                    }
                }
            }
            return mToast
        }

    /**
     * function: 显示Toast信息(短)
     *
     * @param text 显示文本
     *
     * @author:linhuan 2014年7月15日 下午4:39:45
     */
    fun showToast(text: CharSequence) {
        //    	if (text.toString().startsWith("requset")) {
        //    		text = BaseApplication.getInstance().getString(R.string.hint_networkerror);
        //		}
        showToast(Toast.LENGTH_SHORT, text)
    }

    /**
     * function: 显示Toast信息(短)
     *
     * @param resId 显示文本的资源ID
     * @param formatArgs 字符串格式化参数
     *
     * @author:linhuan 2014年7月15日 下午4:39:23
     */
    fun showToast(resId: Int, vararg formatArgs: Any) {
        showToast(Toast.LENGTH_SHORT, resId, *formatArgs)
    }

    /**
     * function: 显示Toast信息(短)
     *
     * @param resId
     *
     * @author:linhuan 2014-8-1 上午11:59:57
     */
    fun showToast(resId: Int) {
        showToast(Toast.LENGTH_SHORT, ResourceHelper.getString(resId))
    }

    /**
     * function: 显示Toast信息(长)
     *
     * @param text 显示文本
     *
     * @author:linhuan 2014年7月15日 下午4:40:12
     */
    fun showLongToast(text: CharSequence) {
        showToast(Toast.LENGTH_LONG, text)
    }

    /**
     * function: 显示Toast信息(长)
     *
     * @param resId 显示文本的资源ID
     * @param formatArgs 字符串格式化参数
     *
     * @author:linhuan 2014年7月15日 下午4:40:23
     */
    fun showLongToast(resId: Int, vararg formatArgs: Any) {
        showToast(Toast.LENGTH_LONG, resId, *formatArgs)
    }

    /**
     * @param resId
     *
     * @author:linhuan 2014-8-1 上午11:59:51
     */
    fun showLongToast(resId: Int) {
        showToast(Toast.LENGTH_LONG, ResourceHelper.getString(resId))
    }

    /**
     * 显示Toast信息
     *
     * @param duration   时长
     * @param resId      显示文本的资源ID
     * @param formatArgs 字符串格式化参数
     */
    private fun showToast(duration: Int, resId: Int, vararg formatArgs: Any) {
        try {
            showToast(duration, ResourceHelper.getString(resId, formatArgs))
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * function: 显示Toast信息
     *
     * @param duration 时长
     * @param text 显示文本
     *
     * @author:linhuan 2014年7月15日 下午4:41:42
     */
    private fun showToast(duration: Int, text: CharSequence) {
        try {
            val toast = toast
            //            int toastDurationInMilliSeconds = 1000;
            if (Helper.isNotNull(toast) && Helper.isNotEmpty(text.toString().replace(" ".toRegex(), ""))) {
                // 4.0
                //				toast.cancel();
                toast.setText(text)
                toast.duration = Toast.LENGTH_SHORT
                toast.setGravity(Gravity.BOTTOM, 0, 100)
                // Set the countdown to display the toast

                //                if (0 == textviewId) {
                //                    textviewId = Resources.getSystem().getIdentifier("message", "id", "android");
                //                }
                //                ((TextView) toast.getView().findViewById(textviewId)).setGravity(Gravity.CENTER);
                //
                //                CountDownTimer toastCountDown;
                //                toastCountDown = new CountDownTimer(toastDurationInMilliSeconds, 1000 /*Tick duration*/) {
                //                    public void onTick(long millisUntilFinished) {
                //                        toast.show();
                //                    }
                //                    public void onFinish() {
                //                        toast.cancel();
                //                    }
                //                };

                // Show the toast and starts the countdown
                toast.show()
                //                toastCountDown.start();
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}
