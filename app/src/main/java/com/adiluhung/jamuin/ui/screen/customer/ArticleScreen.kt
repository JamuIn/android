package com.adiluhung.jamuin.ui.screen.customer

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
import com.adiluhung.jamuin.ui.components.customer.ArticleBanner
import com.adiluhung.jamuin.ui.components.customer.ArticleCard
import com.adiluhung.jamuin.ui.theme.JamuInTheme
import com.adiluhung.jamuin.ui.theme.NewWhite

@Composable
fun ArticleScreen(navController: NavController, image: String) {
    Column(
        modifier = Modifier
            .background(color = NewWhite)
            .padding(horizontal = 16.dp)
    ) {
        ArticleBanner(navController = navController, image = image)
        Spacer(modifier = Modifier.height(16.dp))
        ArticleCard(
            title = "Jamu Beras Kencur",
            description = "Deskripsi Jamu Beras Kencur",
            mainIngredient = listOf("Jahe", "Temulawak"),
            ingredientItem = "1. Bahan 1 \n2. Bahan 2",
            steps = "1. Langkah1 \n2. Langkah 2"
        )
        Spacer(modifier = Modifier.height(2.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleScreenPreview() {
    JamuInTheme() {
        ArticleScreen(navController = rememberNavController(), image = "sample_image_url")
    }
}