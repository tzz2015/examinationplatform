package com.weiman.exam.examinationplatform.home.contact

import com.weiman.exam.examinationplatform.base.BasePresenter
import com.weiman.exam.examinationplatform.base.BaseView

/**
 * 创建 by 刘宇飞 on 2017/7/12.
 * 邮箱：3494576680@qq.com
 * 描述
 */
interface GetCodeActivityContact {
    interface View : BaseView {

    }

    abstract class Presenter : BasePresenter<View>() {
        /**
         * 获取验证码
         */
        abstract fun getMailCode(email: String, code: String)
    }
}