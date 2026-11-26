package com.example.product_explorer

import androidx.compose.material3.Button
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column

@Composable
fun ProductDetailScreen(
    product: ProductUi,
    onAddToCartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = product.title)
        Text(text = product.brand)
        Text(text = product.category)
        Text(text = "${product.price} €")
        Text(text = product.description)
        Button(onClick = onAddToCartClick) {
            Text(text = "Ajouter au panier")
        }
    }
}