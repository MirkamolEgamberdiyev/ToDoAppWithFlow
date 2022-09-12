package com.mirkamol.flowtodoapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirkamol.flowtodoapp.data.local.entity.CreateFood
import com.mirkamol.flowtodoapp.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    fun addCreateFood(food: CreateFood) {
        viewModelScope.launch {
            repository.addCreateFood(food)
        }
    }
}