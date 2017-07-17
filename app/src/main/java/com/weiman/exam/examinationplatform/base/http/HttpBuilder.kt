package com.weiman.exam.examinationplatform.base.http

import android.content.Context
import rx.Observable
import rx.Subscription

/**
 * 创建 by 刘宇飞 on 2017/7/15.
 * 邮箱：3494576680@qq.com
 * 微迈科技有限责任公司
 * 描述
 */
interface HttpBuilder {
    abstract fun setRequestId(requestId: Int): HttpBuilder

    abstract fun setContext(context: Context?): HttpBuilder

    abstract fun setObservable(observable: Observable<*>): HttpBuilder

    abstract fun create(): Subscription?

    abstract fun setCallBack(listener: HttpTaskListener): HttpBuilder
}