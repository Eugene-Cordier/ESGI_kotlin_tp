package com.example.product_explorer

import androidx.compose.material3.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment

@Composable
fun ProductDetailScreen(
    product: ProductUi,
    onAddToCartClick: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ProductImage(
            productTitle = product.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        ProductHeader(
            title = product.title,
            brand = product.brand,
            category = product.category
        )

        ProductPriceCard(
            price = product.price,
            discountPercentage = product.discountPercentage,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        ProductRating(
            rating = product.rating,
            modifier = Modifier.padding(top = 16.dp)
        )

        ProductAvailabilityCard(
            stock = product.stock,
            shippingInformation = product.shippingInformation,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        ProductDescription(
            description = product.description,
            modifier = Modifier.padding(top = 16.dp)
        )

        ProductWarrantyField(
            warrantyInformation = product.warrantyInformation,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        AddToCartButton(
            onClick = onAddToCartClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
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
        painter = painterResource(
            id = android.R.drawable.ic_menu_gallery
        ),
        contentDescription = "Image du produit $productTitle",
        modifier = modifier
    )
}
@Composable
fun ProductRating(
    rating: Double,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Note du produit"
        )

        Text(
            text = "$rating / 5",
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun ProductAvailabilityCard(
    stock: Int,
    shippingInformation: String,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Disponibilité",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Stock : $stock unités",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = shippingInformation,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun ProductDescription(
    description: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Description",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun ProductWarrantyField(
    warrantyInformation: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = warrantyInformation,
        onValueChange = {},
        readOnly = true,
        label = {
            Text(text = "Garantie")
        },
        modifier = modifier
    )
}

@Composable
fun AddToCartButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text = "Ajouter au panier")
    }
}