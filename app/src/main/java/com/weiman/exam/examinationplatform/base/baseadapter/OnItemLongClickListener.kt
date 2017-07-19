package com.weiman.exam.examinationplatform.base.baseadapter

import android.view.View

/**
 * Created by jingbin on 16/7/4.
 */
interface OnItemLongClickListener<T> {
    fun onLongClick(view: View, t: T, position: Int)
}
