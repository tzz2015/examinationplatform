package com.weiman.exam.examinationplatform.mine.activity

import android.os.Bundle
import com.jph.takephoto.model.TImage
import com.weiman.exam.examinationplatform.R
import com.weiman.exam.examinationplatform.account.bean.UserInfoBean
import com.weiman.exam.examinationplatform.base.BaseActivity
import com.weiman.exam.examinationplatform.databinding.ActivityUserInfoBinding
import com.weiman.exam.examinationplatform.home.contact.UserInfoActivityContact
import com.weiman.exam.examinationplatform.mine.presenter.UserInfoActivityPresenter
import com.weiman.exam.examinationplatform.utils.CommonUtils
import com.weiman.exam.examinationplatform.utils.ImgLoadUtil
import com.weiman.exam.examinationplatform.view.dialog.ConfirmDialog
import java.io.File

class UserInfoActivity : BaseActivity<UserInfoActivityPresenter, ActivityUserInfoBinding>(), UserInfoActivityContact.View {

    var localHeadUrl:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
    }

    override fun initView() {
        setTitle("个人信息")

    }

    override fun onResume() {
        super.onResume()
        if(CommonUtils.isEmpty(localHeadUrl))   mBindingView.model = UserInfoBean.getInstance()
        mBindingView.svAccount.settingValue = UserInfoBean.getInstance().getUserEmil()
        mBindingView.svName.settingValue = UserInfoBean.getInstance().getUserName()
       if(CommonUtils.isNotEmpty(localHeadUrl))  ImgLoadUtil.displayRound(mContext,mBindingView.ivHead, File(localHeadUrl))

    }

    override fun initPresenter() {
        mPresenter?.setView(this)
    }

    override fun initListener() {
        super.initListener()
        mBindingView.btLogout.setOnClickListener { showLogoutDialog() }
        mBindingView.ivHead.setOnClickListener {
            CommonUtils.seleteSinglePhoto(true, object : CommonUtils.SelectPhotoListener {
                override fun onSuccessBack(resultList: List<TImage>) {
                    localHeadUrl=resultList[0].compressPath
                }

            })
        }
    }

    /**
     * 是否退出
     */
    override fun showLogoutDialog() {
        val confirmDialog = ConfirmDialog(mContext, object : ConfirmDialog.SingleDialogListener {
            override fun onConfirm(s: String) {
                UserInfoBean.getInstance().clearAccount()
                finish()
            }
        }, "是否确定退出")
        confirmDialog.show()
    }


}
