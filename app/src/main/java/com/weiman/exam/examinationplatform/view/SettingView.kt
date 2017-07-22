package com.weiman.exam.examinationplatform.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.weiman.exam.examinationplatform.R
import com.weiman.exam.examinationplatform.utils.AutoUtils
import com.weiman.exam.examinationplatform.utils.CommonUtils


/**
 * 公司：杭州融科网络科技
 * 刘宇飞 创建 on 2017/3/9.
 * 描述：设置view
 */

class SettingView : RelativeLayout {


    private var tvName: TextView? = null
    private var tvValue: TextView? = null
    private var ivArrowRight: ImageView? = null
    private var tv_line: TextView? = null
    private var tv_left_value: TextView? = null
    private var rl_root: RelativeLayout? = null
    lateinit var mContext: Context

    constructor(context: Context) : super(context) {

    }


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
        this.mContext = context
        val view = LayoutInflater.from(context).inflate(R.layout.layout_setting_action, this, true)
        rl_root = view.findViewById(R.id.rl_root) as RelativeLayout
        tvName = view.findViewById(R.id.tv_name) as TextView
        tvValue = view.findViewById(R.id.tv_value) as TextView
        ivArrowRight = view.findViewById(R.id.iv_arrow_right) as ImageView
        tv_line = view.findViewById(R.id.tv_line) as TextView
        tv_left_value = view.findViewById(R.id.tv_left_value) as TextView
        val a = context.obtainStyledAttributes(attrs, R.styleable.SettingView)

        val title = a.getText(R.styleable.SettingView_setting_title)?.toString()
        CommonUtils.setTextValue(tvName!!, title)
        val value = a.getText(R.styleable.SettingView_setting_value)?.toString() + ""
        CommonUtils.setTextValue(tvValue!!, value)
        val leftValue = a.getText(R.styleable.SettingView_setting_left_value)?.toString() + ""
        CommonUtils.setTextValue(tv_left_value!!, leftValue)
        val isShowArrow = a.getBoolean(R.styleable.SettingView_arrow_is_show, true)
        if (isShowArrow) {
            ivArrowRight!!.visibility = View.VISIBLE
        } else
            ivArrowRight!!.visibility = View.GONE
        val showLine = a.getBoolean(R.styleable.SettingView_is_need_line, true)
        if (showLine)
            tv_line!!.visibility = View.VISIBLE
        else
            tv_line!!.visibility = View.GONE
        a.recycle()
    }

    /**
     * 设置右边值颜色
     * @param value
     */
    fun setSettingValueColor(value: Int) {
        tvValue!!.setTextColor(CommonUtils.getColor(context!!, value))
    }

    /**
     * 设置左边值颜色
     * @param value
     */
    fun setSettingLeftValueColor(value: Int) {
        tv_left_value!!.setTextColor(CommonUtils.getColor(context!!, value))
    }

    /**
     * 获取右边值
     * @return
     */
    /**
     * 设置右边值
     * @param value
     */
    var settingValue: String
        get() = tvValue!!.text.toString()
        set(value) = CommonUtils.setTextValue(tvValue!!, value)

    /**
     * 获取左边值颜色
     */
    /**
     * 设置左边值
     * @param value
     */
    var settingLeftValue: String
        get() = tv_left_value!!.text.toString()
        set(value) = CommonUtils.setTextValue(tv_left_value!!, value)

    /**
     * 设置item高度
     */
    fun setItemViewHeight(height: Int) {
        val layoutParams = rl_root!!.layoutParams
        layoutParams.height = height
        rl_root?.layoutParams = layoutParams
        AutoUtils.autoSize(rl_root!!)
        invalidate()
    }

}

