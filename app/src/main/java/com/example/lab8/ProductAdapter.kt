package com.example.lab8

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lab8.databinding.ItemProductBinding

class ProductAdapter(private val onClick: (Product) -> Unit)
    : ListAdapter<Product, ProductAdapter.VH>(ProductDiff()) {

    inner class VH(private val binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product) {
            binding.tvTitle.text = item.title
            binding.tvPrice.text = "${item.price}"
            binding.tvDesc.text = item.description
            Glide.with(binding.imgProduct.context)
                .load(item.image)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(binding.imgProduct)
            binding.root.setOnClickListener { onClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    class ProductDiff: DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(old: Product, new: Product) = old.id == new.id
        override fun areContentsTheSame(old: Product, new: Product) = old == new
    }
}
