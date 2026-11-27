package com.example.product_explorer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ProductCatalogViewModel : ViewModel() {

    private val products = sampleProducts()
    val categories = sampleCategories()
    var searchQuery by mutableStateOf("")
        private set
    var showOnlyInStock by mutableStateOf(false)
        private set
    var favoriteProductIds by mutableStateOf(listOf<Int>())
        private set
    val filteredProducts: List<ProductUi>
        get() = products.filter { product ->

            val matchesSearch =
                product.title.contains(searchQuery, ignoreCase = true) ||
                        product.brand.contains(searchQuery, ignoreCase = true) ||
                        product.category.contains(searchQuery, ignoreCase = true)

            val matchesStock =
                !showOnlyInStock || product.stock > 0

            matchesSearch && matchesStock
        }

    fun updateSearchQuery(query: String) {
        searchQuery = query
    }
    fun toggleStockFilter() {
        showOnlyInStock = !showOnlyInStock
    }
    fun toggleFavorite(productId: Int) {
        favoriteProductIds =
            if (favoriteProductIds.contains(productId)) {
                favoriteProductIds - productId
            } else {
                favoriteProductIds + productId
            }
    }
}
