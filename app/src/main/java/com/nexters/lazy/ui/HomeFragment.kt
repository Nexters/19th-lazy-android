package com.nexters.lazy.ui

import android.os.Bundle
import android.view.View
import com.nexters.lazy.R
import com.nexters.lazy.base.BaseFragment
import com.nexters.lazy.databinding.FragmentHomeBinding
import com.nexters.presentation.viewmodel.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutResId = R.layout.fragment_home
    override val viewModelClass = HomeViewModel::class

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object{
        private var instance : HomeFragment? = null
        fun getInstance() : HomeFragment = instance ?: HomeFragment()
    }
}