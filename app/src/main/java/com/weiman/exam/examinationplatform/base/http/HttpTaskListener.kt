package com.weiman.exam.examinationplatform.base.http

/**
 * 创建 by 刘宇飞 on 2017/7/15.
 * 邮箱：3494576680@qq.com
 * 微迈科技有限责任公司
 * 描述
 */
interface HttpTaskListener {
    abstract fun onSuccess(requestId: Int, t: Any?)
    abstract fun onException(requestId: Int, code:Int)
}