package com.mirkamol.flowtodoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mirkamol.flowtodoapp.R
import com.mirkamol.flowtodoapp.data.local.entity.HistoryModel
import com.mirkamol.flowtodoapp.databinding.FragmentAddFoodBinding
import com.mirkamol.flowtodoapp.databinding.FragmentBalanceBinding
import com.mirkamol.flowtodoapp.viewModel.AddViewModel
import com.mirkamol.flowtodoapp.viewModel.BalanceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BalanceFragment : Fragment(R.layout.fragment_balance) {
    private val binding by viewBinding(FragmentBalanceBinding::bind)
    private val viewModel: BalanceViewModel by viewModels()

    var foodName: String? = null
    var quantity: String? = null
    var price: String? = null
    var kafeName: String? = null
    var countPerson: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            if (it != null) {
                kafeName = it.get("kafeName").toString()
                countPerson = it.get("countPerson").toString()
                foodName = it.get("foodName").toString()
                quantity = it.get("quantity").toString()
                price = it.get("price").toString()

            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addFoodDatabase()
        initViews()
        onBackClick()

    }

    private fun onBackClick() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initViews() {
        binding.apply {
            tvSum.text = "Xisob:  ${countPerson!!.toLong() * price!!.toLong()}"
            tvKfName.text = "kafeName:  " + kafeName
            tvCount.text = "countPerson:  " + countPerson
            tvName.text = "foodName:  " + foodName
            tvQuantity.text = "quantity:  " + quantity
            tvPrice.text = "price:  " + price
        }
    }

    private fun addFoodDatabase() {
        if (kafeName != null &&
            countPerson != null
            && foodName != null
            && quantity != null
            && price != null) {
            viewModel.addFoodHistory(
                HistoryModel(
                    name = kafeName!!,
                    count = countPerson!!,
                    foodName = foodName!!,
                    quantity = quantity!!,
                    price = price!!,
                )
            )
        }

    }
}