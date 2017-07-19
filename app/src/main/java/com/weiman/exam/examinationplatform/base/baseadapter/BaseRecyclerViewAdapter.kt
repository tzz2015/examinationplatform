package com.weiman.exam.examinationplatform.base.baseadapter


import android.support.v7.widget.RecyclerView
import android.view.View
import java.util.*

/**
 * Created by jingbin on 2016/11/25
 */
abstract class BaseRecyclerViewAdapter : RecyclerView.Adapter<BaseRecyclerViewHolder<*>>() {
    private var data: MutableList<BaseRecyclerModel> = ArrayList()
    internal var listener: OnItemClickListener<BaseRecyclerModel>? = null
    internal var onItemLongClickListener: OnItemLongClickListener<BaseRecyclerModel>? = null
    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<*>?, position: Int) {
        holder?.onBaseBindViewHolder(data[position], position)
        holder?.itemView?.setOnClickListener({
            v ->
            listener?.onClick(v, data[position], position)
        })
        holder?.itemView?.setOnLongClickListener(View.OnLongClickListener { v ->
            if (onItemLongClickListener != null) {
                onItemLongClickListener?.onLongClick(v, data[position], position)
                return@OnLongClickListener true
            }
            false
        })
    }

    override fun getItemViewType(position: Int): Int {
        if (data[position].viewType != 0)
            return data[position].viewType
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addAll(data: List<BaseRecyclerModel>) {
        this.data.addAll(data)
    }

    fun add(model: BaseRecyclerModel) {
        data.add(model)
    }

    fun clear() {
        data.clear()
    }

    fun remove(model: BaseRecyclerModel) {
        data.remove(model)
    }

    fun remove(position: Int) {
        data.removeAt(position)
    }

    fun removeAll(data: List<BaseRecyclerModel>) {
        this.data.retainAll(data)
    }

    fun setOnItemClickListener(listener: OnItemClickListener<BaseRecyclerModel>) {
        this.listener = listener
    }


    fun getData(): List<BaseRecyclerModel> {
        return data
    }

    fun setOnItemLongClickListener(onItemLongClickListener: OnItemLongClickListener<BaseRecyclerModel>) {
        this.onItemLongClickListener = onItemLongClickListener
    }
}
