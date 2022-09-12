package com.mirkamol.flowtodoapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mirkamol.flowtodoapp.data.local.entity.Food
import com.mirkamol.flowtodoapp.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(private val repository: MainRepository):ViewModel() {
    var foodList = MutableLiveData<ArrayList<Food>>()
}