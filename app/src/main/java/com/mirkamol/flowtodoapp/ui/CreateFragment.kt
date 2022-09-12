package com.mirkamol.flowtodoapp.ui

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mirkamol.flowtodoapp.R
import com.mirkamol.flowtodoapp.adapters.CreateAdapter
import com.mirkamol.flowtodoapp.data.local.entity.Food
import com.mirkamol.flowtodoapp.data.local.entity.PassFood
import com.mirkamol.flowtodoapp.databinding.FragmentCreateBinding
import com.mirkamol.flowtodoapp.viewModel.CreateViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateFragment : Fragment(R.layout.fragment_create) {
    private val binding by viewBinding(FragmentCreateBinding::bind)
    private val adapter by lazy { CreateAdapter() }
    private val createViewModel: CreateViewModel by viewModels()
    var foodName: String? = null
    var quantity: String? = null
    var price: String? = null
    val list = ArrayList<Food>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setUpAdapter()

    }
    private fun setUpAdapter() {
        getBackStackData<PassFood>("key", true) {
            foodName = it.foodName
            quantity = it.quantity
            price = it.price
            list.add(
                Food(
                    foodName = foodName!!, price = price!!, quantity = quantity!!
                )
            )
            createViewModel.foodList.value = list

            createViewModel.foodList.observe(viewLifecycleOwner) {
                adapter.submitList(it)

                if (it.size > 0) {
                    binding.tvMenu.text = "Yana qo'shish"
                }
            }
        }

        binding.recyclerView.adapter = adapter
    }
    private fun initViews() {
        binding.tvMenu.setOnClickListener {
            showPopupWindow()
        }
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnXisob.setOnClickListener {
            if (binding.edtName.text.toString().isNotEmpty() && binding.edtPerson.text.toString()
                    .isNotEmpty()
            ) {
                findNavController().navigate(
                    R.id.action_createFragment_to_balanceFragment,
                    bundleOf(
                        "kafeName" to binding.edtName.text.toString(),
                        "countPerson" to binding.edtPerson.text.toString(),
                        "foodName" to foodName,
                        "price" to price,
                        "quantity" to quantity
                    )
                )
            }
            Toast.makeText(context, "xisoblash uchun food kiritilmagan!", Toast.LENGTH_SHORT).show()
        }
        binding.btnCalculate.setOnClickListener {
            findNavController().navigate(R.id.calculatorFragment)
        }

    }

    private fun showPopupWindow() {
        val popWindow = PopupWindow(activity)
        popWindow.elevation = 10F
        popWindow.setBackgroundDrawable(null)
        popWindow.isFocusable = true
        popWindow.animationStyle.and(R.style.circle)

        val view = layoutInflater.inflate(R.layout.dialog_item, null)
        popWindow.contentView = view
//        popWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
        popWindow.showAsDropDown(binding.tvMenu)

        val addFood = view.findViewById<AppCompatTextView>(R.id.tv_add_food)
        val takeList = view.findViewById<AppCompatTextView>(R.id.tv_take)


        addFood.setOnClickListener {
            findNavController().navigate(R.id.addFoodFragment)
            popWindow.dismiss()
        }
        takeList.setOnClickListener {
            findNavController().navigate(R.id.foodsFragment)
            popWindow.dismiss()
        }

    }

    private fun <T> Fragment.getBackStackData(
        key: String,
        singleCall: Boolean = true,
        result: (T) -> (Unit)
    ) {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)
            ?.observe(viewLifecycleOwner) {
                result(it)
                if (singleCall) findNavController().currentBackStackEntry?.savedStateHandle?.remove<T>(
                    key
                )
            }
    }

}