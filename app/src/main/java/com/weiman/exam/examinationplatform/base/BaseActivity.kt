package com.weiman.exam.examinationplatform.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import com.weiman.exam.examinationplatform.R
import com.weiman.exam.examinationplatform.utils.AutoUtils
import com.weiman.exam.examinationplatform.utils.CommonUtils
import com.weiman.exam.examinationplatform.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_base.view.*

/**
 * 创建 by 刘宇飞 on 2017/7/10.
 * 邮箱：3494576680@qq.com
 * 描述 基类
 */
abstract class BaseActivity :FragmentActivity(){

    lateinit var mBaseView:View
    lateinit var mChildView:View
    lateinit var mContext:Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        //基础view
        mBaseView = layoutInflater.inflate(R.layout.activity_base, null,false)
        mChildView=layoutInflater.inflate(layoutResID,null,false)
        //子view设置全屏
        val params = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        mChildView.rootView.layoutParams=params
        mBaseView.container.addView(mChildView.rootView)
        //添加到窗口
        window.setContentView(mBaseView.rootView)
        mContext=this
        //设置沉浸状态栏颜色
        StatusBarUtil.setColor(this,CommonUtils.getColor(this,R.color.colorTitle),0)
        //根据设计稿设定 preview 切换至对应的尺寸
        AutoUtils.setSize(this, false, 720, 1280)
        //自适应页面
        AutoUtils.autoView(this)
        initView()

    }

    /**
     * 初始化布局
     */
    abstract fun initView()
}