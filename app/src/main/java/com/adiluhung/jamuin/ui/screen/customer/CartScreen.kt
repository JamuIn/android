package com.adiluhung.jamuin.ui.screen.customer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.route.Routes
import com.adiluhung.jamuin.ui.components.customer.BottomNavigationBar
import com.adiluhung.jamuin.ui.components.customer.PrimaryButton
import com.adiluhung.jamuin.ui.components.customer.ProductCardAtCart
import com.adiluhung.jamuin.ui.components.customer.TopBarCheckout
import com.adiluhung.jamuin.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navController: NavController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarCheckout(
                title = stringResource(id = R.string.checkout)
            )
        },
        content = { paddingValues: PaddingValues ->
            Box(
                modifier = Modifier
                    .background(color = NewWhite)
                    .fillMaxHeight()
                    .padding(
                        top = paddingValues.calculateTopPadding() + 5.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = paddingValues.calculateBottomPadding() + 16.dp
                    )
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(1) { index ->
                        ProductCardAtCart(
                            modifier = Modifier.fillMaxWidth(),
                            image = "your_image_url",
                            title = "Item $index",
                            description = "Item description",
                            mainIngredient = listOf("Ingredient 1", "Ingredient 2"),
                            price = 100,
                            entity = 1,
                            onClickDeleteButton = { /* Handle delete button click */ }
                        )
                    }
                }
                PrimaryButton(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    text = stringResource(id = R.string.checkout),
                    onClick = {
                        navController.navigate(Routes.Checkout.routes) {
                            popUpTo(Routes.Home.routes) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    )
}




@Preview
@Composable
fun CartScreenPreview() {
    JamuInTheme {
        // Create a NavController instance (for preview purposes)
        val navController = rememberNavController()

        // Call your CartScreen composable with the required parameters
        CartScreen(navController = navController)
    }
}
