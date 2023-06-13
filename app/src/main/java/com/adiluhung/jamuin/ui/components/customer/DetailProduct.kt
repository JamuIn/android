package com.adiluhung.jamuin.ui.components.customer

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.helper.toRupiah
import com.adiluhung.jamuin.ui.theme.Dark
import com.adiluhung.jamuin.ui.theme.DodgerBlue
import com.adiluhung.jamuin.ui.theme.JamuInTheme
import com.adiluhung.jamuin.ui.theme.NewGreen

@Composable
fun DetailBanner(navController: NavController, image: String?) {
    Column {
        Box(modifier = Modifier.height(300.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(image).crossfade(true)
                    .build(),
                contentDescription = stringResource(id = R.string.image_profile),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp))
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
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = White, contentColor = Dark
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
fun AddRemoveButton(
    totalOrder: Int,
    modifier: Modifier = Modifier,
    onAddTotalOrder: () -> Unit,
    onRemoveTotalOrder: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        OutlinedIconButton(border = BorderStroke(1.dp, DodgerBlue), onClick = {
            onRemoveTotalOrder()
        }) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = Icons.Default.Remove,
                contentDescription = "Remove Icon",
                tint = DodgerBlue
            )
        }

        Text(
            text = totalOrder.toString(), style = TextStyle(
                fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Black
            ), modifier = Modifier.padding(horizontal = 16.dp)
        )

        OutlinedIconButton(border = BorderStroke(1.dp, DodgerBlue), onClick = {
            onAddTotalOrder()
        }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Icon",
                tint = DodgerBlue,
            )
        }
    }
}

//@Composable
// fun AddRemoveButton(
//    totalOrder: MutableState<Int>,
//    modifier: Modifier = Modifier,
//    updateOrder: Unit
//) {
//    Row(
//        horizontalArrangement = Arrangement.SpaceAround,
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = modifier
//    ) {
//        IconButton(
//            onClick = {
//                totalOrder -= 1
//                //Update order directly
//                updateOrder
//            }
//        ) {
//            Card(
//                modifier = Modifier
//                    .size(35.dp),
//                shape = CircleShape,
//            ) {
//                Box(
//                    modifier = Modifier.size(15.dp)
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Delete,
//                        contentDescription = "Remove Icon",
//                        tint = DodgerBlue,
//                        modifier = Modifier.align(Alignment.Center)
//                    )
//                }
//            }
//        }
//
//        Text(
//            text = totalOrder.toString(),
//            style = TextStyle(
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black
//            ),
//            modifier = Modifier.padding(horizontal = 16.dp)
//        )
//
//        IconButton(
//            onClick = {
//                totalOrder += 1
//                //Update order directly
//                updateOrder
//            }
//        ) {
//            Card(
//                modifier = Modifier
//                    .size(35.dp),
//                shape = CircleShape,
//            ) {
//                Box(
//                    modifier = Modifier.size(15.dp)
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Add,
//                        contentDescription = "Add Icon",
//                        tint = DodgerBlue,
//                        modifier = Modifier.align(Alignment.Center)
//                    )
//                }
//            }
//        }
//    }
//}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun AddRemoveButtonPreview() {
    AddRemoveButton(totalOrder = 0,
        onAddTotalOrder = {},
        onRemoveTotalOrder = {})
}


@Composable
fun PrimaryOutlineButtonRow(
    totalOrder: Int,
    onAddTotalOrder: () -> Unit,
    onRemoveTotalOrder: () -> Unit,
    onAddToCart: () -> Unit,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(5.dp))
        AddRemoveButton(
            totalOrder = totalOrder,
            modifier = Modifier.weight(1f),
            onAddTotalOrder = onAddTotalOrder,
            onRemoveTotalOrder = onRemoveTotalOrder
        )
        Spacer(modifier = Modifier.width(5.dp))
        PrimaryOutlineButton(modifier = Modifier.weight(1f),
            label = stringResource(id = R.string.cart),
            onClick = {
                onAddToCart()
            }
        )
    }
}


class ImageProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String> = sequenceOf(
        "https://example.com/image1.jpg"
    )
}

@Composable
fun DetailCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    mainIngredient: List<String>,
    price: Int,
    totalOrder: Int,
    onAddTotalOrder: () -> Unit,
    onRemoveTotalOrder: () -> Unit,
    onAddToCart: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth(),
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
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = price.toRupiah(),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = NewGreen
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = Black,
                maxLines = 2,
            )
            Spacer(modifier = Modifier.height(6.dp))
            PrimaryOutlineButtonRow(
                totalOrder = totalOrder,
                onAddTotalOrder = onAddTotalOrder,
                onRemoveTotalOrder = onRemoveTotalOrder,
                onAddToCart = onAddToCart
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
    price: Int,
    totalOrder: Int,
    onAddTotalOrder: () -> Unit = {},
    onRemoveTotalOrder: () -> Unit = {},
    onAddToCart: () -> Unit = {},
) {
    DetailCard(
        modifier = modifier,
        title = title,
        description = description,
        mainIngredient = mainIngredient,
        price = price,
        totalOrder = totalOrder,
        onAddTotalOrder = onAddTotalOrder,
        onRemoveTotalOrder = onRemoveTotalOrder,
        onAddToCart = onAddToCart
    )
}


@Preview(showBackground = true)
@Composable
fun DetailBannerPreview(
    @PreviewParameter(ImageProvider::class) image: String = "https://example.com/image.jpg"
) {
    JamuInTheme {
        val navController = rememberNavController()
        DetailBanner(navController = navController, image)
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailCardPreview() {
    JamuInTheme {
        ProductDetailCard(
            title = "Jamu Beras Kencur",
            description = "Baik untuk ginjal. Murah loh!",
            mainIngredient = listOf("Jahe", "Temulawak"),
            price = 30000,
            totalOrder = 0,
            onAddTotalOrder = {},
            onRemoveTotalOrder = {}
        )
    }
}