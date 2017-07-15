package com.weiman.exam.examinationplatform.utils

import android.util.Log

/**
 * 创建 by 刘宇飞 on 2017/7/11.
 * 邮箱：3494576680@qq.com
 * 描述
 */
class LogUtil  private constructor(){


    private val TAG = "TCircle"

    //public static int logLevel = Log.ASSERT;

    var logLevel = Log.ERROR
    companion object {
        fun getInstance() = Holder.instance
    }

    private object Holder {
        var instance = LogUtil()
    }

    private fun getFunctionName(): String? {
        val sts = Thread.currentThread().stackTrace ?: return null
        for (st in sts) {
            if (st.isNativeMethod) {
                continue
            }
            if (st.className == Thread::class.java.name) {
                continue
            }
            if (st.className == this.javaClass.name) {
                continue
            }
            val className = st.className
            val indexOf = className.lastIndexOf(".")
            return "[类名:" + st.className.substring(indexOf + 1, className.length) + "--方法名:" + st.methodName+"第" + st.lineNumber + "行 ]"
        }
        return null
    }

    fun i(str: Any) {
        // if (!AppConf.DEBUG)
        // return;
        if (logLevel <= Log.INFO) {
            val name = getFunctionName()
            if (name != null) {
                Log.i(TAG, name + " - " + str)
            } else {
                Log.i(TAG, str.toString())
            }
        }
    }

    fun v(str: Any) {
        // if (!AppConfig.DEBUG)
        // return;
        if (logLevel <= Log.VERBOSE) {
            val name = getFunctionName()
            if (name != null) {
                Log.v(TAG, name + " - " + str)
            } else {
                Log.v(TAG, str.toString())
            }
        }
    }

    fun w(str: Any) {
        // if (!AppConfig.DEBUG)
        // return;
        if (logLevel <= Log.WARN) {
            val name = getFunctionName()
            if (name != null) {
                Log.w(TAG, name + " - " + str)
            } else {
                Log.w(TAG, str.toString())
            }
        }
    }

    fun e(str: Any) {
        // if (!AppConfig.DEBUG)
        // return;
        if (logLevel <= Log.ERROR) {
            val name = getFunctionName()
            if (name != null) {
                Log.e(TAG, name + " - " + str)
            } else {
                Log.e(TAG, str.toString())
            }
        }
    }

    fun e(ex: Exception) {
        // if (!AppConfig.DEBUG)
        // return;
        if (logLevel <= Log.ERROR) {
            Log.e(TAG, "error", ex)
        }
    }

    fun d(str: Any) {
        // if (!AppConfig.DEBUG)
        // return;
        if (logLevel <= Log.DEBUG) {
            val name = getFunctionName()
            if (name != null) {
                Log.d(TAG, name + " - " + str)
            } else {
                Log.d(TAG, str.toString())
            }
        }
    }
}