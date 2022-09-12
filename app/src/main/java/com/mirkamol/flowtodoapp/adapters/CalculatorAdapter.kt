package com.mirkamol.flowtodoapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mirkamol.flowtodoapp.databinding.ItemCalculatorBinding

class CalculatorAdapter (val list: ArrayList<String>, var listener: (String) -> Unit) :
    RecyclerView.Adapter<CalculatorAdapter.VH>() {

    inner class VH(private val binding: ItemCalculatorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String) {
            binding.apply {
                itemRecycler.text = data
                itemRecycler.setOnClickListener {
                    Log.d("TAG", "bind: $data")
                    listener.invoke(data)

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemCalculatorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(list.get(position))

    override fun getItemCount(): Int = list.size
}