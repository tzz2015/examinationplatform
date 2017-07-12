package com.weiman.exam.examinationplatform.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.kaopiz.kprogresshud.KProgressHUD
import com.tbruyelle.rxpermissions.RxPermissions
import com.weiman.exam.examinationplatform.R
import com.weiman.exam.examinationplatform.utils.AutoUtils
import com.weiman.exam.examinationplatform.utils.CommonUtils
import com.weiman.exam.examinationplatform.utils.TUtil
import kotlinx.android.synthetic.main.activity_base.view.*
import kotlinx.android.synthetic.main.common_back_title.view.*
import rx.Subscription
import rx.subscriptions.CompositeSubscription

/**
 * 创建 by 刘宇飞 on 2017/7/10.
 * 邮箱：3494576680@qq.com
 * 描述 基类
 */
abstract class BaseFragment<T : BasePresenter<*>> : Fragment() {

    lateinit var mBaseView: View
    lateinit var mChildView: View
    lateinit var mContext: Context
    var mPresenter: T? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = TUtil.getT(this, 0)
        mPresenter?.mContext = context
        initPresenter()

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBaseView = inflater!!.inflate(R.layout.activity_base, container, false)
        mChildView = inflater.inflate(setLayoutId(), null, false)
        //子view设置全屏
        val params = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        mChildView.rootView.layoutParams = params
        mBaseView.container.addView(mChildView.rootView)
        mContext = context
        hideIconBack()
        //自适应页面
        AutoUtils.autoView(mBaseView.rootView)
        initView()
        return mBaseView.rootView
    }

    abstract fun setLayoutId(): Int


    /**
     * 初始化布局
     */
    abstract fun initView()

    abstract fun initPresenter()

    /**
     * 隐藏标题栏
     */
    fun hideTitleBar() {
        mBaseView.common_title.common_title?.rl_title_bar?.visibility = GONE
    }

    /**
     * 隐藏返回箭头
     */
    fun hideIconBack() {
        mBaseView.common_title.common_title?.ll_lift_back?.visibility = GONE
    }

    /**
     * 设置标题
     */
    fun setTitle(title: String) {
        mBaseView.common_title.common_title?.tv_title?.text = title
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
        mBaseView.common_title.tv_right_text.visibility = VISIBLE
        mBaseView.common_title.tv_right_text.text = title
        mBaseView.common_title.tv_right_text.setOnClickListener(listener)
    }

    /**
     * 设置右侧图片
     */
    fun setRightImg(img: Int, listener: View.OnClickListener) {
        mBaseView.common_title.iv_right_img.visibility = VISIBLE
        mBaseView.common_title.iv_right_img.setOnClickListener(listener)
        mBaseView.common_title.iv_right_img.setBackgroundResource(img)
    }

    /**
     * 显示进度框
     * @param str
     */
    private var mProgressDialog: KProgressHUD? = null

    fun showInfoProgressDialog(vararg str: String) {
        if (mProgressDialog == null) {
            mProgressDialog = KProgressHUD(context)
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
            rxPermissions = RxPermissions(activity)
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