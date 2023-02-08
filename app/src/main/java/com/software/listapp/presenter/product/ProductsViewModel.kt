package com.software.listapp.presenter.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.software.listapp.data.comman.base.BindingViewModel
import com.software.listapp.data.main.product.OfferResponse
import com.software.listapp.database.ProductDao
import com.software.listapp.domain.base.BaseResultList
import com.software.listapp.domain.main.product.ProductAttributesEntity
import com.software.listapp.domain.main.product.ProductEntity
import com.software.listapp.domain.main.product.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val productDao: ProductDao
): BindingViewModel() {

    private val productState = MutableStateFlow<ProductState>(ProductState.Init)
    val mProductState: StateFlow<ProductState> get() = productState

    fun product(){
        viewModelScope.async {
            productUseCase.product()
                .onStart {
                    productState.value = ProductState.IsLoading(true)
                }
                .catch { exception ->
                    productState.value = ProductState.IsLoading(false)
                    productState.value = ProductState.ShowToast(exception.message.toString())
                }
                .collect{ result ->
                    productState.value = ProductState.IsLoading(false)
                    when(result){
                        is BaseResultList.Success ->{
                            productState.value = ProductState.Success("")
                            result.data.forEach {
                                insertProduct(it)
                            }
                            result.data2.forEach {
                                insertProductAttributes(it)
                            }
                        }
                        is BaseResultList.Error -> {
                            productState.value = ProductState.Error(result.rawResponse)
                        }
                    }
                }
        }
    }

    private fun insertProduct(productEntity: ProductEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                productUseCase.insertProduct(productEntity)
            }
        }
    }

    private fun insertProductAttributes(productAttributesEntity: ProductAttributesEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                productUseCase.insertProductAttributes(productAttributesEntity)
            }
        }
    }

    fun getAllProducts():LiveData<List<ProductEntity>>{
        return productDao.getAllProducts()
    }

    fun getProduct(id: Int): LiveData<ProductEntity>{
        return productDao.getProduct(id)
    }

    fun getProductAttributes(productId: Int): LiveData<List<ProductAttributesEntity>>{
        return productDao.getProductAttributes(productId)
    }
}

sealed class ProductState {
    object Init : ProductState()
    data class IsLoading(val isLoading: Boolean) : ProductState()
    data class ShowToast(val message: String) : ProductState()
    data class Success(val university: String) : ProductState()
    data class Error(val rawResponse: OfferResponse) : ProductState()
}