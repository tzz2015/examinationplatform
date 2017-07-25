package com.weiman.exam.examinationplatform.account.activity

import android.os.Bundle
import com.weiman.exam.examinationplatform.R
import com.weiman.exam.examinationplatform.account.presenter.ResetPswActivityPresenter
import com.weiman.exam.examinationplatform.base.BaseActivity
import com.weiman.exam.examinationplatform.databinding.ActivityResetPswBinding
import com.weiman.exam.examinationplatform.home.contact.ResetPswActivityContact

class ResetPswActivity : BaseActivity<ResetPswActivityPresenter,ActivityResetPswBinding>(),ResetPswActivityContact.View {
    /**
     * 初始化布局
     */
    override fun initView() {
       setTitle("重置密码")
    }

    override fun initPresenter() {
      mPresenter?.setView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_psw)
    }

    override fun initListener() {
        super.initListener()
       mBindingView.btReset.setOnClickListener {
           mPresenter?.postReset(mBindingView.etCode.text.toString(),mBindingView.etPsw.text.toString())
       }
    }
}
