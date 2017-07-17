package com.weiman.exam.examinationplatform.home.fragment

import com.weiman.exam.examinationplatform.R
import com.weiman.exam.examinationplatform.base.BaseFragment
import com.weiman.exam.examinationplatform.databinding.LayoutTestBinding
import com.weiman.exam.examinationplatform.home.contact.MineFragmentContact
import com.weiman.exam.examinationplatform.home.presenter.MineFragmentPresenter

/**
 * 创建 by 刘宇飞 on 2017/7/13.
 * 邮箱：3494576680@qq.com
 * 微迈科技有限责任公司
 * 描述
 */
class MineFragment : BaseFragment<MineFragmentPresenter,LayoutTestBinding>(), MineFragmentContact.View {
    override fun setLayoutId(): Int {
        return R.layout.layout_test
    }

    override fun initView() {
        showTitleBar()
        setTitle("我的")
        mBindingView.tvShow.text="我的"
    }

    override fun initPresenter() {
        mPresenter?.setView(this)
    }
}