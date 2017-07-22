package com.weiman.exam.examinationplatform.home.fragment

import com.weiman.exam.examinationplatform.R
import com.weiman.exam.examinationplatform.base.BaseFragment
import com.weiman.exam.examinationplatform.databinding.LayoutTestBinding
import com.weiman.exam.examinationplatform.home.contact.HomeFragmentContact
import com.weiman.exam.examinationplatform.home.presenter.HomeFragmentPresenter

/**
 * 创建 by 刘宇飞 on 2017/7/13.
 * 邮箱：3494576680@qq.com
 * 微迈科技有限责任公司
 * 描述
 */
class HomeFragment : BaseFragment<HomeFragmentPresenter,LayoutTestBinding>(), HomeFragmentContact.View {


    override fun setLayoutId(): Int {
        return R.layout.layout_test
    }

    override fun initView() {
        showTitleBar()
        setTitle("首页")
        mBindingView.tvShow.text = "首页"
       // mPresenter?.getData()
    }



    override fun initPresenter() {
        mPresenter?.setView(this)
    }
    override fun setBackDate(phone: String) {
        mBindingView.tvShow.text = "请求网络成功:" + phone
    }



}