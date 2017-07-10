package com.example.a11829.kotlindome.utils

import android.content.Context
import android.view.Gravity
import android.widget.Toast

/**
 * 杭州融科网络
 *  刘宇飞创建 on 2017/5/25.
 * 描述：显示在中间的toast
 */


fun Context.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    val mToast = Toast.makeText(this, msg, length)
        mToast.setGravity(Gravity.CENTER, 0, 0)
        mToast.show()

}
