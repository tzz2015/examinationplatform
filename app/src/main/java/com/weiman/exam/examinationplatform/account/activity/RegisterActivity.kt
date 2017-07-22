package com.weiman.exam.examinationplatform.account.activity

import android.os.Bundle
import com.weiman.exam.examinationplatform.R
import com.weiman.exam.examinationplatform.account.presenter.RegisterActivityPresenter
import com.weiman.exam.examinationplatform.base.BaseActivity
import com.weiman.exam.examinationplatform.databinding.ActivityRegisterBinding
import com.weiman.exam.examinationplatform.home.contact.RegistActivityContact

class RegisterActivity : BaseActivity<RegisterActivityPresenter, ActivityRegisterBinding>(), RegistActivityContact.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    override fun initPresenter() {
        mPresenter?.setView(this)
    }

    /**
     * 初始化布局
     */
    override fun initView() {
        setTitle("注册")
    }

    override fun initListener() {
        super.initListener()
        mBindingView.btRegister.setOnClickListener {
            mPresenter?.doRegsiter(mBindingView.etAccount.text.toString(), mBindingView.etPsw.text.toString(),
                    mBindingView.etCheckPsw.text.toString(), mBindingView.etUserName.text.toString())
        }
    }

}
