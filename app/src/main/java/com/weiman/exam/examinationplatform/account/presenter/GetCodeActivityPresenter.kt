package com.weiman.exam.examinationplatform.account.presenter

import android.app.Activity
import com.weiman.exam.examinationplatform.account.activity.ResetPswActivity
import com.weiman.exam.examinationplatform.account.bean.LoginInputBean
import com.weiman.exam.examinationplatform.base.http.HttpRequestUtils
import com.weiman.exam.examinationplatform.base.http.HttpTaskListener
import com.weiman.exam.examinationplatform.home.contact.GetCodeActivityContact
import com.weiman.exam.examinationplatform.utils.CommonUtils
import com.weiman.exam.examinationplatform.utils.Constants
import com.weiman.exam.examinationplatform.utils.SharedPreUtil

/**
 * 创建 by 刘宇飞 on 2017/7/22.
 * 邮箱：3494576680@qq.com
 * 微迈科技有限责任公司
 * 描述
 */
class GetCodeActivityPresenter : GetCodeActivityContact.Presenter(), HttpTaskListener {


    override fun onSuccess(requestId: Int, t: Any?) {

    }

    override fun onException(requestId: Int, code: Int) {
        when (requestId) {
          Constants.REQUESTID_0 ->{//发送成功
              if (code==999){
                 CommonUtils.startActivity(ResetPswActivity::class.java,null)
                  (mContext as Activity).finish()
              }
          }

        }
    }

    /**
     * 获取验证码
     */
    override fun getMailCode(email: String, code: String) {
          if(!CommonUtils.isEmail(email)&&!CommonUtils.isMobile(email)){
              CommonUtils.showToast(mContext,"请输入正确的手机号码或者邮箱")
              return
          }
        if(CommonUtils.isEmpty(code)){
            CommonUtils.showToast(mContext,"请输入图形码")
            return
        }
        SharedPreUtil.saveString(mContext, Constants.LACCOUNT,email)
        var bean = LoginInputBean(email, code)
        HttpRequestUtils.getInstance()
                .setCallBack(this)
                .setContext(mContext)
                .setRequestId(Constants.REQUESTID_0)
                .setObservable(mHttpTask.requestCode(bean))
                .create()

    }

}