package com.weiman.exam.examinationplatform.base


import android.content.Context
import com.weiman.exam.examinationplatform.base.http.HttpTask


/**
 * 公司：
 * 刘宇飞 创建 on 2017/3/6.
 * 描述：p 基类
 */

abstract class BasePresenter<T> {
    lateinit var mContext: Context
    lateinit var mHttpTask: HttpTask
    var mView: T? = null


    fun setView(v: T) {
        this.mView = v
        this.onStart()
    }


    fun onStart() {}
    fun onDestroy() {
        mView = null

    }
}