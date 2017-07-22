package com.weiman.exam.examinationplatform.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.weiman.exam.examinationplatform.R
import com.weiman.exam.examinationplatform.utils.CommonUtils

/**
 * 杭州融科网络
 * 刘宇飞创建 on 2017/6/1.
 * 描述：设置
 */

class SettingActionView : RelativeLayout {


    private var iv_icon: ImageView? = null
    private var tv_title: TextView? = null
    private var iv_arrow_right: ImageView? = null
    private var tv_line: TextView? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        if (isInEditMode) {
            return
        }
        initView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        if (isInEditMode) {
            return
        }
        initView(context, attrs)

    }

    private fun initView(context: Context, attrs: AttributeSet) {
        val view = LayoutInflater.from(context).inflate(R.layout.setting_action_view, this, true)
        iv_icon = view.findViewById(R.id.iv_icon) as ImageView
        tv_title = view.findViewById(R.id.tv_title) as TextView
        iv_arrow_right = view.findViewById(R.id.iv_arrow_right) as ImageView
        tv_line = view.findViewById(R.id.tv_line) as TextView

        val a = context.obtainStyledAttributes(attrs, R.styleable.SettingActionView)
        val src = a.getResourceId(R.styleable.SettingActionView_icon_src, -1)
        if (src > 0) iv_icon!!.setBackgroundResource(src)

        val text = a.getText(R.styleable.SettingActionView_title_name)?.toString()
        CommonUtils.setTextValue(tv_title!!, text)

        val showArrow = a.getBoolean(R.styleable.SettingActionView_is_show_arrow, true)
        if (showArrow)
            iv_arrow_right!!.visibility = View.VISIBLE
        else
            iv_arrow_right!!.visibility = View.GONE

        val showLine = a.getBoolean(R.styleable.SettingActionView_is_show_line, true)
        if (showLine)
            tv_line!!.visibility = View.VISIBLE
        else
            tv_line!!.visibility = View.GONE

        a.recycle()
    }

    /**
     * 设置原色
     * @param color
     */
    fun setTextColor(color: Int) {
        tv_title!!.setTextColor(CommonUtils.getColor(context, color))
    }

    /**
     * 设置标题
     */
    fun setTitle(title: String) {
        CommonUtils.setTextValue(tv_title!!, title)
    }


}
