package com.weiman.exam.examinationplatform.view.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.weiman.exam.examinationplatform.R
import com.weiman.exam.examinationplatform.utils.AutoUtils
import com.weiman.exam.examinationplatform.utils.CommonUtils

/**
 * 公司：杭州融科网络科技
 * 刘宇飞 创建 on 2017/3/14.
 * 描述：
 */

class ConfirmDialog(private val mContext: Context, private val dialogListener: ConfirmDialog.SingleDialogListener?, vararg arg: String) : Dialog(mContext, R.style.bottom_select_dialog) {

    internal var tvShow: TextView? = null
    internal var btSubmit: Button? = null
    internal var tvBtCancel: Button? = null

    private var arg: Array<out String>

    init {
        this.arg = arg

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window = window
        window!!.setGravity(Gravity.CENTER)
        val inflater = getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.dialog_confirm, null)
        tvShow = view.findViewById(R.id.tv_show) as TextView
        btSubmit = view.findViewById(R.id.bt_submit) as Button
        tvBtCancel = view.findViewById(R.id.tv_bt_cancel) as Button
        AutoUtils.autoView(view)
        setContentView(view)
        CommonUtils.FullScreen(mContext as Activity, this, 0.8)
        // 点击Dialog外部消失
        setCanceledOnTouchOutside(true)
        initView()


    }

    private fun initView() {
        if (!arg.isEmpty()) {
            if (arg.size >= 1) {
                tvShow!!.text = arg[0]
            }
            if (arg.size >= 2) {
                if (CommonUtils.isNotEmpty(arg[1])) {
                    btSubmit!!.text = arg[1]
                } else {
                    btSubmit!!.visibility = View.GONE
                }
            }
            if (arg.size >= 3) {
                if (CommonUtils.isNotEmpty(arg[2])) {
                    tvBtCancel!!.text = arg[2]
                }
            }

        }

        btSubmit?.setOnClickListener {
            dialogListener?.onConfirm("")
            dismiss()
        }
        tvBtCancel?.setOnClickListener { dismiss() }
    }




    interface SingleDialogListener {
        fun onConfirm(s: String)
    }
}
