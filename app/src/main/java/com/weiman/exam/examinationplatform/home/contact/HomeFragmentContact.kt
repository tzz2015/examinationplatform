package com.weiman.exam.examinationplatform.home.contact

import com.weiman.exam.examinationplatform.base.BasePresenter
import com.weiman.exam.examinationplatform.base.BaseView

/**
 * 创建 by 刘宇飞 on 2017/7/12.
 * 邮箱：3494576680@qq.com
 * 描述
 */
interface HomeFragmentContact {
    interface View :BaseView{
      fun setBackDate(phone:String)
    }
    abstract class Presenter :BasePresenter<View>(){
        abstract fun getData()
    }
}