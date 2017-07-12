package com.weiman.exam.examinationplatform.home.activity

import android.os.Bundle
import com.weiman.exam.examinationplatform.R
import com.weiman.exam.examinationplatform.base.BaseActivity
import com.weiman.exam.examinationplatform.home.presenter.MainHomeActivityPresenter
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity:BaseActivity<MainHomeActivityPresenter>()  {
    override fun initPresenter() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         mChildView.tv_show.text="微迈科技"

    }
    override fun initView() {
        hideTitleBar()
    }

}
