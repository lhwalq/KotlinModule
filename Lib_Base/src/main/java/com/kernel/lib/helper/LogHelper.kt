package com.kernel.lib.helper

import android.util.Log

/**
 * 输出log isDebug控制
 *
 * @author linhuan on 2014-6-6上午11:26:23
 */
object LogHelper {

    var isDebug = true// 是否打开LOG

    private val applicationTag = "LogHelper"// LOG默认TAG

    private val TAG_CONTENT_PRINT = "%s:%s.%s:%d"

    fun getCurrentStackTraceElement(): StackTraceElement {
        return Thread.currentThread().stackTrace[4]
    }

    fun v(tag: Any, str: Any) {
        if (isDebug) {
            Log.v(applicationTag, getContents(getCurrentStackTraceElement()) + ":" + getString(tag) + ": " + getString(str))
        }
    }

    private fun getString(`object`: Any): String {
        if (Helper.isNull(`object`)) {
            return "Message is Null"
        } else {
            val string = `object`.toString()
            return if (string.length == 0) {
                "Message lenght is 0"
            } else {
                string
            }
        }
    }

    // 打印LOG
    fun trace() {
        if (isDebug) {
            Log.d(applicationTag, getContent(getCurrentStackTraceElement()))
        }
    }

    // 获取LOG
    private fun getContent(trace: StackTraceElement): String {
        return String.format(TAG_CONTENT_PRINT, applicationTag,
                trace.className, trace.methodName,
                trace.lineNumber)
    }

    // 获取LOG
    private fun getContents(trace: StackTraceElement): String {
        return String.format("%s:%s:%d", applicationTag,
                trace.methodName, trace.lineNumber)
    }

    // 打印默认TAG的LOG
    fun traceStack() {
        if (isDebug) {
            traceStack(applicationTag, Log.ERROR)
        }
    }

    // 打印Log当前调用栈信息
    fun traceStack(tag: String, priority: Int) {
        if (isDebug) {
            val stackTrace = Thread.currentThread().stackTrace
            Log.println(priority, tag, stackTrace[4].toString())
            val str = StringBuilder()
            var prevClass: String? = null
            for (i in 5 until stackTrace.size) {
                var className = stackTrace[i].fileName
                val idx = className.indexOf(".java")
                if (idx >= 0) {
                    className = className.substring(0, idx)
                }
                if (Helper.isNull(prevClass) || prevClass != className) {
                    str.append(className.substring(0, idx))
                }
                prevClass = className
                str.append(".").append(stackTrace[i].methodName)
                        .append(":").append(stackTrace[i].lineNumber)
                        .append("->")
            }
            Log.println(priority, tag, str.toString())
        }
    }

    fun v(msg: String) {
        if (isDebug) {
            Log.v(applicationTag, getContents(getCurrentStackTraceElement())
                    + "  ====   " + msg)
        }
    }

    // 指定TAG和指定内容的方法
    fun d(tag: String, msg: String) {
        if (isDebug) {
            Log.d(tag, getContent(getCurrentStackTraceElement()) + "  ====   "
                    + msg)
        }
    }

    // 默认TAG和制定内容的方法
    fun ds(msg: String) {
        if (isDebug) {
            Log.d(applicationTag, getContent(getCurrentStackTraceElement())
                    + "===" + msg)
        }
    }

    fun d(msg: String) {
        if (isDebug) {
            Log.d(applicationTag, getContents(getCurrentStackTraceElement())
                    + "  ====   " + msg)
        }
    }

    // 下面的定义和上面方法相同，可以定义不同等级的Debugger
    fun i(tag: String, msg: String) {
        if (isDebug) {
            Log.i(tag, getContent(getCurrentStackTraceElement()) + "  ====   "
                    + msg)
        }
    }

    fun d(message: String, vararg args: Any) {
        if (isDebug) {
            d(String.format(message, *args))
        }
    }

    fun w(tag: String, msg: String) {
        if (isDebug) {
            Log.w(tag, getContent(getCurrentStackTraceElement()) + "  ====   "
                    + msg)
        }
    }

    fun e(tag: String, msg: String) {
        if (isDebug) {
            Log.e(tag, getContent(getCurrentStackTraceElement()) + "  ====   "
                    + msg)
        }
    }

    // 默认TAG和制定内容的方法
    fun i(msg: String) {
        if (isDebug) {
            Log.i(applicationTag, getContent(getCurrentStackTraceElement())
                    + "  ====   " + msg)
        }
    }

    // 默认TAG和制定内容的方法
    fun w(msg: String) {
        if (isDebug) {
            Log.w(applicationTag, getContent(getCurrentStackTraceElement())
                    + "  ====   " + msg)
        }
    }

    // 默认TAG和制定内容的方法
    fun e(msg: String) {
        if (isDebug) {
            Log.e(applicationTag, getContent(getCurrentStackTraceElement())
                    + "  ====   " + msg)
        }
    }

    fun e(exception: Exception) {
        if (isDebug) {
            Log.e(applicationTag, getContent(getCurrentStackTraceElement())
                    + "  ====   " + exception.message)
            exception.printStackTrace()
        }
    }

    fun e(exception: Exception, string: String) {
        if (isDebug) {
            Log.e(applicationTag, getContent(getCurrentStackTraceElement())
                    + "\n  ====   " + exception.message + "\n  ====   "
                    + exception.stackTrace + "   " + string)
            exception.printStackTrace()
        }
    }

    fun e(string: String, exception: Exception) {
        if (isDebug) {
            Log.e(applicationTag, getContent(getCurrentStackTraceElement())
                    + "\n  ====   " + exception.message + "\n  ====   "
                    + exception.stackTrace + "   " + string)
            exception.printStackTrace()
        }
    }

    fun e(tag: String, message: String, exception: Exception) {
        if (isDebug) {
            Log.e(tag, getContent(getCurrentStackTraceElement())
                    + "\n  ====   " + exception.message + "\n  ====   "
                    + exception.stackTrace + "   " + message)
            exception.printStackTrace()
        }
    }

    fun showLog(str: String) {
        var index = 0
        val maxLength = 3000
        var finalString: String
        while (index < str.length) {
            if (str.length <= index + maxLength) {
                finalString = str.substring(index)
            } else {
                finalString = str.substring(index, index + maxLength)
            }
            index += maxLength
            LogHelper.v(finalString)
        }
    }

}