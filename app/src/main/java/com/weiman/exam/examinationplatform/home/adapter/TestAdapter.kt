package com.weiman.exam.examinationplatform.home.adapter

import android.view.ViewGroup
import com.weiman.exam.examinationplatform.R
import com.weiman.exam.examinationplatform.base.baseadapter.BaseRecyclerViewAdapter
import com.weiman.exam.examinationplatform.base.baseadapter.BaseRecyclerViewHolder
import com.weiman.exam.examinationplatform.home.holder.TestHolder

/**
 * 创建 by 刘宇飞 on 2017/7/19.
 * 邮箱：3494576680@qq.com
 * 微迈科技有限责任公司
 * 描述
 */
class TestAdapter :BaseRecyclerViewAdapter(){
    val TEST_ITEM = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<*>? {
        when(viewType){
            TEST_ITEM-> return TestHolder(parent, R.layout.layout_test_item)
        }
        return null
    }
}