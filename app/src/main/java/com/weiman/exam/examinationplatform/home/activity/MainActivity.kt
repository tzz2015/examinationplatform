package com.weiman.exam.examinationplatform.home.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.weiman.exam.examinationplatform.R
import com.weiman.exam.examinationplatform.base.BaseActivity
import com.weiman.exam.examinationplatform.databinding.ActivityMainBinding
import com.weiman.exam.examinationplatform.home.contact.MainActivityContact
import com.weiman.exam.examinationplatform.home.fragment.ExamCircleFragment
import com.weiman.exam.examinationplatform.home.fragment.ExamFragment
import com.weiman.exam.examinationplatform.home.fragment.HomeFragment
import com.weiman.exam.examinationplatform.home.fragment.MineFragment
import com.weiman.exam.examinationplatform.home.presenter.MainHomeActivityPresenter
import com.weiman.exam.examinationplatform.utils.AutoUtils
import com.weiman.exam.examinationplatform.utils.CommonUtils
import com.weiman.exam.examinationplatform.view.navigationView.NavigationView
import java.util.*

class MainActivity : BaseActivity<MainHomeActivityPresenter, ActivityMainBinding>(), MainActivityContact.View {
    private val fragmentList = ArrayList<Fragment>()
    var mCurrFragment: Fragment? = null
    override fun initPresenter() {
        mPresenter?.setView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initView() {
        hideTitleBar()
        val map = LinkedHashMap<String, Int>()
        map.put("首页", R.drawable.selector_home)
        map.put("考试", R.drawable.selector_note)
        map.put("考圈", R.drawable.selector_circle)
        map.put("我的", R.drawable.selector_mine)
        mBindingView.nvTab.setNaviga(map, object : NavigationView.NavigaClick {
            override fun onClick(position: Int) {
                changeFragment(position)
            }
        })
        //未读数显示
        mBindingView.nvTab.setUnRead(0, 200)
        AutoUtils.autoView(mBindingView.nvTab)
        initFragment()

    }

    /**
     * 初始化fragment
     */
    override fun initFragment() {
        fragmentList.add(HomeFragment())
        fragmentList.add(ExamFragment())
        fragmentList.add(ExamCircleFragment())
        fragmentList.add(MineFragment())
        mCurrFragment = fragmentList[0]
        supportFragmentManager.beginTransaction()
                .add(R.id.fl_content, fragmentList[0])
                .commitAllowingStateLoss()
    }

    /**
     * 显示和异常fragment
     */
    override fun changeFragment(position: Int) {
        if (fragmentList[position] != mCurrFragment) {
            if (fragmentList[position].isAdded) {
                supportFragmentManager.beginTransaction()
                        .hide(mCurrFragment)
                        .show(fragmentList[position])
                        .commitAllowingStateLoss()
            } else {
                supportFragmentManager.beginTransaction()
                        .add(R.id.fl_content, fragmentList[position])
                        .hide(mCurrFragment)
                        .commitAllowingStateLoss()
            }
            mCurrFragment = fragmentList[position]
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        CommonUtils.clearPhotoCache(mContext)
    }
}
