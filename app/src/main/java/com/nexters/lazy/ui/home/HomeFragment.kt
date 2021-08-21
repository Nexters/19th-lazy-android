package com.nexters.lazy.ui.home

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.igalata.bubblepicker.adapter.BubblePickerAdapter
import com.igalata.bubblepicker.model.BubbleGradient
import com.igalata.bubblepicker.model.PickerItem
import com.nexters.lazy.R
import com.nexters.lazy.adapter.home.HomeAdapter
import com.nexters.lazy.base.BaseFragment
import com.nexters.lazy.databinding.FragmentHomeBinding
import com.nexters.lazy.habitform.HabitFormActivity
import com.nexters.lazy.habitform.HabitFormType
import com.nexters.presentation.viewmodel.HabitForm
import com.nexters.presentation.viewmodel.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutResId = R.layout.fragment_home
    override val viewModelClass = HomeViewModel::class

    private val sampleAdapter by lazy { HomeAdapter() }

    private val launchOfHabitForm =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                //Todo 데이터 새로 갱신하는 등등의 리프레시 처리
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = this.viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()

        observeViewModel()
        initView()
        initRecyclerView()
        loadSample()
        picker()
    }

    private fun observeViewModel() {
        viewModel.goPage.observe(viewLifecycleOwner, {
            when (it) {
                HabitForm -> {
                    launchOfHabitForm.launch(
                        HabitFormActivity.newIntent(
                            requireContext(),
                            HabitFormType.REGISTER
                        )
                    )
                }
            }
        })
    }

    private fun initView() {
        binding.collapsingLayout.viewTreeObserver.addOnGlobalLayoutListener {
            with(binding) {
                val coordinatorHeight = containerCoordinator.height
                val scrollHeight = containerScroll.height
                val rvHeight = rvMain.height
                collapsingLayout.minimumHeight = coordinatorHeight - scrollHeight
                appBarLayout.layoutParams.height = collapsingLayout.minimumHeight + rvHeight
            }
        }
        binding.appBarLayout.setExpanded(false)
    }

    private fun initRecyclerView() {
        with(binding.rvMain) {
            adapter = sampleAdapter
        }
    }

    private fun loadSample() {
        sampleAdapter.notifySample()
    }

    // code to remove
    private fun picker() {
        binding.picker.adapter = object : BubblePickerAdapter {
            override val totalCount = 15

            override fun getItem(position: Int): PickerItem {
                return PickerItem().apply {
                    gradient = when (position % 3) {
                        0 ->
                            BubbleGradient(
                                R.color.white,
                                R.color.white,
                                BubbleGradient.VERTICAL
                            )
                        1 ->
                            BubbleGradient(
                                R.color.white,
                                R.color.white,
                                BubbleGradient.VERTICAL
                            )
                        2 ->
                            BubbleGradient(
                                R.color.white,
                                R.color.white,
                                BubbleGradient.VERTICAL
                            )
                        else ->
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

    companion object {
        private var instance: HomeFragment? = null
        fun getInstance(): HomeFragment = instance ?: HomeFragment()
    }
}