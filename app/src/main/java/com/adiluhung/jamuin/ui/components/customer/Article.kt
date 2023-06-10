package com.adiluhung.jamuin.ui.components.customer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.ui.theme.Dark
import com.adiluhung.jamuin.ui.theme.JamuInTheme

@Composable
fun ArticleBanner(navController: NavController, image: String) {
    Column {
        Box(modifier = Modifier.height(300.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(true).build(),
                contentDescription = stringResource(id = R.string.image_profile),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Dark
                    )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_back),
                            contentDescription = stringResource(id = R.string.back)
                        )
                        Text(text = stringResource(id = R.string.back))
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    ingredientItem: String,
    steps: String,
    onClick: () -> Unit = {},
    mainIngredient: List<String>,
    actionLayout: @Composable () -> Unit = {},
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(2.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row {
                mainIngredient.map {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.primary
                        ), modifier = Modifier.padding(end = 4.dp)
                    ) {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(4.dp),
                        )
                    }
                }
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black,
                maxLines = 2,
            )
            Spacer(modifier = Modifier.height(6.dp))
            ArticleIngredientCategory()
            Text(
                text = ingredientItem,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(6.dp))
            ArticleStepsCategory()
            Text(
                text = steps,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black,
            )
        }
    }
}


@Composable
fun ProductDetailCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    mainIngredient: List<String>,
    ingredientItem: String,
    steps: String
) {
    ArticleCard(
        modifier = modifier,
        title = title,
        description = description,
        mainIngredient = mainIngredient,
        ingredientItem = ingredientItem,
        steps = steps
    )
}

@Preview(showBackground = true)
@Composable
fun ArticleBannerPreview(
    @PreviewParameter(ImageProvider::class) image: String = "https://example.com/image.jpg"
) {
    JamuInTheme {
        val navController = rememberNavController()
        ArticleBanner(navController = navController, image)
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleCardPreview() {
    JamuInTheme {
        ArticleCard(
            title = "Jamu Beras Kencur",
            description = "Baik untuk ginjal. Murah loh!",
            mainIngredient = listOf("Jahe", "Temulawak"),
            ingredientItem = "1. Bahan 1 \n2. Bahan 2",
            steps = "1. Langkah1 \n2. Langkah 2"
        )
    }
}