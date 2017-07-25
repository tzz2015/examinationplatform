package com.weiman.exam.examinationplatform.account.activity

import android.os.Bundle
import com.weiman.exam.examinationplatform.R
import com.weiman.exam.examinationplatform.account.presenter.GetCodeActivityPresenter
import com.weiman.exam.examinationplatform.base.BaseActivity
import com.weiman.exam.examinationplatform.databinding.ActivityGetCodeBinding
import com.weiman.exam.examinationplatform.home.contact.GetCodeActivityContact

class GetCodeActivity : BaseActivity<GetCodeActivityPresenter, ActivityGetCodeBinding>(), GetCodeActivityContact.View {
    /**
     * 初始化布局
     */
    override fun initView() {
        setTitle("获取验证码")
    }

    override fun initPresenter() {
        mPresenter?.setView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_code)
    }

    override fun initListener() {
        super.initListener()
        mBindingView.btNext.setOnClickListener {
            mPresenter?.getMailCode(mBindingView.etAccount.text.toString(), mBindingView.etCode.text.toString())
        }
    }
}
