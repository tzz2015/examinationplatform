package com.weiman.exam.examinationplatform.home.fragment

import com.weiman.exam.examinationplatform.R
import com.weiman.exam.examinationplatform.account.bean.UserInfoBean
import com.weiman.exam.examinationplatform.base.BaseFragment
import com.weiman.exam.examinationplatform.base.http.HttpRequestUtils
import com.weiman.exam.examinationplatform.base.http.HttpTaskListener
import com.weiman.exam.examinationplatform.home.contact.HomeFragmentContact
import com.weiman.exam.examinationplatform.home.presenter.HomeFragmentPresenter
import kotlinx.android.synthetic.main.layout_test.view.*
import java.util.*

/**
 * 创建 by 刘宇飞 on 2017/7/13.
 * 邮箱：3494576680@qq.com
 * 微迈科技有限责任公司
 * 描述
 */
class HomeFragment : BaseFragment<HomeFragmentPresenter>(), HomeFragmentContact.View, HttpTaskListener {


    override fun setLayoutId(): Int {
        return R.layout.layout_test
    }

    override fun initView() {
        showTitleBar()
        setTitle("首页")
        mChildView.tv_show.text = "首页"
        getData()
    }

    private fun getData() {

        val map = HashMap<String, Any>()
        map.put("phone", "15506200515")
        map.put("code", "4986")
        map.put("userType", "2")
        map.put("alias", "ffffffff_c7a8_3c15_0000_00004ca6b30b")
        map.put("source", "APP")
        map.put("userId", "280")
        HttpRequestUtils.getInstance()
                .setContext(mContext)
                .setRequestId(10)
                .setCallBack(this)
                .setObservable(mHttpTask.requestLogin(map))
                .create()

    }

    override fun initPresenter() {
        mPresenter?.setView(this)
    }

    override fun onSuccess(requestId: Int, t: Any?) {
        when (requestId) {
            10 ->
                mChildView.tv_show.text = "请求网络成功:" + (t as UserInfoBean).phone
        }
    }

    override fun onException(requestId: Int, code: Int) {

    }
}