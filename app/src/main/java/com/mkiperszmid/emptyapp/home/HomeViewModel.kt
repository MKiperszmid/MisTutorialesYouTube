package com.mkiperszmid.emptyapp.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel(
    private val productService: ProductService
) : ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            try {
                val products = productService.getProducts()
                state = state.copy(
                    products = products
                )
            } catch (e: Exception) {
                //
            }
        }
    }

    fun changeName(name: String) {
        state = state.copy(
            productName = name
        )
    }

    fun changePrice(price: String) {
        state = state.copy(
            productPrice = price
        )
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch {
            try {
                productService.deleteProduct(product.id)
            } catch (e: Exception) {
                println()
            }
            getProducts()
        }
    }

    fun editProduct(product: Product) {
        state = state.copy(
            productName = product.name,
            productPrice = product.price.toString(),
            productId = product.id
        )
    }

    fun createProduct() {
        val product =
            ProductDto(
                state.productName,
                state.productPrice.toDouble()
            )
        viewModelScope.launch {
            try {
                if (state.productId == null) {
                    productService.insertProduct(product)
                } else {
                    productService.updateProduct(product, state.productId!!)
                }
            } catch (e: Exception) {
                println()
            }
            getProducts()
        }
        state = state.copy(
            productName = "",
            productPrice = "",
            productId = null
        )
    }
}
