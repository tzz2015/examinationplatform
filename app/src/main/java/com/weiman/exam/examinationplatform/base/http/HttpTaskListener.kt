package com.weiman.exam.examinationplatform.base.http

/**
 * 创建 by 刘宇飞 on 2017/7/15.
 * 邮箱：3494576680@qq.com
 * 微迈科技有限责任公司
 * 描述
 */
interface HttpTaskListener {
    /**
     * 请求成功
     */
     fun onSuccess(requestId: Int, t: Any?)

    /**
     * 请求失败
     */
     fun onException(requestId: Int, code:Int)
}