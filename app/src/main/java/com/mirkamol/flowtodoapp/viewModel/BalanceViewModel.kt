package com.mirkamol.flowtodoapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirkamol.flowtodoapp.data.local.entity.HistoryModel
import com.mirkamol.flowtodoapp.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BalanceViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    fun addFoodHistory(food: HistoryModel) = viewModelScope.launch {
        repository.addFood(food)
    }

}