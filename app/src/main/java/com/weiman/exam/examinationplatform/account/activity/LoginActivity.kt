package com.weiman.exam.examinationplatform.account.activity

import android.os.Bundle
import com.weiman.exam.examinationplatform.R
import com.weiman.exam.examinationplatform.account.presenter.LoginActivityPresenter
import com.weiman.exam.examinationplatform.base.BaseActivity
import com.weiman.exam.examinationplatform.databinding.ActivityLoginBinding
import com.weiman.exam.examinationplatform.home.contact.LoginActivityContact
import com.weiman.exam.examinationplatform.utils.SharedPreUtil

class LoginActivity : BaseActivity<LoginActivityPresenter, ActivityLoginBinding>(), LoginActivityContact.View {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    /**
     * 初始化布局
     */
    override fun initView() {
        setTitle("登录微迈考试平台")
        mBindingView.etAccount.setText(SharedPreUtil.getString(mContext, "lAccount", ""))
        mBindingView.etPsw.setText(SharedPreUtil.getString(mContext, "lPsw", ""))
    }

    override fun initPresenter() {
        mPresenter?.setView(this)
    }

    override fun initListener() {
        super.initListener()
        mBindingView.btLogin.setOnClickListener {
            mPresenter?.doLogin(mBindingView.etAccount.text.toString(), mBindingView.etPsw.text.toString())
        }
    }
}
