package com.mirkamol.flowtodoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mirkamol.flowtodoapp.R
import com.mirkamol.flowtodoapp.data.local.entity.CreateFood
import com.mirkamol.flowtodoapp.data.local.entity.PassFood
import com.mirkamol.flowtodoapp.databinding.FragmentAddFoodBinding
import com.mirkamol.flowtodoapp.databinding.FragmentCreateBinding
import com.mirkamol.flowtodoapp.viewModel.AddViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFoodFragment : Fragment(R.layout.fragment_add_food) {
    private val binding by viewBinding(FragmentAddFoodBinding::bind)
    private val viewModel: AddViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        onBackClick()

    }

    private fun onBackClick() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initViews() {

        binding.btnAdd.setOnClickListener {
            val foodName = binding.edtName.text.toString().trim()
            val quantity = binding.edtQuantity.text.toString().trim()
            val price = binding.edtPrice.text.toString().trim()

            if (foodName.isNotEmpty() && quantity.isNotEmpty() && price.isNotEmpty()) {
                viewModel.addCreateFood(
                    CreateFood(
                        foodName = foodName,
                        quantity = quantity,
                        price = price
                    )
                )
                setBackStackData("key", PassFood(foodName, quantity, price), true)

            } else {
                Toast.makeText(context, "Enter datas!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun <T> Fragment.setBackStackData(key: String, data: T, doBack: Boolean = false) {
        findNavController().previousBackStackEntry?.savedStateHandle?.set(key, data)
        if (doBack)
            findNavController().popBackStack()
    }

}