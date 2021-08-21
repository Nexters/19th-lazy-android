package com.nexters.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.nexters.presentation.base.BaseViewModel

sealed class GoPage
object HabitForm : GoPage()
object HabitDetail : GoPage()

class HomeViewModel : BaseViewModel() {

    val goPage = MutableLiveData<GoPage>()

//    private val isReady = MutableLiveData(0)
//    private var entireHeight : Int = 0
//        set(value) {
//            isReady.value = isReady.value.let {
//                it+1
//            }
//        field = value
//    }
//    private val scrollTitleHeight = MutableLiveData<Int>()
//    private val scrollContentHeight = MutableLiveData<Int>()


    fun onClickAdd() {
        if(validToAddHabit()){
            goPage.value = HabitForm
        }else{
            //Todo 갯수 제안 알리는 팝업 띄우기
        }
    }

    private fun validToAddHabit(): Boolean {
        //Todo 습관 개수 체크하는 로직
        return true
    }
}