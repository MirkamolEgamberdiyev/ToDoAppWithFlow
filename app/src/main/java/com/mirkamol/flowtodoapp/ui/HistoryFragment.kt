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
import com.mirkamol.flowtodoapp.adapters.HistoryAdapter
import com.mirkamol.flowtodoapp.data.local.entity.HistoryModel
import com.mirkamol.flowtodoapp.databinding.FragmentHistoryBinding
import com.mirkamol.flowtodoapp.utils.Resource
import com.mirkamol.flowtodoapp.utils.Status
import com.mirkamol.flowtodoapp.viewModel.CreateViewModel
import com.mirkamol.flowtodoapp.viewModel.HistoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.fragment_history) {
    private val binding by viewBinding(FragmentHistoryBinding::bind)
    private val adapter by lazy { HistoryAdapter() }
    private val viewModel: HistoryViewModel by viewModels()
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadRecourse()
        onBackClick()
        viewModel.fetchFoods()
    }

    private fun onBackClick() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun loadRecourse() {
        lifecycleScope.launch{
            val value = viewModel.getHistoryFoods()
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

        binding.recyclerView.adapter = adapter

    }

}