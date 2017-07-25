package com.weiman.exam.examinationplatform.account.presenter

import android.app.Activity
import com.weiman.exam.examinationplatform.account.bean.LoginInputBean
import com.weiman.exam.examinationplatform.base.http.HttpRequestUtils
import com.weiman.exam.examinationplatform.base.http.HttpTaskListener
import com.weiman.exam.examinationplatform.home.contact.ResetPswActivityContact
import com.weiman.exam.examinationplatform.utils.CommonUtils
import com.weiman.exam.examinationplatform.utils.Constants
import com.weiman.exam.examinationplatform.utils.SharedPreUtil

/**
 * 创建 by 刘宇飞 on 2017/7/22.
 * 邮箱：3494576680@qq.com
 * 微迈科技有限责任公司
 * 描述
 */
class ResetPswActivityPresenter : ResetPswActivityContact.Presenter(), HttpTaskListener {


    override fun onSuccess(requestId: Int, t: Any?) {
        when (requestId) {


        }
    }

    override fun onException(requestId: Int, code: Int) {
        when (requestId) {
            Constants.REQUESTID_0 ->{//发送成功
                if (code==999){
                  CommonUtils.showToast(mContext,"重置成功，请重新登录")
                    (mContext as Activity).finish()
                 /*   var presenter = LoginActivityPresenter()
                    presenter.mContext = mContext;
                    presenter.mHttpTask = mHttpTask
                    presenter.doLogin(SharedPreUtil.getString(mContext,Constants.LACCOUNT, ""), SharedPreUtil.getString(mContext,Constants.LPSW, ""))*/
                }
            }

        }
    }

    /**
     * 重置密码
     */
    override fun postReset(code: String, psw: String) {
        if (CommonUtils.isEmpty(code)) {
            CommonUtils.showToast(mContext, "请输入验证码")
            return
        }
        if (CommonUtils.isEmpty(psw)) {
            CommonUtils.showToast(mContext, "请输入新密码")
            return
        }
        SharedPreUtil.saveString(mContext,Constants.LPSW,psw)
        var email=SharedPreUtil.getString(mContext,Constants.LACCOUNT,"")
        var bean = LoginInputBean(email, psw, code, false)
        HttpRequestUtils.getInstance()
                .setCallBack(this)
                .setContext(mContext)
                .setRequestId(Constants.REQUESTID_0)
                .setObservable(mHttpTask.postResetPsw(bean))
                .create()
    }


}