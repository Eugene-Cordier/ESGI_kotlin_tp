package com.example.product_explorer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProductCatalogScreen(
    products: List<ProductUi>,
    onProductClick: (ProductUi) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn (
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
// Ajoute une marge intérieure autour du contenu de la liste
        verticalArrangement = Arrangement.spacedBy(16.dp)
// Ajoute un espace vertical entre les éléments de la liste.
    ) {
        item {
            Text(
                text = "Catalogue produits",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}