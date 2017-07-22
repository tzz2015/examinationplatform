package com.weiman.exam.examinationplatform.account.presenter

import com.weiman.exam.examinationplatform.account.bean.LoginInputBean
import com.weiman.exam.examinationplatform.base.http.HttpRequestUtils
import com.weiman.exam.examinationplatform.base.http.HttpTaskListener
import com.weiman.exam.examinationplatform.home.contact.RegistActivityContact
import com.weiman.exam.examinationplatform.utils.CommonUtils
import com.weiman.exam.examinationplatform.utils.SharedPreUtil

/**
 * 创建 by 刘宇飞 on 2017/7/22.
 * 邮箱：3494576680@qq.com
 * 微迈科技有限责任公司
 * 描述
 */
class RegisterActivityPresenter : RegistActivityContact.Presenter(), HttpTaskListener {

    /**
     * 登录
     */
    override fun doRegsiter(account: String, psw: String,checkPsw:String,userName:String) {
        if (CommonUtils.isEmpty(psw) || CommonUtils.isNotEmpty(psw) && psw.length < 6) {
            CommonUtils.showToast(mContext, "请输入大于六位数的密码")
            return
        }
        if (!CommonUtils.isMobile(account) && !CommonUtils.isEmail(account)) {
            CommonUtils.showToast(mContext, "请输入手机号码或者邮箱")
            return
        }
        if(psw!=(checkPsw)){
            CommonUtils.showToast(mContext, "两次密码不一致")
            return
        }
        if(CommonUtils.isEmpty(userName)){
            CommonUtils.showToast(mContext, "用户名不能为空")
            return
        }
        SharedPreUtil.saveString(mContext,"lAccount",account)
        SharedPreUtil.saveString(mContext,"lPsw",psw)
        var inputBean = LoginInputBean(account, psw, "", userName)
         HttpRequestUtils.getInstance()
                 .setContext(mContext)
                 .setCallBack(this)
                 .setRequestId(1)
                 .setObservable(mHttpTask.requestRegister(inputBean))
                 .create();

    }


    override fun onSuccess(requestId: Int, t: Any?) {
        when (requestId) {
            1 -> {

            }


        }
    }

    override fun onException(requestId: Int, code: Int) {
       when(code){
           999->{
               CommonUtils.showToast(mContext,"注册成功")
               var presenter = LoginActivityPresenter()
               presenter.mContext=mContext;
               presenter.mHttpTask=mHttpTask
               presenter.doLogin( SharedPreUtil.getString(mContext,"lAccount",""),SharedPreUtil.getString(mContext,"lPsw",""))

           }
       }
    }


}