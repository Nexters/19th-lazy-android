package com.nexters.lazy.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.nexters.lazy.BR
import com.nexters.lazy.R
import com.nexters.lazy.databinding.ActivityBaseToolbarBinding
import com.nexters.lazy.gone
import com.nexters.lazy.visible
import com.nexters.presentation.base.BaseViewModel
import kotlin.reflect.KClass


abstract class BaseToolbarActivity<VB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    protected abstract val layoutResId: Int
    protected abstract val viewModelClass: KClass<VM>

    lateinit var binding: VB

    protected val viewModel: VM by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(viewModelStore, defaultViewModelProviderFactory).get(viewModelClass.java)
    }

    private lateinit var rootViewBinding: ActivityBaseToolbarBinding

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rootViewBinding = ActivityBaseToolbarBinding.inflate(layoutInflater)
        setContentView(rootViewBinding.root)

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            layoutResId,
            rootViewBinding.layoutContent,
            true
        )
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
    }

    fun setToolbarTitle(label: String) {
        rootViewBinding.tvTitle.text = label
    }

    fun setToolbarTitle(res: Int) {
        rootViewBinding.tvTitle.text = getString(res)
    }

    protected fun setNaviType(nav: NavType) {
        when (nav) {
            NavType.BACK -> {
                //Todo 이미지 교체
                rootViewBinding.leftImage.setImageResource(R.drawable.slice_2)
                rootViewBinding.leftImage.visible()
            }
            NavType.CLOSE -> {
                rootViewBinding.leftImage.setImageResource(R.drawable.slice_2)
                rootViewBinding.leftImage.visible()
            }
            else -> {
                rootViewBinding.leftImage.gone()
            }
        }
    }
}