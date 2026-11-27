package com.example.product_explorer

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProductCatalogContainer(
    onProductClick: (ProductUi) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProductCatalogViewModel = viewModel()
) {
    ProductCatalogScreen(
        products = viewModel.filteredProducts,
        onProductClick = onProductClick,
        categories = viewModel.categories,
        searchQuery = viewModel.searchQuery,
        onSearchQueryChange = viewModel::updateSearchQuery,
        showOnlyInStock = viewModel.showOnlyInStock,
        onToggleStockFilter = viewModel::toggleStockFilter,
        favoriteProductIds = viewModel.favoriteProductIds,
        onFavoriteClick = viewModel::toggleFavorite,
        modifier = modifier
    )
}