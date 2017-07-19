package com.weiman.exam.examinationplatform.home.presenter

import android.support.v7.widget.LinearLayoutManager
import com.example.xrecyclerview.XRecyclerView
import com.weiman.exam.examinationplatform.home.adapter.TestAdapter
import com.weiman.exam.examinationplatform.home.contact.ExamFragmentContact
import com.weiman.exam.examinationplatform.home.data.TestModel

/**
 * 创建 by 刘宇飞 on 2017/7/13.
 * 邮箱：3494576680@qq.com
 * 微迈科技有限责任公司
 * 描述
 */
class ExamFragmentPresenter : ExamFragmentContact.Presenter(), XRecyclerView.LoadingListener {


    lateinit var adapter: TestAdapter
    lateinit var xRecylerView:XRecyclerView
    override fun intRecyclerView(recyclerView: XRecyclerView) {
        xRecylerView=recyclerView
        recyclerView.setHasFixedSize(false)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.setLoadingListener(this)
        recyclerView.layoutManager=LinearLayoutManager(mContext)
        adapter = TestAdapter()
        recyclerView.adapter = adapter
        initData(false)

    }

    override fun initData(b: Boolean) {
        if (!b) adapter.clear()
        for (i in 1..10) {
            var model = TestModel("item:" + i)
            model.viewType=TestAdapter().TEST_ITEM
            adapter.add(model)
        }
        adapter.notifyDataSetChanged()
        xRecylerView.refreshComplete()
    }

    override fun onRefresh() {
      initData(false)
    }

    override fun onLoadMore() {
        initData(true)
    }

}