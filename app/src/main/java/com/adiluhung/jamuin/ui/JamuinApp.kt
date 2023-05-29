package com.adiluhung.jamuin.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adiluhung.jamuin.ui.components.ProductCardWithRating

private val sampleName = listOf(
    "Andre",
)

@Composable
fun JamuinApp(modifier: Modifier = Modifier) {
    LazyColumn {
        items(sampleName) { name ->
            ProductCardWithRating(
                image = "https://cataas.com/cat",
                title = "Jamu Beras Kencur",
                description = "Ini baik untuk ginjal. Coba kasih tau siapa lagi yang hebat",
                mainIngredient = listOf("Jahe", "Temulawak"),
                price = 30000,
                rating = 4.5,
                ratingNum = 25
            )
        }
    }
}