package com.weiman.exam.examinationplatform.mine.activity

import android.os.Bundle
import com.weiman.exam.examinationplatform.R
import com.weiman.exam.examinationplatform.base.BaseActivity
import com.weiman.exam.examinationplatform.databinding.ActivityUserInfoBinding
import com.weiman.exam.examinationplatform.home.contact.UserInfoActivityContact
import com.weiman.exam.examinationplatform.mine.presenter.UserInfoActivityPresenter

class UserInfoActivity : BaseActivity<UserInfoActivityPresenter,ActivityUserInfoBinding>(),UserInfoActivityContact.View{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
    }
    override fun initView() {
        setTitle("个人信息")
    }

    override fun initPresenter() {
        mPresenter?.setView(this)
    }

}
