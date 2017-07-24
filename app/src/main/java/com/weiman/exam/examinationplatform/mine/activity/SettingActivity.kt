package com.weiman.exam.examinationplatform.mine.activity

import android.os.Bundle
import com.weiman.exam.examinationplatform.R
import com.weiman.exam.examinationplatform.base.BaseActivity
import com.weiman.exam.examinationplatform.databinding.ActivitySettingBinding
import com.weiman.exam.examinationplatform.home.contact.SettingActivityContact
import com.weiman.exam.examinationplatform.mine.presenter.SettingActivityPresenter
import com.weiman.exam.examinationplatform.utils.CommonUtils
import com.weiman.exam.examinationplatform.utils.DataCleanManager
import com.weiman.exam.examinationplatform.view.dialog.ConfirmDialog

class SettingActivity : BaseActivity<SettingActivityPresenter,ActivitySettingBinding>(),SettingActivityContact.View {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
    }
    /**
     * 初始化布局
     */
    override fun initView() {
        setTitle("设置")
        mBindingView.svUpdate.settingValue=CommonUtils.getVersion()
        mBindingView.svAbout.settingValue="微迈考试平台"
        mBindingView.svCache.settingValue=DataCleanManager.getTotalCacheSize(mContext)
        mBindingView.svCache.setOnClickListener { showClearCacheDialog() }
    }


    /**
     * 清空缓存
     */
    override fun showClearCacheDialog() {
        var dialog = ConfirmDialog(mContext, object : ConfirmDialog.SingleDialogListener {
            override fun onConfirm(s: String) {
                DataCleanManager.clearAllCache(mContext)
                mBindingView.svCache.settingValue=DataCleanManager.getTotalCacheSize(mContext)
            }
        }, "是否确定清空缓存?")
        dialog.show()
    }

    override fun initPresenter() {
        mPresenter?.setView(this)
    }
}
