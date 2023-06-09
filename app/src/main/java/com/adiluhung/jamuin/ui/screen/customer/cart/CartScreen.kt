package com.adiluhung.jamuin.ui.screen.customer.cart

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.helper.titlecaseFirstChar
import com.adiluhung.jamuin.route.Routes
import com.adiluhung.jamuin.ui.components.customer.BottomNavigationBar
import com.adiluhung.jamuin.ui.components.customer.PrimaryButton
import com.adiluhung.jamuin.ui.components.customer.ProductCardAtCart
import com.adiluhung.jamuin.ui.screen.ViewModelFactory
import com.adiluhung.jamuin.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navController: NavController,
    viewModel: CartViewModel = viewModel(
        factory = ViewModelFactory(LocalContext.current)
    )
) {
    val context = LocalContext.current
    val listItemCart = viewModel.listCartItem.observeAsState().value ?: emptyList()

    var isInitialized by remember { mutableStateOf(false) }

    if (!isInitialized) {
        viewModel.getItemInUserCart()
        isInitialized = true
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    bottom = innerPadding.calculateBottomPadding(),
                    start = 16.dp,
                    end = 16.dp
                )
        ) {
            Text(
                text = "Keranjang",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = DodgerBlue
                )
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if (listItemCart.isEmpty()) {
                    item {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            text = "Keranjang kosong",
                            textAlign = TextAlign.Center,
                            color = Color.LightGray
                        )
                    }
                } else {
                    items(listItemCart.size) { index ->
                        ProductCardAtCart(
                            modifier = Modifier.fillMaxWidth(),
                            image = listItemCart[index].productImage,
                            title = listItemCart[index].productName,
                            description = listItemCart[index].productDescription,
                            mainIngredient = listItemCart[index].mainIngredient.titlecaseFirstChar(),
                            price = listItemCart[index].price,
                            quantity = listItemCart[index].quantity,
                            onClickDeleteButton = {
                                viewModel.deleteItemInCart(listItemCart[index].id)

                                Toast.makeText(
                                    context,
                                    "Berhasil menghapus item dari keranjang",
                                    Toast.LENGTH_SHORT
                                ).show()

                                isInitialized = false
                            },
                        )
                    }
                    item {
                        PrimaryButton(
                            modifier = Modifier.padding(top = 16.dp),
                            text = stringResource(id = R.string.checkout),
                            onClick = {
                                navController.navigate(Routes.Checkout.route)
                            }
                        )
                    }
                }
            }
        }


    }
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
