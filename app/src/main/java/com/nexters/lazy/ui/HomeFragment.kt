package com.nexters.lazy.ui

import android.os.Bundle
import android.view.View
import com.igalata.bubblepicker.adapter.BubblePickerAdapter
import com.igalata.bubblepicker.model.BubbleGradient
import com.igalata.bubblepicker.model.PickerItem
import com.nexters.lazy.R
import com.nexters.lazy.adapter.HomeAdapter
import com.nexters.lazy.base.BaseFragment
import com.nexters.lazy.databinding.FragmentHomeBinding
import com.nexters.presentation.viewmodel.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutResId = R.layout.fragment_home
    override val viewModelClass = HomeViewModel::class

    private val sampleAdapter by lazy { HomeAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        loadSample()
        picker()
    }

    private fun initRecyclerView() {
        with(binding.rvMain) {
            adapter = sampleAdapter
        }
    }

    private fun loadSample() {
        sampleAdapter.notifySample()
    }

    private fun picker(){
        binding.picker.adapter = object : BubblePickerAdapter {
            override val totalCount = 15

            override fun getItem(position: Int): PickerItem {
                return PickerItem().apply {
                    gradient = when(position%3){
                        0->
                            BubbleGradient(
                                R.color.white,
                                R.color.white,
                                BubbleGradient.VERTICAL
                            )
                        1->
                            BubbleGradient(
                                R.color.white,
                                R.color.white,
                                BubbleGradient.VERTICAL
                            )
                        2->
                            BubbleGradient(
                                R.color.white,
                                R.color.white,
                                BubbleGradient.VERTICAL
                            )
                        else->
                            BubbleGradient(
                                R.color.white,
                                R.color.white,
                                BubbleGradient.VERTICAL
                            )
                    }
                }
            }
        }
        binding.picker.bubbleSize = 8
    }

    override fun onResume() {
        super.onResume()
        binding.picker.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.picker.onPause()

    }

    companion object{
        private var instance : HomeFragment? = null
        fun getInstance() : HomeFragment = instance ?: HomeFragment()
    }
}