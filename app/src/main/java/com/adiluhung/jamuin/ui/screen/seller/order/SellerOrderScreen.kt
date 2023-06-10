package com.adiluhung.jamuin.ui.screen.seller.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.ui.components.ProductCardEditable
import com.adiluhung.jamuin.ui.components.seller.SellerBottomNavbar
import com.adiluhung.jamuin.ui.theme.JamuInTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellerOrderScreen(modifier: Modifier = Modifier, navController: NavController) {
    Scaffold(bottomBar = {
        SellerBottomNavbar(navController = navController)
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    bottom = innerPadding.calculateBottomPadding(),
                    start = 16.dp,
                    end = 16.dp
                )
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                modifier = Modifier.padding(bottom = 24.dp),
                text = "Pesanan",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )

            Text(
                text = "Menunggu Konfirmasi", style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
            ProductCardEditable(
                modifier = Modifier.padding(bottom = 4.dp),
                image = "",
                title = "jamu Beras Kencur",
                description = "baik untuk ginjal dan hati",
                mainIngredient = listOf("Jamu"),
                price = 30000,
                onClickDeleteButton = { /*TODO*/ }) {

            }
            ProductCardEditable(
                modifier = Modifier.padding(bottom = 4.dp),
                image = "",
                title = "jamu Beras Kencur",
                description = "baik untuk ginjal dan hati",
                mainIngredient = listOf("Jamu"),
                price = 30000,
                onClickDeleteButton = { /*TODO*/ }) {

            }

            Text(
                text = "Menunggu Konfirmasi", style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    }
}

@Preview
@Composable
fun SellerOrderScreenPreview() {
    JamuInTheme {

        val navController = rememberNavController()
        SellerOrderScreen(navController = navController)
    }
}