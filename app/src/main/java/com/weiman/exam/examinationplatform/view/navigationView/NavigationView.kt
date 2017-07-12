package com.weiman.exam.examinationplatform.view.navigationView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.weiman.exam.examinationplatform.R
import com.weiman.exam.examinationplatform.utils.AutoUtils

/**
 * 杭州融科网络
 * 刘宇飞创建 on 2017/6/5.
 * 描述：
 */

class NavigationView : LinearLayout {

    private var ll_root: LinearLayout? = null
    private var curreItemView: NavigationItemView? = null

    constructor(context: Context) : super(context) {
        initView(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_navigation, this, true)
        ll_root = view.findViewById(R.id.ll_root) as LinearLayout
    }

    /**
     * 设置底部导航布局
     */
    fun setNaviga(map: Map<String, Int>, navigaClick: NavigaClick) {
        var positon = -1
        for ((key, value) in map) {
            positon++
            val itemView = NavigationItemView(context)
            itemView.setTextValue(key)
            itemView.setImage(value)
            if (positon == 0) {
                itemView.isSelected = true
                curreItemView = itemView
            } else {
                itemView.isSelected = false
            }
            val finalPositon = positon
            itemView.setOnClickListener {
                if (curreItemView !== itemView) {
                    curreItemView!!.isSelected = false
                    itemView.isSelected = true
                    navigaClick.onClick(finalPositon)
                    curreItemView = itemView
                }
            }

            ll_root!!.addView(itemView)
        }
        invalidate()
        for (i in 0..ll_root!!.childCount - 1) {
            val childAt = ll_root!!.getChildAt(i)
            val layoutParams = childAt.layoutParams
            layoutParams.width = AutoUtils.designWidth/ map.size
            childAt.layoutParams = layoutParams
        }
        AutoUtils.autoView(this)
        invalidate()
    }

    /**
     * 设置未读数
     */
    fun setUnRead(position: Int, num: Int) {
        if (position > ll_root!!.childCount - 1) {
            return
        }
        val navigationItemView = ll_root!!.getChildAt(position) as NavigationItemView
        navigationItemView.setUnReadNum(num)
    }

    interface NavigaClick {
        fun onClick(position: Int)
    }
}
