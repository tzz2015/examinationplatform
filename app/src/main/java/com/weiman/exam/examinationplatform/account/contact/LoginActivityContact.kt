package com.weiman.exam.examinationplatform.home.contact

import com.weiman.exam.examinationplatform.base.BasePresenter
import com.weiman.exam.examinationplatform.base.BaseView

/**
 * 创建 by 刘宇飞 on 2017/7/12.
 * 邮箱：3494576680@qq.com
 * 描述
 */
interface LoginActivityContact {
    interface View : BaseView {

    }

    abstract class Presenter : BasePresenter<View>() {
        /**
         * 登录
         */
        abstract fun doLogin(account: String, psw: String)
    }
}