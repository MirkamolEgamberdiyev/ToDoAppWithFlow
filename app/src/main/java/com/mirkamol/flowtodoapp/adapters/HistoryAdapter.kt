package com.mirkamol.flowtodoapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mirkamol.flowtodoapp.data.local.entity.HistoryModel
import com.mirkamol.flowtodoapp.databinding.HistoryItemBinding

class HistoryAdapter : ListAdapter<HistoryModel,
        HistoryAdapter.Vh>(MyDiffUtil()) {
    inner class Vh(
        private var itemHistoryBinding: HistoryItemBinding
    ) :
        RecyclerView.ViewHolder(itemHistoryBinding.root) {

        fun onBind(food: HistoryModel) {
            itemHistoryBinding.apply {
                tvName.text = "Food Name : " + food.foodName
                tvQuantity.text = "Quantity : " + food.quantity
                tvPrice.text ="Price : " + food.price
                tvKfName.text = "kafeName:  " + food.name
                tvCount.text = "countPerson:   " + food.count + " ta"
            }
        }
    }
    class MyDiffUtil : DiffUtil.ItemCallback<HistoryModel>() {
        override fun areItemsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean {
            return oldItem.foodName == newItem.foodName
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(
            HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position))
    }
}