package com.weiman.exam.examinationplatform.home.fragment

import com.weiman.exam.examinationplatform.R
import com.weiman.exam.examinationplatform.base.BaseFragment
import com.weiman.exam.examinationplatform.databinding.XRecyclerviewBinding
import com.weiman.exam.examinationplatform.home.contact.ExamFragmentContact
import com.weiman.exam.examinationplatform.home.presenter.ExamFragmentPresenter

/**
 * 创建 by 刘宇飞 on 2017/7/13.
 * 邮箱：3494576680@qq.com
 * 微迈科技有限责任公司
 * 描述
 */
class ExamFragment : BaseFragment<ExamFragmentPresenter,XRecyclerviewBinding>(), ExamFragmentContact.View {
    override fun setLayoutId(): Int {
        return R.layout.x_recyclerview
    }

    override fun initView() {
        showTitleBar()
        setTitle("考试")
        mPresenter?.intRecyclerView(mBindingView.xRecyclerView)
    }

    override fun initPresenter() {
        mPresenter?.setView(this)
    }
}