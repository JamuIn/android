package com.adiluhung.jamuin.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.adiluhung.jamuin.helper.toRupiah
import com.adiluhung.jamuin.ui.theme.JamuInTheme


@Composable
fun ProductCardBig(
    modifier: Modifier = Modifier,
    image: String,
    title: String,
    description: String,
    onClick: () -> Unit = {},
    mainIngredient: List<String>,
    price: Int,
    actionLayout: @Composable () -> Unit = {},
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(2.dp),
    ) {
        Row(
            modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .weight(1f),
                model = image,
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(horizontal = 8.dp)
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
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.LightGray,
                    maxLines = 2,
                )
                Text(
                    text = price.toRupiah(),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                actionLayout()
            }
        }
    }

}

@Composable
fun ProductCardWithRating(
    modifier: Modifier = Modifier,
    image: String,
    title: String,
    description: String,
    mainIngredient: List<String>,
    price: Int,
    rating: Double,
    ratingNum: Int,
    openDetailProduct: () -> Unit
) {
    ProductCardBig(
        modifier = modifier,
        image = image,
        title = title,
        description = description,
        mainIngredient = mainIngredient,
        onClick = { openDetailProduct() },
        price = price
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Favorite",
            tint = Color(0xFFFEC40B)
        )
        Text(text = rating.toString(), style = MaterialTheme.typography.labelSmall)
        Text(text = "($ratingNum)", style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
fun ProductCardWithEntity(
    modifier: Modifier = Modifier,
    image: String,
    title: String,
    description: String,
    mainIngredient: List<String>,
    price: Int,
    entity: Int
) {
    ProductCardBig(
        modifier = modifier,
        image = image,
        title = title,
        description = description,
        mainIngredient = mainIngredient,
        price = price
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = "$entity x",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun ProductCardEditable(
    modifier: Modifier = Modifier,
    image: String,
    title: String,
    description: String,
    mainIngredient: List<String>,
    price: Int,
    onClickDeleteButton: () -> Unit,
    onClickEditButton: () -> Unit
) {
    ProductCardBig(
        modifier = modifier,
        image = image,
        title = title,
        description = description,
        mainIngredient = mainIngredient,
        price = price
    ) {
        IconButton(
            onClick = { onClickDeleteButton() },
            modifier = Modifier.clip(CircleShape),
            colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color(0xFFFFE6E5))
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = MaterialTheme.colorScheme.error
            )
        }
        IconButton(
            onClick = { onClickEditButton() },
            modifier = Modifier.clip(CircleShape),
            colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun ProductCardAtCart(
    modifier: Modifier = Modifier,
    image: String,
    title: String,
    description: String,
    mainIngredient: List<String>,
    price: Int,
    entity: Int,
    onClickDeleteButton: () -> Unit,
) {
    ProductCardBig(
        modifier = modifier,
        image = image,
        title = title,
        description = description,
        mainIngredient = mainIngredient,
        price = price
    ) {

        Text(
            textAlign = TextAlign.Center,
            text = "$entity x",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.width(4.dp))

        IconButton(
            onClick = { onClickDeleteButton() },
            modifier = Modifier.clip(CircleShape),
            colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color(0xFFFFE6E5))
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun ProductCardWithAddReview(
    modifier: Modifier = Modifier,
    image: String,
    title: String,
    description: String,
    mainIngredient: List<String>,
    price: Int,
    onClickButtonReview: () -> Unit,
) {
    ProductCardBig(
        modifier = modifier,
        image = image,
        title = title,
        description = description,
        mainIngredient = mainIngredient,
        price = price
    ) {
        Button(onClick = { onClickButtonReview() }) {
            Text(text = "Review")
        }
    }
}

@Preview
@Composable
fun ProductCardWithRatingPreview() {
    JamuInTheme {
        ProductCardWithRating(
            image = "https://cataas.com/cat",
            title = "Jamu Beras Kencur",
            description = "Baik untuk ginjal. Murah loh!",
            mainIngredient = listOf("Jahe", "Temulawak"),
            price = 30000,
            rating = 4.5,
            ratingNum = 25,
            openDetailProduct = {})
    }
}

@Preview
@Composable
fun ProductCardWithEntityPreview() {
    JamuInTheme {
        ProductCardWithEntity(
            image = "https://cataas.com/cat",
            title = "Jamu Beras Kencur",
            description = "Baik untuk ginjal. Murah loh!",
            mainIngredient = listOf("Jahe", "Temulawak"),
            price = 30000,
            entity = 2,
        )
    }
}

@Preview
@Composable
fun ProductCardEditablePreview() {
    JamuInTheme {
        ProductCardEditable(image = "https://cataas.com/cat",
            title = "Jamu Beras Kencur",
            description = "Baik untuk ginjal. Murah loh!",
            mainIngredient = listOf("Jahe", "Temulawak"),
            price = 30000,
            onClickDeleteButton = {},
            onClickEditButton = {})
    }
}

@Preview
@Composable
fun ProductCardAtCartPreview() {
    JamuInTheme {
        ProductCardAtCart(
            image = "https://cataas.com/cat",
            title = "Jamu Beras Kencur",
            description = "Baik untuk ginjal. Murah loh!",
            mainIngredient = listOf("Jahe", "Temulawak"),
            price = 30000,
            entity = 2,
            onClickDeleteButton = {},
        )
    }
}

@Preview
@Composable
fun ProductCardWithAddReviewPreview() {
    JamuInTheme {
        ProductCardWithAddReview(image = "https://cataas.com/cat",
            title = "Jamu Beras Kencur",
            description = "Baik untuk ginjal. Murah loh!",
            mainIngredient = listOf("Jahe", "Temulawak"),
            price = 30000,
            onClickButtonReview = {})
    }
}