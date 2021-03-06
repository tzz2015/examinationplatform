package com.weiman.exam.examinationplatform.base.http

import java.io.Serializable

/**
 * 公司：
 * 刘宇飞 创建  2017/3/6.
 * 描述：
 */

data class BaseResponse<T>(
        var data: T?=null,
        var code: Int,
        var msg: String,
        var time: Long
) : Serializable

