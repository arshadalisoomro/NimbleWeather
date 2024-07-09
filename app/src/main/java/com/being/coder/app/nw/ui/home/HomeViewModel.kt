package com.being.coder.app.nw.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.being.coder.app.nw.data.dto.CurrentConditionDto
import com.being.coder.app.nw.domain.usecase.GetCurrentConditionUseCase
import com.being.coder.app.nw.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentConditionUseCase: GetCurrentConditionUseCase
): ViewModel(){
//    private val _currentConditionState = MutableStateFlow<Response<CurrentConditionDto>>(Response.Loading)
//    val currentConditionState: StateFlow<Response<CurrentConditionDto>> = _currentConditionState
//
//    fun getCurrentCondition(city: String) {
//        viewModelScope.launch {
//            try {
//                val result = getCurrentConditionUseCase(city)
//                _currentConditionState.value = result.collect{
//                    Log.e("HomeViewModel", "getCurrentCondition: $it")
//                }
//            } catch (e: Exception) {
//                _currentConditionState.value = Response.Error("Network request failed")
//                Log.e("HomeViewModel", "Error: ${e.message}", e)
//            }
//        }
//    }
    var getCurrentConditionWithState: StateFlow<Response<CurrentConditionDto>> = MutableStateFlow(Response.Loading)

    fun getCurrentCondition(city: String){
        viewModelScope.launch {
            Log.e("HomeViewModel", "getCurrentCondition()")
            getCurrentConditionWithState = getCurrentConditionUseCase.invoke(city)
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(),
                    initialValue = getCurrentConditionWithState.value
                )
        }
    }

}