package com.example.product_explorer

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ProductCatalogUiState(
    val products: List<ProductUi> = emptyList(),
    val categories: List<String> = emptyList(),
    val searchQuery: String = "",
    val showOnlyInStock: Boolean = false,
    val favoriteProductIds: List<Int> = emptyList()
)

class ProductCatalogViewModel : ViewModel() {
    private val allProducts: List<ProductUi> = sampleProducts()
    private val allCategories: List<String> = sampleCategories()

    private val _uiState = MutableStateFlow(
        ProductCatalogUiState(
            products = allProducts,
            categories = allCategories
        )
    )
    val uiState: StateFlow<ProductCatalogUiState> = _uiState.asStateFlow()

    private fun filterProducts(searchQuery: String, showOnlyInStock: Boolean): List<ProductUi> {
        return allProducts.filter { product ->
            val matchesSearch =
                product.title.contains(searchQuery, ignoreCase = true) ||
                        product.brand.contains(searchQuery, ignoreCase = true) ||
                        product.category.contains(searchQuery, ignoreCase = true)
            val matchesStock =
                !showOnlyInStock || product.stock > 0
            matchesSearch && matchesStock
        }
    }

    fun onSearchQueryChange(newValue: String) {
        _uiState.update { currentState ->
            currentState.copy(
                searchQuery = newValue,
                products = filterProducts(
                    searchQuery = newValue,
                    showOnlyInStock = currentState.showOnlyInStock
                )
            )
        }
    }

    fun onToggleStockFilter() {
        _uiState.update { currentState ->
            val newShowOnlyInStock = !currentState.showOnlyInStock

            currentState.copy(
                showOnlyInStock = newShowOnlyInStock,
                products = filterProducts(
                    searchQuery = currentState.searchQuery,
                    showOnlyInStock = newShowOnlyInStock
                )
            )
        }
    }

    fun onFavoriteClick(productId: Int) {
        _uiState.update { currentState ->
            val newFavoriteIds =
                if (currentState.favoriteProductIds.contains(productId)) {
                    currentState.favoriteProductIds - productId
                } else {
                    currentState.favoriteProductIds + productId
                }

            currentState.copy(
                favoriteProductIds = newFavoriteIds
            )
        }
    }
}