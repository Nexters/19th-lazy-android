package com.nexters.lazy.base

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.nexters.presentation.base.BaseViewModel
import kotlin.reflect.KClass
import com.nexters.lazy.BR

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
    protected abstract val layoutResId: Int
    protected abstract val viewModelClass: KClass<VM>

    lateinit var binding: VB

    protected val viewModel: VM by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(viewModelStore, defaultViewModelProviderFactory).get(viewModelClass.java)
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.TRANSPARENT

        binding = DataBindingUtil.setContentView(this, layoutResId)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
    }
}