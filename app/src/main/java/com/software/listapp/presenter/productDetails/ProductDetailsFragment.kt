package com.software.listapp.presenter.productDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.software.listapp.data.comman.base.BindingFragment
import com.software.listapp.databinding.FragmentProductDetailsBinding
import com.software.listapp.presenter.product.ProductsViewModel
import com.software.listapp.utils.ImageDownloader
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : BindingFragment<FragmentProductDetailsBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentProductDetailsBinding::inflate

    private val viewModel: ProductsViewModel by viewModels()
    private lateinit var adapter: ProductAttributeAdapter
    private val args: ProductDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
        getProductDetails(args.productId)
    }

    private fun setUp() {
        adapter = ProductAttributeAdapter()
        binding.rvProductAttributes.adapter = adapter
    }

    private fun getProductDetails(id: Int) {
        viewModel.getProduct(id).observe(viewLifecycleOwner) { product ->
            binding.apply {
                tvProductName.text = product.name
                tvProductSeller.text = product.merchant
                tvProductBrand.text = product.brand
                ImageDownloader.loadImage(requireContext(), product.imageUrl, ivProduct)
            }
        }
        viewModel.getProductAttributes(id).observe(viewLifecycleOwner) { productAttributes ->
            adapter.items = productAttributes
        }
    }
}