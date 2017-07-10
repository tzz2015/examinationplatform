package com.weiman.exam.examinationplatform.utils

import android.content.Context
import android.content.res.Resources

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
}

