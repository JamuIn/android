package com.adiluhung.jamuin.ui.components.seller

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.adiluhung.jamuin.helper.toRupiah
import com.adiluhung.jamuin.models.OrderProduct
import com.adiluhung.jamuin.ui.theme.JamuInTheme

@Composable
fun OrderCard(
    modifier: Modifier = Modifier,
    customer: String,
    listProduct: List<OrderProduct>,
    navController: NavController
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(modifier = modifier.padding(16.dp).fillMaxWidth()) {
            Text(
                text = customer,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            listProduct.forEach {
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .weight(1f),
                        model = it.image,
                        contentDescription = "Product Image",
                        contentScale = ContentScale.Crop,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Column(modifier = Modifier.weight(2f)) {

                        Text(
                            text = it.name,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = it.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            maxLines = 2
                        )
                        Text(
                            text = it.price.toRupiah(),
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }

                    Text(text = it.quantity.toString() + "x")
                }
            }
            Button(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.End),
                onClick = { /*TODO*/ }) {
                Text(text = "Terima Pesanan", fontWeight = FontWeight.SemiBold)
            }
        }
    }
}


@Composable
fun OrderCardDone(
    modifier: Modifier = Modifier,
    customer: String,
    listProduct: List<OrderProduct>,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(modifier = modifier.padding(16.dp).fillMaxWidth()) {
            Text(
                text = customer,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            listProduct.forEach {
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .weight(1f),
                        model = it.image,
                        contentDescription = "Product Image",
                        contentScale = ContentScale.Crop,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Column(modifier = Modifier.weight(2f)) {

                        Text(
                            text = it.name,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = it.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            maxLines = 2
                        )
                        Text(
                            text = it.price.toRupiah(),
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }

                    Text(text = it.quantity.toString() + "x")
                }
            }
        }
    }
}


@Preview
@Composable
fun OrderCardPreview() {
    val navController = rememberNavController()
    JamuInTheme {
        OrderCard(
            customer = "Adiluhung", listProduct = listOf(
                OrderProduct(
                    name = "Jamu Beras Kencur",
                    description = "Baik untuk ginjal dan hati",
                    price = 30000,
                    quantity = 2,
                    image = ""
                ),
                OrderProduct(
                    name = "Jamu Beras Kencur",
                    description = "Baik untuk ginjal dan hati",
                    price = 30000,
                    quantity = 2,
                    image = ""
                )
            ),
            navController = navController
        )
    }
}