package com.adiluhung.jamuin.ui.screen.customer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.ui.components.customer.*
import com.adiluhung.jamuin.ui.theme.JamuInTheme
import com.adiluhung.jamuin.ui.theme.NewWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    navController: NavController
) {
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
                        PaymentMethodCard()
                        Spacer(modifier = Modifier.height(16.dp)
                        )
                    }
                    item {
                        MustPay()
                    }
                    item {
                        PaymentPriceCard(price = 10000)
                    }
                    item {
                        PaymentProve()
                        ImagePayment()
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun PaymentScreenPreview() {
    JamuInTheme {
        val navController = rememberNavController()
        PaymentScreen(navController = navController)
    }
}