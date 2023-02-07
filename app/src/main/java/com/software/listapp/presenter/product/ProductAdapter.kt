package com.software.listapp.presenter.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.software.listapp.databinding.ItemProductBinding
import com.software.listapp.domain.main.product.ProductEntity
import com.software.listapp.utils.AutoUpdatableAdapter
import com.software.listapp.utils.ImageDownloader
import kotlin.properties.Delegates

class ProductAdapter (private val itemClickListener: (ProductEntity) -> Unit) :
    RecyclerView.Adapter<ProductAdapter.VH>(),
    AutoUpdatableAdapter {

    var items: List<ProductEntity> by Delegates.observable(emptyList()) { _, old, new ->
        autoNotify(old, new) { o, n -> o == n }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(items[position])
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(items[position])
        }
    }

    override fun getItemCount(): Int = items.size

    class VH(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(product: ProductEntity) {
            binding.apply {
                tvProductName.text = product.name
                tvProductSeller.text = product.merchant
                tvProductBrand.text = product.brand
                ImageDownloader.loadImage(binding.root.context, product.imageUrl, ivProduct)
            }
        }
    }
}