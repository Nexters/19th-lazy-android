package com.nexters.lazy.base.recyclerview

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import com.nexters.lazy.BR

open class BaseViewHolder<T : BaseItem>(open val binding : ViewDataBinding)
    : RecyclerView.ViewHolder(binding.root), LifecycleOwner{

    private val lifecycleRegistry by lazy(LazyThreadSafetyMode.NONE) { LifecycleRegistry(this) }

    init {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    open fun bind(item : T){
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }

    fun attach() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    fun detach() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    }
}