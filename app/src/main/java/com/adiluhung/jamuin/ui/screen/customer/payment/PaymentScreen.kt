package com.adiluhung.jamuin.ui.screen.customer.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.data.sources.PaymentMethod
import com.adiluhung.jamuin.route.Routes
import com.adiluhung.jamuin.ui.components.customer.ImagePayment
import com.adiluhung.jamuin.ui.components.customer.MustPay
import com.adiluhung.jamuin.ui.components.customer.PaymentMethodCard
import com.adiluhung.jamuin.ui.components.customer.PaymentPriceCard
import com.adiluhung.jamuin.ui.components.customer.PaymentProve
import com.adiluhung.jamuin.ui.components.customer.PrimaryButton
import com.adiluhung.jamuin.ui.components.customer.SendTo
import com.adiluhung.jamuin.ui.components.customer.TopBarCheckout
import com.adiluhung.jamuin.ui.screen.ViewModelFactory
import com.adiluhung.jamuin.ui.theme.JamuInTheme
import com.adiluhung.jamuin.ui.theme.NewWhite
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun PaymentScreen(
    navController: NavController,
    orderId: Int,
    paymentMethodId: Int,
    totalPrice: Int,
    viewModel: PaymentViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(LocalContext.current)
    )
) {
    val paymentMethod =
        PaymentMethod.data[paymentMethodId].title + ", " + PaymentMethod.data[paymentMethodId].accoutNumber
    // Camera permission state
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )

    if (cameraPermissionState.status.isGranted) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopBarCheckout(
                    title = stringResource(id = R.string.Payment_screen)
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
                        item {
                            SendTo()
                        }
                        item {
                            PaymentMethodCard(paymentMethodId)
                            Spacer(
                                modifier = Modifier.height(16.dp)
                            )
                        }
                        item {
                            MustPay()
                        }
                        item {
                            PaymentPriceCard(price = totalPrice)
                        }
                        item {
                            PaymentProve()
                            ImagePayment()
                            PrimaryButton(
                                modifier = Modifier.padding(top = 24.dp),
                                text = "Bayar"
                            ) {
                                viewModel.updateOrder(orderId, totalPrice, "paid")
                                viewModel.checkoutOrder(paymentAddress = paymentMethod)

                                navController.navigate(Routes.Home.route) {
                                    popUpTo(Routes.Home.route) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    }
                }
            }
        )
    } else {
        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val textToShow = if (cameraPermissionState.status.shouldShowRationale) {
                "Kamera sangat penting untuk aplikasi ini. Mohon berikan izin."
            } else {
                "Kamera sangat penting untuk aplikasi ini. Mohon berikan izin."
            }
            Text(textToShow, textAlign = TextAlign.Center)
            Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                Text("Request permission")
            }
        }
    }


}

@Preview
@Composable
fun PaymentScreenPreview() {
    JamuInTheme {
        val navController = rememberNavController()
        PaymentScreen(
            navController = navController, 1,
            1, 1
        )
    }
}