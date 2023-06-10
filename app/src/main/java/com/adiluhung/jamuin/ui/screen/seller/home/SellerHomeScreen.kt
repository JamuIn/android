package com.adiluhung.jamuin.ui.screen.seller.home

import android.util.Log
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
import com.adiluhung.jamuin.ui.components.Banner
import com.adiluhung.jamuin.ui.components.GreetingBar
import com.adiluhung.jamuin.ui.components.JamuDummy
import com.adiluhung.jamuin.ui.components.RecipeArticle
import com.adiluhung.jamuin.ui.components.RecipeArticleCategory
import com.adiluhung.jamuin.ui.components.seller.SellerBottomNavbar
import com.adiluhung.jamuin.ui.theme.JamuInTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellerHomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    Scaffold(bottomBar = {
        SellerBottomNavbar(navController = navController)
    }) { innerPadding ->
        Log.d("SellerHomeScreen", "innerPadding: $innerPadding")
        Column(modifier = Modifier.padding(16.dp)) {
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
                    RecipeArticle(
                        modifier = Modifier.padding(bottom = 8.dp),
                        item = JamuDummy(
                            id = "1",
                            banner = "",
                            title = "Jamu Kencur"
                        ),
                        navController = navController
                    )
                }
                item {
                    RecipeArticle(
                        modifier = Modifier.padding(bottom = 8.dp),
                        item = JamuDummy(
                            id = "1",
                            banner = "",
                            title = "Jamu Kencur"
                        ),
                        navController = navController
                    )
                }
                item {
                    RecipeArticle(
                        modifier = Modifier.padding(bottom = 8.dp),
                        item = JamuDummy(
                            id = "1",
                            banner = "",
                            title = "Jamu Kencur"
                        ),
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