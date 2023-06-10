package com.adiluhung.jamuin.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.adiluhung.jamuin.R
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
            Icon(
                painter = painterResource(id = R.drawable.more_icon),
                contentDescription = "lainnya"
            )
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
                text = "Artikel Resep",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Icon(
                painter = painterResource(id = R.drawable.more_icon),
                contentDescription = "lainnya"
            )
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

@Composable
fun ProdutCard(
    modifier: Modifier = Modifier,
    image: String,
    title: String,
    description: String,
    onClick: () -> Unit = {},
    mainIngredient: String,
    price: Int,
    actionLayout: @Composable () -> Unit = {},
) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = White)
            .border(BorderStroke(1.4.dp, SolidColor(SoftGray)), shape = RoundedCornerShape(16.dp))
            .padding(14.dp)
    ) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(134.dp)
                    .clip(
                        shape = RoundedCornerShape(12.dp)
                    ),
                model = image,
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = price.toRupiah(),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary,
                )
                Star("5")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = description,
                maxLines = 2,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Normal, color = Color.LightGray
                )
            )
            Seller("Toko Faiz 243")
        }
    }
}

//data class JamuDummy(
//    val id: String,
//    val banner: String,
//    val title: String,
//)
//
//data class SellerName(
//    val Name: String
//)

@Composable
fun RecipeArticle(
    modifier: Modifier = Modifier,
    id: String,
    banner: String,
    title: String,
    navController: NavController
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(Routes.DetailArticle.createRoute(id = id.toInt()))
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 0.4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(banner)
                    .crossfade(true)
                    .build(),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column {
                Text(
                    text = title.capitalize(),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}



@Preview
@Composable
fun RecipeArticlePreview() {
    val navController = rememberNavController()
    JamuInTheme {
        RecipeArticle(
            id = "1",
            banner = "https://example.com/banner.jpg",
            title = "Jamu Kencur",
            navController = navController
        )
    }
}

@Preview
@Composable
fun ProductCardPreview() {
    JamuInTheme {
        ProdutCard(
            title = "Jamu beras",
            price = 12000,
            mainIngredient = "Jahe",
            image = "",
            description = "desc"
        )
    }
}

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
        //Price(isFree = false, "128.000"
    }
}

