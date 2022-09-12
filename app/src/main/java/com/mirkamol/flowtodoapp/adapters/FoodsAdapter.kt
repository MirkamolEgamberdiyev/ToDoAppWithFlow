package com.mirkamol.flowtodoapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mirkamol.flowtodoapp.data.local.entity.CreateFood
import com.mirkamol.flowtodoapp.databinding.ItemFoodsBinding

class FoodsAdapter (private val itemCLick: (CreateFood) -> Unit) : ListAdapter<CreateFood, FoodsAdapter.Vh>(MyDiffUtil()) {

    inner class Vh(
        private var itemHistoryBinding: ItemFoodsBinding
    ) :
        RecyclerView.ViewHolder(itemHistoryBinding.root) {

        fun onBind(food: CreateFood) {
            itemHistoryBinding.apply {
                tvName.text = "foodname:  "+food.foodName
                tvQuantity.text = "food quantity:  "+food.quantity
                tvPrice.text = "food price:  "+food.price
                itemHistoryBinding.itemCard.setOnClickListener {
                    itemCLick.invoke(food)
                }
            }
        }
    }
    class MyDiffUtil : DiffUtil.ItemCallback<CreateFood>() {
        override fun areItemsTheSame(oldItem: CreateFood, newItem: CreateFood): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CreateFood, newItem: CreateFood): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(
            ItemFoodsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position))
    }
}