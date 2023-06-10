package com.adiluhung.jamuin.ui.components.customer

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun DetailBanner(navController: NavController, image: String) {
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
                        containerColor = White,
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
fun AddRemoveButton(
    totalOrder: MutableState<Int>,
    modifier: Modifier = Modifier,
    isInCart: Boolean = false
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        IconButton(
            onClick = {
                if (isInCart) {
                    if (totalOrder.value > 0) {
                        Log.e("Clicked", "Dihapus dari keranjang")
                        totalOrder.value -= 1
                    }
                } else {
                    if (totalOrder.value > 1) {
                        totalOrder.value -= 1
                    }
                }
            }
        ) {
            Card(
                modifier = Modifier
                    .size(35.dp),
                shape = CircleShape,
            ) {
                Box(
                    modifier = Modifier.size(15.dp)
                ) {
                    Icon(
                        modifier = Modifier.align(Alignment.Center),
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove Icon",
                        tint = DodgerBlue
                    )
                }
            }
        }

        Text(
            text = totalOrder.value.toString(),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        IconButton(
            onClick = {
                totalOrder.value += 1
            }
        ) {
            Card(
                modifier = Modifier
                    .size(35.dp),
                shape = CircleShape,
            ) {
                Box(
                    modifier = Modifier.size(15.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Icon",
                        tint = DodgerBlue,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

//@Composable
//fun AddRemoveButton(
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
//                totalOrder.value -= 1
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
//            text = totalOrder.value.toString(),
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
//                totalOrder.value += 1
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
    val totalOrder = mutableStateOf(0)
    AddRemoveButton(totalOrder = totalOrder)
}


@Composable
fun PrimaryOutlineButtonRow() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(5.dp))
        AddRemoveButton(
            totalOrder = remember { mutableStateOf(0) },
            modifier = Modifier.weight(1f),
            isInCart = true
        )
        Spacer(modifier = Modifier.width(5.dp))
        PrimaryOutlineButton(
            modifier = Modifier.weight(1f),
            label = stringResource(id = R.string.cart),
            onClick = {}
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
    onClick: () -> Unit = {},
    mainIngredient: List<String>,
    price: Int,
    actionLayout: @Composable () -> Unit = {},
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = White
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
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                actionLayout()
            }
            Spacer(modifier = Modifier.height(6.dp))
            PrimaryOutlineButtonRow()
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
) {
    DetailCard(
        modifier = modifier,
        title = title,
        description = description,
        mainIngredient = mainIngredient,
        price = price
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

@Preview (showBackground = true)
@Composable
fun ProductDetailCardPreview() {
    JamuInTheme {
        ProductDetailCard(
            title = "Jamu Beras Kencur",
            description = "Baik untuk ginjal. Murah loh!",
            mainIngredient = listOf("Jahe", "Temulawak"),
            price = 30000
        )
    }
}