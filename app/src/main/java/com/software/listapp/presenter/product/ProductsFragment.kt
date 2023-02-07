package com.software.listapp.presenter.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.software.listapp.data.comman.base.BindingFragment
import com.software.listapp.databinding.FragmentProductsBinding
import com.software.listapp.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ProductsFragment : BindingFragment<FragmentProductsBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentProductsBinding::inflate

    private val viewModel: ProductsViewModel by viewModels()
    private lateinit var adapter: ProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        adapter = ProductAdapter {

        }
        binding.rvProducts.adapter = adapter
        viewModel.product()
        observeUpdate()
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModel.getAllProducts().observe(viewLifecycleOwner) { tasks ->
            adapter.items = tasks
        }
    }

    private fun observeUpdate() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.mProductState.collectLatest {
                handleStateChange(it)
            }
        }
    }

    private fun handleStateChange(productState: ProductState) {
        when (productState) {
            is ProductState.Init -> Unit
            is ProductState.Error -> {}
            is ProductState.ShowToast -> binding.root.context.showToast(productState.message)
            is ProductState.IsLoading -> {}
            is ProductState.Success -> {}
        }
    }
}