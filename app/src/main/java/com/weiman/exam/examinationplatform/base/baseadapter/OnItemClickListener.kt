package com.weiman.exam.examinationplatform.base.baseadapter

import android.view.View

/**
 * Created by jingbin on 2016/3/2.
 */
interface OnItemClickListener<T> {
    fun onClick(view: View, t: T, position: Int)
}
