package com.weiman.exam.examinationplatform.base.http

import android.content.Context
import com.weiman.exam.examinationplatform.App
import com.weiman.exam.examinationplatform.utils.CommonUtils
import com.weiman.exam.examinationplatform.utils.LogUtil
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 创建 by 刘宇飞 on 2017/7/15.
 * 邮箱：3494576680@qq.com
 * 微迈科技有限责任公司
 * 描述
 */
class HttpRequestUtils private constructor() : HttpBuilder {
    private var observable: Observable<*>? = null
    private var requestId: Int = 0
    private var listener: HttpTaskListener? = null
    private var context: Context? = null

    companion object {
        fun getInstance(): HttpRequestUtils = HttpRequestUtils()
    }

    override fun setRequestId(requestId: Int): HttpBuilder {
        this.requestId = requestId
        return this
    }

    override fun setContext(context: Context?): HttpBuilder {
        this.context = context
        return this
    }

    override fun setObservable(observable: Observable<*>): HttpBuilder {
        this.observable = observable
        return this
    }


    override fun setCallBack(listener: HttpTaskListener): HttpBuilder {
        this.listener = listener
        return this
    }

    override fun create(): Subscription? {
        if (observable == null) {
            LogUtil.getInstance().e("请设置observable")
            return null
        } else {
            return request()
        }
    }

    private fun request(): Subscription? {
        //避免没有必要的请求
        if (CommonUtils.isNetworkConnected(App.context)) {
            CommonUtils.showToast(App.context, "无网络连接,请检查网络")
            return null
        }
        if (context != null) {
            CommonUtils.showInfoProgressDialog(context as Context)
        }
        val subscribe = observable!!
                .subscribeOn(Schedulers.newThread())//请求网络在子线程中
                .observeOn(AndroidSchedulers.mainThread())//回调在主线程中
                .subscribe(
                        {
                            baseResponse ->
                            if (baseResponse is BaseResponse<*>) {
                                if (baseResponse.code==0) {
                                    if (baseResponse.data != null) {
                                        listener?.onSuccess(requestId, baseResponse.data)
                                    } else
                                        listener?.onException(requestId, 999)
                                } else{
                                    listener?.onException(requestId, 0)
                                    CommonUtils.showToast(CommonUtils.getContext(),baseResponse.msg)
                                }

                            }
                            CommonUtils.hideInfoProgressDialog()
                        },
                        {
                            t ->
                            LogUtil.getInstance().e(t.message.toString())
                            listener?.onException(requestId, 0)
                            CommonUtils.hideInfoProgressDialog()
                        },
                        {
                            CommonUtils.hideInfoProgressDialog()
                        }

                )
        //activity 或者fragment销毁时 必须销毁所有的请求 不然容易导致空指针
        CommonUtils.addSubscription(subscribe)

        return subscribe
    }
}










