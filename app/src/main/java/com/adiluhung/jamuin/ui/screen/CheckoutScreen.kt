package com.adiluhung.jamuin.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.route.Routes
import com.adiluhung.jamuin.ui.components.*
import com.adiluhung.jamuin.ui.theme.JamuInTheme
import com.adiluhung.jamuin.ui.theme.NewWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(navController: NavController) {
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
                Column(modifier = Modifier.fillMaxSize()) {
                    LazyColumn(
                    ) {
                        items(1) { index ->
                            ProductCardWithEntity(
                                modifier = Modifier.fillMaxWidth(),
                                image = "your_image_url",
                                title = "Item $index",
                                description = "Item description",
                                mainIngredient = listOf("Ingredient 1", "Ingredient 2"),
                                price = 100,
                                entity = 1,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    BuyerAddress()
                    Address(navController = rememberNavController())
                    Spacer(modifier = Modifier.height(10.dp))
                    PriceDetail()
                    BottomCart()
                    Spacer(modifier = Modifier.height(10.dp))
                    AccordionPreview()

                }
                PrimaryButton(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    text = stringResource(id = R.string.payment),
                    onClick = {
                        navController.navigate(Routes.Checkout.routes) {
                            popUpTo(Routes.Home.routes) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
    )
}


@Composable
fun BuyerAddress() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Alamat",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
    }
}


@Preview
@Composable
fun CheckoutScreenPreview() {
    JamuInTheme {
        val navController = rememberNavController()
        CheckoutScreen(navController = navController)
    }
}
