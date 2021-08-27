package com.nexters.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.nexters.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JoinViewModel @Inject constructor() : BaseViewModel() {
    private val state: State = State()

    fun state() = state

    data class State(
        val nickname : MutableLiveData<String> = MutableLiveData()
    )
}