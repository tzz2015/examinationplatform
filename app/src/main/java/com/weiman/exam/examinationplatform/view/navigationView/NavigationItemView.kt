package com.weiman.exam.examinationplatform.view.navigationView

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
 * 刘宇飞创建 on 2017/6/1.
 * 描述：导航
 */

class NavigationItemView : RelativeLayout {

    private var tv_dse: TextView? = null
    private var iv_top: ImageView? = null
    private var tv_red_dot: TextView? = null

    constructor(context: Context) : super(context) {
        initView(context, null)
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

    private fun initView(context: Context, attrs: AttributeSet?) {
        val view = LayoutInflater.from(context).inflate(R.layout.navigation_tab, this, true)
        tv_dse = view.findViewById(R.id.tv_dse) as TextView
        iv_top = view.findViewById(R.id.iv_top) as ImageView
        tv_red_dot = view.findViewById(R.id.tv_red_dot) as TextView
        if (attrs == null) {
            return
        }
        val a = context.obtainStyledAttributes(attrs, R.styleable.NavigationView)

        val dse_name = a.getText(R.styleable.NavigationView_dse_name).toString()
        CommonUtils.setTextValue(tv_dse!!, dse_name)
        if (CommonUtils.isEmpty(dse_name)) {
            tv_dse!!.visibility = View.GONE
            val layoutParams = iv_top!!.layoutParams
            layoutParams.height = 70
            layoutParams.width = 70
            iv_top!!.layoutParams = layoutParams

        }

        val drawable_src = a.getResourceId(R.styleable.NavigationView_drawable_src, -1)
        if (drawable_src > 0) {
            iv_top!!.setBackgroundResource(drawable_src)
        }

        val is_select = a.getBoolean(R.styleable.NavigationView_is_select, false)
        isSelected = is_select
        a.recycle()
    }

    /**
     * 设置图片
     */
    fun setImage(imgId: Int) {
        if (imgId > 0) {
            iv_top!!.setBackgroundResource(imgId)
        }
    }

    /**
     * 设置文本
     */
    fun setTextValue(text: String) {
        CommonUtils.setTextValue(tv_dse!!, text)
        if (CommonUtils.isEmpty(text)) {
            tv_dse!!.visibility = View.GONE
            val layoutParams = iv_top!!.layoutParams
            layoutParams.height = 70
            layoutParams.width = 70
            iv_top!!.layoutParams = layoutParams

        }
    }

    /**
     * 设置未读数
     */
    open fun setUnReadNum(num: Int) {
        tv_red_dot!!.text = num.toString()+ ""
        if (num > 99) {
            tv_red_dot!!.text = "99+"
        }
        if (num > 0) {
            tv_red_dot!!.visibility = View.VISIBLE
            if (num > 10) {
                if (num < 100)
                    tv_red_dot!!.setPadding(6, 6, 6, 6)
                else
                    tv_red_dot!!.setPadding(5, 8, 5, 8)
            } else
                tv_red_dot!!.setPadding(10, 6, 10, 6)

        } else {
            tv_red_dot!!.visibility = View.GONE
        }
    }

    /**
     * 是否选中

     * @param selected
     */
    override fun setSelected(selected: Boolean) {
        iv_top!!.isSelected = selected
        if (selected)
            tv_dse!!.setTextColor(CommonUtils.getColor(context, R.color.button_blue))
        else
            tv_dse!!.setTextColor(CommonUtils.getColor(context, R.color.color666666))
    }


    /**
     * iv_top 是否被选中
     */
    override fun isSelected(): Boolean {
        return iv_top!!.isSelected
    }
}
