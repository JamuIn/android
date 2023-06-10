package com.adiluhung.jamuin.ui.components.customer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.adiluhung.jamuin.helper.toRupiah
import com.adiluhung.jamuin.route.Routes
import com.adiluhung.jamuin.ui.theme.JamuInTheme
import com.adiluhung.jamuin.ui.theme.RedFree
import com.adiluhung.jamuin.ui.theme.SoftGray

@Composable
fun TopProductCategory() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Produk Unggulan",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Lainnya", style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                )
                Icon(
                    modifier = Modifier.size(12.dp),
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = "Lainnya"
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
    }
}

@Composable
fun RecipeArticleCategory() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Resep Untuk Anda",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Lainnya", style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                )
                Icon(
                    modifier = Modifier.size(12.dp),
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = "Lainnya"
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
    }
}

@Composable
fun ArticleIngredientCategory() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Bahan-bahan",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable
fun ArticleStepsCategory() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Cara Membuat",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}


@Composable
fun Price(isFree: Boolean = false, price: String = "0") {
    if (isFree) {
        Text(
            text = "Free",
            style = MaterialTheme.typography.bodySmall.copy(
                color = RedFree,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
            )
        )
    } else {
        Text(
            text = "Rp. $price",
            style = MaterialTheme.typography.bodySmall.copy(
                color = Green,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )
        )
    }
}

// data class JamuDummy(
//    val id: String,
//    val banner: String,
//    val title: String,
//)
//
// data class SellerName(
//    val Name: String
//)


@Preview
@Composable
fun TopProductCategoryPreview() {
    JamuInTheme {
        TopProductCategory()
    }
}

@Preview
@Composable
fun RecipeArticleCategoryPreview() {
    JamuInTheme {
        RecipeArticleCategory()
    }
}

@Preview
@Composable
fun ArticleIngredientCategoryPreview() {
    MaterialTheme {
        ArticleIngredientCategory()
    }
}

@Preview
@Composable
fun ArticleStepsCategoryPreview() {
    MaterialTheme {
        ArticleStepsCategory()
    }
}

@Preview
@Composable
fun PricePreview() {
    MaterialTheme {
        Price(isFree = true)
        // Price(isFree = false, "128.000"
    }
}

