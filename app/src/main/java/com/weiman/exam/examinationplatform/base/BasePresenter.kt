package com.weiman.exam.examinationplatform.base


import android.content.Context


/**
 * 公司：
 * 刘宇飞 创建 on 2017/3/6.
 * 描述：p 基类
 */

abstract class BasePresenter<T> {
    var mContext: Context? = null
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