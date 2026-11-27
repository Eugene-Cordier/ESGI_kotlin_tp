package com.example.product_explorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.product_explorer.ui.theme.Product_ExplorerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Product_ExplorerTheme() {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProductCatalogContainer(
                        onProductClick = {

                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailScreenPreview() {
    Product_ExplorerTheme() {
        ProductDetailScreen(
            product = sampleProduct(),
            onAddToCartClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailScreenOutOfStockPreview() {
    Product_ExplorerTheme() {
        ProductDetailScreen(
            product = sampleProductOutOfStock(),
            onAddToCartClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCatalogScreenPreview() {
    Product_ExplorerTheme() {
        ProductCatalogContainer(
            onProductClick = {}
        )
    }
}

@Preview(
    showBackground = true,
    name = "Catalogue - Dark Theme"
)
@Composable
fun ProductCatalogScreenDarkPreview() {
    Product_ExplorerTheme(darkTheme = true) {
        ProductCatalogContainer(
            onProductClick = {}
        )
    }
}

@Preview(
    showBackground = true,
    name = "Catalogue - Light Theme"
)
@Composable
fun ProductCatalogScreenLightPreview() {
    Product_ExplorerTheme(darkTheme = false) {
        ProductCatalogContainer(
            onProductClick = {}
        )
    }
}