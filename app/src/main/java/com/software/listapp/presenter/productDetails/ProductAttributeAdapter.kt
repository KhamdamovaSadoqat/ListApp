package com.software.listapp.presenter.productDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.software.listapp.databinding.ItemProductDescriptionBinding
import com.software.listapp.domain.main.product.ProductAttributesEntity
import com.software.listapp.utils.AutoUpdatableAdapter
import kotlin.properties.Delegates

class ProductAttributeAdapter:
    RecyclerView.Adapter<ProductAttributeAdapter.VH>(),
    AutoUpdatableAdapter {

    var items: List<ProductAttributesEntity> by Delegates.observable(emptyList()) { _, old, new ->
        autoNotify(old, new) { o, n -> o == n }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(
            ItemProductDescriptionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class VH(private val binding: ItemProductDescriptionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(productAttributesEntity: ProductAttributesEntity) {
            binding.apply {
                tvAttribute.text = productAttributesEntity.name
                tvValue.text = productAttributesEntity.value
                  }
        }
    }

}