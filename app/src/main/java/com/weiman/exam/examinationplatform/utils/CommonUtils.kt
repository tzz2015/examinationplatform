package com.weiman.exam.examinationplatform.utils

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.content.res.Resources
import android.net.ConnectivityManager
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import com.kaopiz.kprogresshud.KProgressHUD
import com.weiman.exam.examinationplatform.App
import rx.Subscription
import rx.subscriptions.CompositeSubscription

/**
 * Created by jingbin on 2016/11/22.
 * 获取原生资源
 */
object CommonUtils {



    /**
     * 获取资源颜色
     */
    fun getColor(context:Context, color:Int):Int{
        return getResources(context).getColor(color)
    }

    /**
     * 获取资源
     */
    fun getResources(context:Context):Resources{
        return context.resources
    }

    /**
     * 字符串是否为空

     * @param input
     * *
     * @return
     */
    fun isEmpty(input: String?): Boolean {
        if (input == null || "" == input || "null" == input)
            return true

        for (i in 0..input.length - 1) {
            val c = input[i]
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false
            }
        }
        return true
    }

    /**
     * 判断字符串是否为空

     * @param value
     * *
     * @return
     */
    fun isNotEmpty(value: String?): Boolean {
        return !isEmpty(value)
    }

    private var toast: Toast? = null
    fun showToast(context: Context, title: String) {
        if (toast == null) {
            toast = Toast.makeText(context, title, Toast.LENGTH_SHORT)
            toast!!.setGravity(Gravity.CENTER, 0, 0)
            toast!!.show()
        } else {
            toast!!.setText(title)
            toast!!.show()
        }
    }

    fun getContext():Context{
        return App.context
    }

    /**
     * 显示进度框
     * @param str
     */
    private var mProgressDialog: KProgressHUD? = null//进度窗体

    fun showInfoProgressDialog(context: Context, vararg str: String) {
        if (mProgressDialog == null) {
            mProgressDialog = KProgressHUD(context)
            mProgressDialog!!.setCancellable(true)
        }
        if (str.isEmpty()) {
            mProgressDialog!!.setLabel("加载中...")
        } else {
            mProgressDialog!!.setLabel(str[0])
        }

        if (!mProgressDialog!!.isShowing) {
            mProgressDialog!!.show()
        }
    }

    /**
     * 隐藏等待条
     */
    fun hideInfoProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog!!.dismiss()
            mProgressDialog = null
        }
    }

    /**
     * 添加网络请求观察者
     * @param s
     */
    private var mCompositeSubscription: CompositeSubscription? = null //网络管理器

    fun addSubscription(s: Subscription?) {
        if (s == null) {
            return
        }
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = CompositeSubscription()
        }
        this.mCompositeSubscription!!.add(s)
    }

    /**
     * 移除网络请求
     */
    fun removeSubscription() {
        if (this.mCompositeSubscription != null && mCompositeSubscription!!.hasSubscriptions()) {
            this.mCompositeSubscription!!.unsubscribe()
            this.mCompositeSubscription = null
        }
    }

    /**
     * 设置文本

     * @param value
     * *
     * @return
     */
    fun setTextValue(textView: TextView, value: String) {
        if (isNotEmpty(value))
            textView.text = value
        else {
                textView.text = ""
        }
    }

    /**
     * 判断网络是否连通
     */
    fun isNetworkConnected(context: Context?): Boolean {
        try {
            if (context != null) {
                val cm = context
                        .getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
                val info = cm.activeNetworkInfo
                return info != null && info.isConnected
            } else {
                /**如果context为空，就返回false，表示网络未连接 */
                return false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }


    }

    /**
     * 传入map，转换成符合格式的url请求

     * @param map
     * *
     * @return
     */
    fun map2String(map: Map<String, String>): String {
        val sb = StringBuilder("{")
        for ((key, value) in map) {
            val s1 = "$key:'$value',"
            sb.append(s1)
        }
        val s = sb.toString()
        val s1 = s.substring(0, s.length - 1)
        val result = s1 + "}"
        return result
    }

    fun startActivity(t: Class<*>, map: Map<String, String>?) {
        val intent = Intent(getContext(), t)

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        if (map != null) {
            for (key in map.keys) {
                intent.putExtra(key, map[key])
            }
        }
        getContext().startActivity(intent)
    }


}

