package com.adiluhung.jamuin.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.ui.components.*
import com.adiluhung.jamuin.ui.theme.JamuInTheme
import com.adiluhung.jamuin.ui.theme.NewWhite

@Composable
fun DetailScreen(navController: NavController, image: String) {
    Column(
        modifier = Modifier
            .background(color = NewWhite)
            .padding(horizontal = 16.dp)
    ) {
        DetailBanner(navController = navController, image = image)
        Spacer(modifier = Modifier.height(16.dp))
        ProductDetailCard(
            title = "Jamu Beras Kencur",
            description = "Baik untuk ginjal. Murah loh!",
            mainIngredient = listOf("Jahe", "Temulawak"),
            price = 30000,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        SellerCard(navController = navController)
        Spacer(modifier = Modifier.height(8.dp))
        Review (
            review = "Review",
            star = "2"
                )
        Spacer(modifier = Modifier.height(2.dp))
        ReviewerCard()
        Spacer(modifier = Modifier.height(6.dp))
        ReviewerCard()
    }
}


@Preview (showBackground = true)
@Composable
fun DetailScreenPreview() {
    JamuInTheme() {
        DetailScreen(navController = rememberNavController(), image = "sample_image_url")
    }
}