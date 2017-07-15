package com.weiman.exam.examinationplatform

import android.app.Application
import android.content.Context

/**
 * 创建 by 刘宇飞 on 2017/7/10.
 * 邮箱：3494576680@qq.com
 * 描述
 */
open class App : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
       lateinit var context: Context
    }

}


