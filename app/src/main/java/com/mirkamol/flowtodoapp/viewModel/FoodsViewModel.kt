package com.mirkamol.flowtodoapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirkamol.flowtodoapp.data.local.entity.CreateFood
import com.mirkamol.flowtodoapp.repository.MainRepository
import com.mirkamol.flowtodoapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodsViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val foods = MutableStateFlow<Resource<List<CreateFood>>>(Resource.loading(null))

    @ExperimentalCoroutinesApi
    fun fetchFoods() {
        viewModelScope.launch {
            repository.getCreateFood()
                .catch { e ->
                    foods.value = (Resource.error(e.toString(), null))
                }
                .collect {
                    foods.value = (Resource.success(it))
                }
        }
    }

    @ExperimentalCoroutinesApi
    fun getCreateFoods(): StateFlow<Resource<List<CreateFood>>> {
        return foods
    }

}