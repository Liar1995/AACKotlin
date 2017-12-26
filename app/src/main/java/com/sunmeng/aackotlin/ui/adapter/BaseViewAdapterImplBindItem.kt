package com.sunmeng.aackotlin.ui.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR
import com.sunmeng.aackotlin.ui.listener.ItemClickPresenter

/**
 * Created by sunmeng on 2017/12/25.
 * Email:sunmeng995@gmail.com
 * Description:
 */
abstract class BaseViewAdapterImplBindItem <M, in B : ViewDataBinding>(private var context: Context, private val item_user: Int)
    : RecyclerView.Adapter<BaseBindingViewHolder<ViewDataBinding>>() {

    var items: ObservableList<M> = ObservableArrayList<M>()
    private var itemsChangeCallback:ListChangedCallback=ListChangedCallback()
    var itemPresenter: ItemClickPresenter<M>? = null
    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseBindingViewHolder<ViewDataBinding> {
        return BaseBindingViewHolder(DataBindingUtil.inflate<B>(LayoutInflater.from(this.context), item_user, parent, false))
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder<ViewDataBinding>?, position: Int) {
        val binding = DataBindingUtil.getBinding<B>(holder?.itemView)
        val item = items[position]
        holder?.binding?.setVariable(BR.item, item)
        holder?.binding?.setVariable(BR.presenter, itemPresenter)
        holder?.binding?.executePendingBindings()
        this.onBindItem(binding, this.items[position],position)
    }

    protected abstract fun onBindItem(binding: B, item: M, position: Int)


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
        items.addOnListChangedCallback(itemsChangeCallback)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView?) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.items.removeOnListChangedCallback(itemsChangeCallback)
    }

    protected fun onChanged(newItems: ObservableArrayList<M>) {
        resetItems(newItems)
        notifyDataSetChanged()
    }

    protected fun onItemRangeChanged(newItems: ObservableArrayList<M>, positionStart: Int, itemCount: Int) {
        resetItems(newItems)
        notifyItemRangeChanged(positionStart, itemCount)
    }

    protected fun onItemRangeInserted(newItems: ObservableArrayList<M>, positionStart: Int, itemCount: Int) {
        resetItems(newItems)
        notifyItemRangeInserted(positionStart, itemCount)
    }

    protected fun onItemRangeMoved(newItems: ObservableArrayList<M>) {
        resetItems(newItems)
        notifyDataSetChanged()
    }

    protected fun onItemRangeRemoved(newItems: ObservableArrayList<M>, positionStart: Int, itemCount: Int) {
        resetItems(newItems)
        notifyItemRangeRemoved(positionStart, itemCount)
    }

    private fun resetItems(newItems: ObservableArrayList<M>) {
        this.items = newItems
    }

    internal inner class ListChangedCallback : ObservableList.OnListChangedCallback<ObservableArrayList<M>>() {
        override fun onChanged(newItems: ObservableArrayList<M>) {
            this@BaseViewAdapterImplBindItem.onChanged(newItems)
        }

        override fun onItemRangeChanged(newItems: ObservableArrayList<M>, i: Int, i1: Int) {
            this@BaseViewAdapterImplBindItem.onItemRangeChanged(newItems, i, i1)
        }

        override fun onItemRangeInserted(newItems: ObservableArrayList<M>, i: Int, i1: Int) {
            this@BaseViewAdapterImplBindItem.onItemRangeInserted(newItems, i, i1)
        }

        override fun onItemRangeMoved(newItems: ObservableArrayList<M>, i: Int, i1: Int, i2: Int) {
            this@BaseViewAdapterImplBindItem.onItemRangeMoved(newItems)
        }

        override fun onItemRangeRemoved(sender: ObservableArrayList<M>, positionStart: Int, itemCount: Int) {
            this@BaseViewAdapterImplBindItem.onItemRangeRemoved(sender, positionStart, itemCount)
        }
    }

}