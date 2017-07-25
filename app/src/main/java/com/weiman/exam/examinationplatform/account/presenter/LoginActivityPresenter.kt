package com.weiman.exam.examinationplatform.account.presenter

import android.app.Activity
import com.weiman.exam.examinationplatform.account.bean.LoginInputBean
import com.weiman.exam.examinationplatform.account.bean.UserInfoBean
import com.weiman.exam.examinationplatform.base.http.HttpRequestUtils
import com.weiman.exam.examinationplatform.base.http.HttpTaskListener
import com.weiman.exam.examinationplatform.home.contact.LoginActivityContact
import com.weiman.exam.examinationplatform.utils.CommonUtils
import com.weiman.exam.examinationplatform.utils.Constants
import com.weiman.exam.examinationplatform.utils.SharedPreUtil

/**
 * 创建 by 刘宇飞 on 2017/7/22.
 * 邮箱：3494576680@qq.com
 * 微迈科技有限责任公司
 * 描述
 */
class LoginActivityPresenter : LoginActivityContact.Presenter(), HttpTaskListener {

    /**
     * 登录
     */
    override fun doLogin(account: String, psw: String) {
        if (CommonUtils.isEmpty(psw) || CommonUtils.isNotEmpty(psw) && psw.length < 6) {
            CommonUtils.showToast(mContext, "请输入大于六位数的密码")
            return
        }
        if (!CommonUtils.isMobile(account) && !CommonUtils.isEmail(account)) {
            CommonUtils.showToast(mContext, "请输入手机号码或者邮箱")
            return
        }
        SharedPreUtil.saveString(mContext,Constants.LACCOUNT,account)
        SharedPreUtil.saveString(mContext,Constants.LPSW,psw)
        var loginInputBean = LoginInputBean(account, psw, "android")
        HttpRequestUtils.getInstance()
                .setContext(mContext)
                .setRequestId(10)
                .setCallBack(this)
                .setObservable(mHttpTask.requestLogin(loginInputBean))
                .create()

    }


    override fun onSuccess(requestId: Int, t: Any?) {
        when (requestId) {
            10 -> {
                (t as UserInfoBean).saveAccount()
                (mContext as Activity).finish()
            }


        }
    }

    override fun onException(requestId: Int, code: Int) {
    }


}