package com.adiluhung.jamuin.ui.screen

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
import com.adiluhung.jamuin.ui.components.*
import com.adiluhung.jamuin.ui.theme.JamuInTheme
import com.adiluhung.jamuin.ui.theme.NewWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanResultScreen(
    navController: NavController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarCheckout(
                title = stringResource(id = R.string.scan_result)
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
                        RecipeArticleCategory()
                    }
                    item {
                        RecipeArticle(
                            id = "1",
                            banner = "https://example.com/banner.jpg",
                            title = "Jamu Kencur",
                            navController = navController
                        )
                    }
                    item {
                        TopProductCategory()
                    }
                    items(1) { index ->
                        ProductCardWithRating(
                            image = "https://cataas.com/cat",
                            title = "Jamu Beras Kencur",
                            description = "Baik untuk ginjal. Murah loh!",
                            mainIngredient = listOf("Jahe", "Temulawak"),
                            price = 30000,
                            rating = 4.5,
                            ratingNum = 25,
                            openDetailProduct = {}
                        )
                    }
                }
            }
        }
    )
}




@Preview
@Composable
fun ScanResultScreenPreview() {
    JamuInTheme {
        // Create a NavController instance (for preview purposes)
        val navController = rememberNavController()

        // Call your CartScreen composable with the required parameters
        ScanResultScreen(navController = navController)
    }
}
