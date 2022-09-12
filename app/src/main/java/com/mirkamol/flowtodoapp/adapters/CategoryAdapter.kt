package com.mirkamol.flowtodoapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mirkamol.flowtodoapp.data.local.entity.Category
import com.mirkamol.flowtodoapp.databinding.ItemCategoryBinding


class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.VH>() {

    private val dif = AsyncListDiffer(this, ITEM_DIFF)
    private var onClick: ((Category) -> Unit)? = null

    inner class VH(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val category = dif.currentList[adapterPosition]
            binding.apply {
                tvName.text = category.name
                Glide.with(root.context).load(category.image)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(ivImage)

                root.setOnClickListener {
                    onClick?.invoke(category)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()

    override fun getItemCount(): Int = dif.currentList.size

    fun submitList(list: List<Category>) {
        dif.submitList(list)
    }
    fun onClick(category: (Category) -> Unit) {
        onClick = category
    }
    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean =
                oldItem == newItem
        }
    }
}

