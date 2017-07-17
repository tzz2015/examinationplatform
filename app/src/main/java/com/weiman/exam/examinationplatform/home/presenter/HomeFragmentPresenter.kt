package com.weiman.exam.examinationplatform.home.presenter

import com.weiman.exam.examinationplatform.account.bean.UserInfoBean
import com.weiman.exam.examinationplatform.base.http.HttpRequestUtils
import com.weiman.exam.examinationplatform.base.http.HttpTaskListener
import com.weiman.exam.examinationplatform.home.contact.HomeFragmentContact
import java.util.*

/**
 * 创建 by 刘宇飞 on 2017/7/13.
 * 邮箱：3494576680@qq.com
 * 微迈科技有限责任公司
 * 描述
 */
class HomeFragmentPresenter :HomeFragmentContact.Presenter(), HttpTaskListener {
    override fun getData() {

        val map = HashMap<String, Any>()
        map.put("phone", "15506200515")
        map.put("code", "4986")
        map.put("userType", "2")
        map.put("alias", "ffffffff_c7a8_3c15_0000_00004ca6b30b")
        map.put("source", "APP")
        map.put("userId", "280")
        HttpRequestUtils.getInstance()
                .setContext(mContext)
                .setRequestId(10)
                .setCallBack(this)
                .setObservable(mHttpTask.requestLogin(map))
                .create()
    }
    override fun onSuccess(requestId: Int, t: Any?) {
        when (requestId) {
            10 ->
                mView?.setBackDate((t as UserInfoBean).phone)
        }
    }

    override fun onException(requestId: Int, code: Int) {

    }

}