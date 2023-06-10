package com.adiluhung.jamuin.ui.screen.seller.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.ui.components.customer.Banner
import com.adiluhung.jamuin.ui.components.customer.GreetingBar
import com.adiluhung.jamuin.ui.components.customer.RecipeCard
import com.adiluhung.jamuin.ui.components.customer.RecipeArticleCategory
import com.adiluhung.jamuin.ui.components.seller.SellerBottomNavbar
import com.adiluhung.jamuin.ui.theme.JamuInTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellerHomeScreen(modifier: Modifier = Modifier, navController: NavController) {
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
        ) {
            GreetingBar(
                modifier = Modifier.padding(bottom = 24.dp),
                navController = navController,
                name = "Udin",
                greeting = "Selamat datang kembali!"
            )
            Banner(modifier = Modifier.padding(bottom = 16.dp))
            RecipeArticleCategory()
            LazyColumn() {
                item {
                    RecipeCard(
                        modifier = Modifier.padding(bottom = 8.dp),
                        id = 1,
                        image = "https://example.com/banner.jpg",
                        title = "Jamu Kencur",
                        description = "Jamu kencur adalah jamu yang terbuat dari kencur",
                        navController = navController
                    )
                }
                item {
                    RecipeCard(
                        modifier = Modifier.padding(bottom = 8.dp),
                        id = 1,
                        image = "https://example.com/banner.jpg",
                        title = "Jamu Kencur",
                        description = "Jamu kencur adalah jamu yang terbuat dari kencur",
                        navController = navController
                    )
                }
                item {
                    RecipeCard(
                        modifier = Modifier.padding(bottom = 8.dp),
                        id = 1,
                        image = "https://example.com/banner.jpg",
                        title = "Jamu Kencur",
                        description = "Jamu kencur adalah jamu yang terbuat dari kencur",
                        navController = navController
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun SellerHomeScreenPreview() {
    JamuInTheme {
        val navController = rememberNavController()
        SellerHomeScreen(navController = navController)
    }
}