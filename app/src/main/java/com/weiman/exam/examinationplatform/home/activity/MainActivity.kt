package com.weiman.exam.examinationplatform.home.activity

import android.os.Bundle
import com.weiman.exam.examinationplatform.R
import com.weiman.exam.examinationplatform.base.BaseActivity
import com.weiman.exam.examinationplatform.home.presenter.MainHomeActivityPresenter
import com.weiman.exam.examinationplatform.view.navigationView.NavigationView
import java.util.*

class MainActivity:BaseActivity<MainHomeActivityPresenter>()  {
    override fun initPresenter() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
    override fun initView() {
        hideTitleBar()
        val nv_tab = findViewById(R.id.nv_tab) as NavigationView
        val map = LinkedHashMap<String, Int>()
        map.put("首页", R.drawable.selector_home)
        map.put("考试", R.drawable.selector_note)
        map.put("考圈", R.drawable.selector_circle)
        map.put("我的", R.drawable.selector_mine)
        nv_tab.setNaviga(map, object : NavigationView.NavigaClick {
            override fun onClick(position: Int) {
                showToast(""+position)
            }

        })
        //未读数显示
        nv_tab.setUnRead(0, 2)
    }

}
