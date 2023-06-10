package com.adiluhung.jamuin.ui.screen.seller.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.ui.components.seller.ProfileCard
import com.adiluhung.jamuin.ui.components.seller.SellerBottomNavbar
import com.adiluhung.jamuin.ui.theme.JamuInTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellerProfileScreen(modifier: Modifier = Modifier, navController: NavController) {
    Scaffold(modifier = modifier,
        bottomBar = {
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
                text = "Profil",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary
                )
            )

            ProfileCard(
                image = "https://picsum.photos/200/300",
                name = "John Doe",
                username = "johndoe",
                email = "johndoe@gmail.com",
                phoneNumber = "08123456789",
                navController = navController
            )

            OutlinedButton(modifier = Modifier.padding(top = 16.dp), onClick = { /*TODO*/ }) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Logout",
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Preview
@Composable
fun SellerOrderScreenPreview() {
    JamuInTheme {

        val navController = rememberNavController()
        SellerProfileScreen(navController = navController)
    }
}