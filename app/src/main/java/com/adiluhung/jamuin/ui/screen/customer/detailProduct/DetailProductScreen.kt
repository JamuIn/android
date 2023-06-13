package com.adiluhung.jamuin.ui.screen.customer.detailProduct

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.helper.titlecaseFirstChar
import com.adiluhung.jamuin.route.Routes
import com.adiluhung.jamuin.ui.components.customer.DetailBanner
import com.adiluhung.jamuin.ui.components.customer.ProductDetailCard
import com.adiluhung.jamuin.ui.components.customer.Review
import com.adiluhung.jamuin.ui.components.customer.ReviewerCard
import com.adiluhung.jamuin.ui.components.customer.SellerCard
import com.adiluhung.jamuin.ui.screen.ViewModelFactory
import com.adiluhung.jamuin.ui.theme.JamuInTheme
import com.adiluhung.jamuin.ui.theme.NewWhite

@Composable
fun DetailProductScreen(
    navController: NavController,
    productId: Int,
    viewModel: DetailProductViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(LocalContext.current)
    )
) {
    viewModel.getDetailProduct(productId)

    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val product = viewModel.product.observeAsState().value?.product

    var totalOrder by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .background(color = NewWhite)
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState)
    ) {
        if (product != null) {
            DetailBanner(navController = navController, image = product.image)
            Spacer(modifier = Modifier.height(16.dp))
            ProductDetailCard(
                modifier = Modifier.padding(vertical = 8.dp),
                title = product.name,
                description = product.description,
                mainIngredient = listOf(product.mainIngredient.titlecaseFirstChar()),
                price = product.price,
                totalOrder = totalOrder,
                onAddTotalOrder = {
                    totalOrder += 1
                },
                onRemoveTotalOrder = {
                    if (totalOrder > 0) {
                        totalOrder -= 1
                    }
                },
                onAddToCart = {
                    if (totalOrder > 0) {
                        viewModel.addUserCart(product.id, totalOrder)
                        Toast.makeText(
                            context,
                            "Berhasil menambahkan ke keranjang",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.popBackStack()
                    }
                }
            )
            Spacer(modifier = Modifier.height(2.dp))
            SellerCard(navController = navController, seller = product.seller)
            Spacer(modifier = Modifier.height(8.dp))
            Review(
                review = "Review",
                star = "2"
            )
            Spacer(modifier = Modifier.height(2.dp))
            ReviewerCard()
            Spacer(modifier = Modifier.height(6.dp))
            ReviewerCard()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    JamuInTheme() {
        DetailProductScreen(navController = rememberNavController(), productId = 1)
    }
}
