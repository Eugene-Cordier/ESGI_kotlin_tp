package com.example.product_explorer

import androidx.compose.foundation.Image
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.res.painterResource

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
        ProductHeader(product.title, product.brand, product.category)
        ProductPriceCard(
            price = product.price,
            discountPercentage = product.discountPercentage,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        Text(text = product.description)
        Button(onClick = onAddToCartClick) {
            Text(text = "Ajouter au panier")
        }
    }
}

@Composable
fun ProductHeader(
    title: String,
    brand: String,
    category: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = brand,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = category,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun ProductPriceCard(
    price: Double,
    discountPercentage: Double,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "$price €",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Remise : $discountPercentage %",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun ProductImage(
    productTitle: String,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = android.R.drawable.ic_menu_gallery),
        contentDescription = "Image du produit $productTitle",
        modifier = modifier
    )
}

