package com.nexters.lazy.base.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nexters.presentation.base.BaseViewModel
import com.nexters.lazy.BR

abstract class BaseAdapter<T : BaseItem>(diffItemCallback: BaseDiffItemCallback<T> = BaseDiffItemCallback()) : ListAdapter<T, BaseViewHolder<T>>(diffItemCallback) {
    var viewModel: BaseViewModel? = null
    private var itemClickListener: ((View, T) -> Unit)? = null

    abstract fun getItemViewTypeByItem(item: T): Int

    override fun onViewAttachedToWindow(holder: BaseViewHolder<T>) {
        super.onViewAttachedToWindow(holder)
        holder.attach()
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<T>) {
        holder.detach()
        super.onViewDetachedFromWindow(holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val inflater = LayoutInflater.from(parent.context)

        val binding = createDataBinding(parent, inflater, getLayoutIdByViewType(viewType))

        val viewHolder = createViewHolder(binding, viewType)

        binding.lifecycleOwner = viewHolder
        binding.setVariable(
            BR.clickListener,
            View.OnClickListener {
                if (viewHolder.adapterPosition != RecyclerView.NO_POSITION) {
                    itemClickListener?.invoke(it, getItem(viewHolder.adapterPosition))
                }
            }
        )
        binding.setVariable(BR.viewModel, viewModel)

        onViewHolderCreated(viewHolder, binding, viewType)

        return viewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        bind(holder, position)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int, payloads: MutableList<Any>) {
        bind(holder, position)
    }

    override fun getItemViewType(position: Int): Int {
        return getItemViewTypeByItem(getItem(position))
    }

    open fun onViewHolderCreated(viewHolder: BaseViewHolder<T>, binding: ViewDataBinding, viewType: Int) {}

    open fun createViewHolder(binding: ViewDataBinding, viewType: Int): BaseViewHolder<T> {
        return BaseViewHolder(binding)
    }

    open fun createDataBinding(parent: ViewGroup, inflater: LayoutInflater, layoutIdByViewType: Int): ViewDataBinding {
        return DataBindingUtil.inflate(inflater, layoutIdByViewType, parent, false)
    }

    open fun getLayoutIdByViewType(viewType: Int): Int {
        return viewType
    }

    open fun bind(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(getItem(position))
    }

    fun setViewHolderViewModel(viewModel: BaseViewModel) {
        this.viewModel = viewModel
    }

    fun setItemClickListener(itemClickListener: (View, T) -> Unit) {
        this.itemClickListener = itemClickListener
    }

    fun getItemByPosition(position: Int): T {
        return getItem(position)
    }
}
