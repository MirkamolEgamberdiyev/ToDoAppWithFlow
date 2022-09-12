package com.mirkamol.flowtodoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mirkamol.flowtodoapp.R
import com.mirkamol.flowtodoapp.adapters.FoodsAdapter
import com.mirkamol.flowtodoapp.data.local.entity.PassFood
import com.mirkamol.flowtodoapp.databinding.FragmentAddFoodBinding
import com.mirkamol.flowtodoapp.databinding.FragmentFoodsBinding
import com.mirkamol.flowtodoapp.utils.Status
import com.mirkamol.flowtodoapp.viewModel.AddViewModel
import com.mirkamol.flowtodoapp.viewModel.FoodsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
@AndroidEntryPoint
class FoodsFragment : Fragment(R.layout.fragment_foods) {
    private val binding by viewBinding(FragmentFoodsBinding::bind)
    private val viewModel: FoodsViewModel by viewModels()
    private lateinit var adapter: FoodsAdapter
    private  val TAG = "FoodsFragment"
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        loadRecourse()
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        viewModel.fetchFoods()

    }


    @OptIn(ExperimentalCoroutinesApi::class)
    private fun loadRecourse() {
        lifecycleScope.launch{
            val value = viewModel.getCreateFoods()
            value.collect{
                when(it.status){
                    Status.LOADING ->{
                    }
                    Status.SUCCESS ->{
                        adapter.submitList(it.data)
                    }
                    Status.ERROR ->{
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setupAdapter() {
        adapter = FoodsAdapter {
            setBackStackData("key", PassFood(it.foodName, it.price, it.quantity))
            findNavController().popBackStack()
        }
        binding.recyclerView.adapter = adapter
    }


    fun <T> Fragment.setBackStackData(key: String, data: T, doBack: Boolean = false) {
        findNavController().previousBackStackEntry?.savedStateHandle?.set(key, data)
        if (doBack)
            findNavController().popBackStack()
    }


}