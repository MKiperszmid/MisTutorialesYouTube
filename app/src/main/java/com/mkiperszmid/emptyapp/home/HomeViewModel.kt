package com.mkiperszmid.emptyapp.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.UUID

class HomeViewModel(
    private val dao: ProductDao
) : ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    init {
        viewModelScope.launch {
            dao.getAllProducts().collectLatest {
                state = state.copy(
                    products = it
                )
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
            dao.deleteProduct(product)
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
            Product(
                state.productId ?: UUID.randomUUID().toString(),
                state.productName,
                state.productPrice.toDouble()
            )
        viewModelScope.launch {
            dao.insertProduct(product)
        }
        state = state.copy(
            productName = "",
            productPrice = "",
            productId = null
        )
    }
}
