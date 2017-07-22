package com.weiman.exam.examinationplatform.home.presenter

import com.weiman.exam.examinationplatform.account.bean.LoginInputBean
import com.weiman.exam.examinationplatform.account.bean.UserInfoBean
import com.weiman.exam.examinationplatform.base.http.HttpRequestUtils
import com.weiman.exam.examinationplatform.base.http.HttpTaskListener
import com.weiman.exam.examinationplatform.home.contact.HomeFragmentContact

/**
 * 创建 by 刘宇飞 on 2017/7/13.
 * 邮箱：3494576680@qq.com
 * 微迈科技有限责任公司
 * 描述
 */
class HomeFragmentPresenter : HomeFragmentContact.Presenter(), HttpTaskListener {
    override fun getData() {
        var loginInputBean = LoginInputBean("13743291@qq.com","123456", "android")
        HttpRequestUtils.getInstance()
                .setContext(mContext)
                .setRequestId(10)
                .setCallBack(this)
                .setObservable(mHttpTask.requestLogin(loginInputBean))
                .create()
    }

    override fun onSuccess(requestId: Int, t: Any?) {
        when (requestId) {
            10 ->
                doWithAccount(t as UserInfoBean)

        }
    }

    /**
     * 解析返回数据
     */
    private fun doWithAccount(userInfoBean: UserInfoBean) {
        userInfoBean.saveAccount()
        mView?.setBackDate(userInfoBean.email)
    }

    override fun onException(requestId: Int, code: Int) {

    }

}