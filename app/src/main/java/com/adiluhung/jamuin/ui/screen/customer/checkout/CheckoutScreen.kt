package com.adiluhung.jamuin.ui.screen.customer.checkout

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.data.sources.PaymentMethod
import com.adiluhung.jamuin.data.sources.PaymentMethodModel
import com.adiluhung.jamuin.route.Routes
import com.adiluhung.jamuin.ui.components.*
import com.adiluhung.jamuin.ui.components.customer.AccordionPreview
import com.adiluhung.jamuin.ui.components.customer.Address
import com.adiluhung.jamuin.ui.components.customer.BottomCart
import com.adiluhung.jamuin.ui.components.customer.PaymentMethodDropDown
import com.adiluhung.jamuin.ui.components.customer.PriceDetail
import com.adiluhung.jamuin.ui.components.customer.PrimaryButton
import com.adiluhung.jamuin.ui.components.customer.ProductCardWithQuantity
import com.adiluhung.jamuin.ui.components.customer.TopBarCheckout
import com.adiluhung.jamuin.ui.screen.ViewModelFactory
import com.adiluhung.jamuin.ui.theme.Dark
import com.adiluhung.jamuin.ui.theme.JamuInTheme
import com.adiluhung.jamuin.ui.theme.NewWhite

data class InvoiceItem(
    val name: String,
    val price: Int,
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    navController: NavController,
    viewModel: CheckoutViewModel = viewModel(
        factory = ViewModelFactory(
            LocalContext.current
        )
    )
) {
    val context = LocalContext.current
    val listCart = viewModel.listCartItem.observeAsState().value
    val user = viewModel.user.observeAsState().value

    val listInvoiceItem = listCart?.map { cart ->
        InvoiceItem(
            name = cart.productName,
            price = cart.price
        )
    }

    val totalPrice = listCart?.sumOf { cart ->
        cart.price
    }

    var paymentMethod by remember { mutableIntStateOf(0) }

    val orderId = viewModel.orderId.observeAsState().value

    if (orderId != null && totalPrice != null) {
        navController.navigate(
            Routes.Payment.createRoute(
                orderId = orderId,
                paymentMethodId = paymentMethod - 1,
                totalPrice = totalPrice
            )
        )
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Dark
                    )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_back),
                            contentDescription = stringResource(id = R.string.back)
                        )
                        Text(text = stringResource(id = R.string.back))
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                }
            }
        },
        content = { paddingValues: PaddingValues ->
            Box(
                modifier = Modifier
                    .background(color = NewWhite)
                    .fillMaxHeight()
                    .padding(
                        top = paddingValues.calculateTopPadding() - 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = paddingValues.calculateBottomPadding() + 16.dp
                    )
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    LazyColumn(
                        contentPadding = PaddingValues(bottom = 64.dp)
                    ) {
                        item {
                            TopBarCheckout(
                                title = stringResource(id = R.string.checkout)
                            )
                        }

                        if (listCart != null) {
                            items(listCart.size) { index ->
                                ProductCardWithQuantity(
                                    modifier = Modifier.fillMaxWidth(),
                                    image = listCart[index].productImage,
                                    title = listCart[index].productName,
                                    description = listCart[index].productDescription,
                                    mainIngredient = listCart[index].mainIngredient,
                                    price = listCart[index].price,
                                    quantity = listCart[index].quantity,
                                )
                            }
                        }

                        item {
                            Spacer(modifier = Modifier.height(10.dp))
                            BuyerAddress()
                            Address(navController = navController, address = user?.address)
                            Spacer(modifier = Modifier.height(10.dp))
                            PriceDetail()
                            BottomCart(listInvoiceItem = listInvoiceItem, totalPrice = totalPrice)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Metode Pembayaran",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            PaymentMethodDropDown(onChange = {
                                paymentMethod = it
                            })
                        }
                    }
                }
                PrimaryButton(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    text = stringResource(id = R.string.payment),
                    onClick = {
                        if (paymentMethod != 0) {
                            if (totalPrice != null) {
                                viewModel.createOrder(totalPrice, "unpaid")
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "Pilih metode pembayaran terlebih dahulu!",
                                Toast.LENGTH_SHORT
                            ).show()
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
