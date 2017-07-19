package com.weiman.exam.examinationplatform.home.holder

import android.view.ViewGroup
import com.weiman.exam.examinationplatform.base.baseadapter.BaseRecyclerModel
import com.weiman.exam.examinationplatform.base.baseadapter.BaseRecyclerViewHolder
import com.weiman.exam.examinationplatform.databinding.LayoutTestItemBinding
import com.weiman.exam.examinationplatform.home.data.TestModel


/**

 * 刘宇飞创建 on 2017/5/18.
 * 描述：
 */
class TestHolder(parent: ViewGroup, title: Int) : BaseRecyclerViewHolder<LayoutTestItemBinding>(parent, title) {
    override fun onBindViewHolder(model: BaseRecyclerModel, position: Int) {
        if (model is TestModel) {
            binding.item = model
        }
    }


}
