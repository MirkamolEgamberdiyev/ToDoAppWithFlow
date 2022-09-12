package com.mirkamol.flowtodoapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirkamol.flowtodoapp.data.local.entity.CreateFood
import com.mirkamol.flowtodoapp.data.local.entity.HistoryModel
import com.mirkamol.flowtodoapp.repository.MainRepository
import com.mirkamol.flowtodoapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val historyFoods = MutableStateFlow<Resource<List<HistoryModel>>>(Resource.loading(null))
    @ExperimentalCoroutinesApi
    fun fetchFoods() {
        viewModelScope.launch {
            repository.getFoodHistory()
                .catch { e ->
                    historyFoods.value = (Resource.error(e.toString(), null))
                }
                .collect {
                    historyFoods.value = (Resource.success(it))
                }

        }
    }

    @ExperimentalCoroutinesApi
    fun getHistoryFoods(): StateFlow<Resource<List<HistoryModel>>> {
        return historyFoods
    }
}