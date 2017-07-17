package com.weiman.exam.examinationplatform.base

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import com.kaopiz.kprogresshud.KProgressHUD
import com.tbruyelle.rxpermissions.RxPermissions
import com.weiman.exam.examinationplatform.R
import com.weiman.exam.examinationplatform.databinding.ActivityBaseBinding
import com.weiman.exam.examinationplatform.utils.*
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.common_back_title.view.*
import rx.Subscription
import rx.subscriptions.CompositeSubscription

/**
 * 创建 by 刘宇飞 on 2017/7/10.
 * 邮箱：3494576680@qq.com
 * 描述 基类
 */
abstract class BaseActivity<T : BasePresenter<*>, SV : ViewDataBinding> : FragmentActivity() {
    lateinit var mBaseBinding: ActivityBaseBinding
    lateinit var mBindingView: SV
    lateinit var mContext: Context
    var mPresenter: T? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        LogUtil.getInstance().e(javaClass.simpleName)
        mPresenter = TUtil.getT(this, 0)
        mPresenter?.mContext = this
        initPresenter()

    }


    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        //标题栏已经在activity_base 不用到每个布局里面添加
        mBaseBinding = DataBindingUtil.inflate<ActivityBaseBinding>(layoutInflater, R.layout.activity_base, null, false)
        mBindingView = DataBindingUtil.inflate<SV>(layoutInflater, layoutResID, null, false)
        // content
        val params = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        mBindingView.root.layoutParams = params
        val mContainer = mBaseBinding.container
        mContainer.addView(mBindingView.root)
        window.setContentView(mBaseBinding.root)
        // 设置透明状态栏
        StatusBarUtil.setColor(this, CommonUtils.getColor(this, R.color.colorTitle), 0)
        mContext = this

        //根据设计稿设定 preview 切换至对应的尺寸
        AutoUtils.setSize(this, false, 720, 1280)
        //自适应页面
        AutoUtils.autoView(this)
        initView()
    }

    /**
     * 点击返回键默认关闭
     */
    private fun initListener() {
        mBaseBinding.commonTitle.ll_lift_back.setOnClickListener { finish() }
    }

    /**
     * 初始化布局
     */
    abstract fun initView()

    abstract fun initPresenter()

    /**
     * 隐藏标题栏
     */
    fun hideTitleBar() {
       common_title.visibility= GONE
    }

    /**
     * 隐藏返回箭头
     */
    fun hideIconBack() {
        mBaseBinding.commonTitle?.ll_lift_back?.visibility = GONE
    }

    /**
     * 设置标题
     */
    fun setTitle(title: String) {
        mBaseBinding.commonTitle?.tv_title?.text = title
    }

    /**
     * 显示toast
     */
    fun showToast(title: String) {
        CommonUtils.showToast(mContext, title)
    }

    /**
     * 设置右侧文字
     */
    fun setRightTitle(title: String, listener: View.OnClickListener) {
        mBaseBinding.commonTitle.tv_right_text.visibility = VISIBLE
        mBaseBinding.commonTitle.tv_right_text.text = title
        mBaseBinding.commonTitle.tv_right_text.setOnClickListener(listener)
    }

    /**
     * 设置右侧图片
     */
    fun setRightImg(img: Int, listener: View.OnClickListener) {
        mBaseBinding.commonTitle.iv_right_img.visibility = VISIBLE
        mBaseBinding.commonTitle.iv_right_img.setOnClickListener(listener)
        mBaseBinding.commonTitle.iv_right_img.setBackgroundResource(img)
    }

    /**
     * 显示进度框
     * @param str
     */
    private var mProgressDialog: KProgressHUD? = null

    fun showInfoProgressDialog(vararg str: String) {
        if (mProgressDialog == null) {
            mProgressDialog = KProgressHUD(this)
            mProgressDialog!!.setCancellable(true)
        }
        if (str.isEmpty()) {
            mProgressDialog!!.setLabel("加载中...")
        } else {
            mProgressDialog!!.setLabel(str[0])
        }

        if (!mProgressDialog!!.isShowing) {
            mProgressDialog!!.show()
        }
    }

    /**
     * 隐藏等待条
     */
    fun hideInfoProgressDialog() {
        CommonUtils.hideInfoProgressDialog()
        if (mProgressDialog != null) {
            mProgressDialog!!.dismiss()
        }
    }

    /**
     * 添加网络请求观察者
     * @param s
     */
    private var mCompositeSubscription: CompositeSubscription? = null

    fun addSubscription(s: Subscription?) {
        if (s == null) {
            return
        }
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = CompositeSubscription()
        }
        this.mCompositeSubscription!!.add(s)
    }

    /**
     * 移除网络请求
     */
    fun removeSubscription() {
        CommonUtils.removeSubscription()
        if (this.mCompositeSubscription != null && mCompositeSubscription!!.hasSubscriptions()) {
            this.mCompositeSubscription!!.unsubscribe()
            this.mCompositeSubscription = null
        }
    }

    /**
     * 请求权限
     */
    private var rxPermissions: RxPermissions? = null

    fun requestPermission(permissions: Array<String>) {
        if (rxPermissions == null) {
            rxPermissions = RxPermissions(this)
        }
        val subscribe = rxPermissions!!
                .request(*permissions)
                .subscribe { aBoolean -> requestPermissionCallBack(aBoolean) }
        addSubscription(subscribe)
    }

    /**
     * 请求结果
     * @param aBoolean
     */
    fun requestPermissionCallBack(aBoolean: Boolean?) {}

    override fun onDestroy() {
        super.onDestroy()
        hideInfoProgressDialog()
        removeSubscription()
        mPresenter?.onDestroy()
    }

}