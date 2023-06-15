package com.adiluhung.jamuin.ui.components.customer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.helper.toRupiah
import com.adiluhung.jamuin.route.Routes
import com.adiluhung.jamuin.ui.screen.customer.checkout.InvoiceItem
import com.adiluhung.jamuin.ui.theme.JamuInTheme
import com.adiluhung.jamuin.ui.theme.SoftGray


@Composable
fun CheckoutProduct() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "produk yang Dibeli",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
    }
}

@Composable
fun BuyerAddres() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Alamat",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
    }
}

@Composable
fun PaymentMethod() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Metode Pembayaran",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
    }
}

@Composable
fun PriceDetail() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Rincian Harga",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
    }
}

@Composable
fun BottomCart(listInvoiceItem: List<InvoiceItem>?, totalPrice: Int?) {
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(2.dp, SolidColor(SoftGray))
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Spacer(modifier = Modifier.height(5.dp))
                listInvoiceItem?.map {
                    PriceDetailItem(
                        label = it.name,
                        value = it.price.toRupiah()
                    )
                }

                if (totalPrice != null) {
                    PriceDetailItem(
                        label = stringResource(id = R.string.total),
                        value = totalPrice.toRupiah(),
                        valueColor = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        }
    }
}

@Composable
fun PriceDetailItem(
    label: String,
    value: String,
    valueColor: Color = Color.LightGray
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium.copy(color = SoftGray)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium.copy(color = valueColor)
        )
    }
}

@Composable
fun Address(navController: NavController, address: String?) {
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(2.dp, SolidColor(SoftGray))
        ) {
            Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.Center) {
                if (address != null) {
                    Text(
                        text = address,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            SecondaryButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.change_address),
                onClick = {
                    navController.navigate(Routes.EditAddress.route)
                }
            )
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}


@Preview
@Composable
fun CheckoutProductPreview() {
    JamuInTheme {
        CheckoutProduct()
    }
}

@Preview
@Composable
fun BuyerAddresPreview() {
    JamuInTheme {
        BuyerAddres()
    }
}

@Preview
@Composable
fun PaymentMethodPreview() {
    JamuInTheme {
        PaymentMethod()
    }
}

@Preview
@Composable
fun PriceDetailPreview() {
    JamuInTheme {
        PriceDetail()
    }
}

@Preview
@Composable
fun BottomCartPreview() {
    JamuInTheme {
        BottomCart(
            listInvoiceItem = listOf(
                InvoiceItem(
                    name = "Jamu Beras Kencur",
                    price = 10000,
                ),
                InvoiceItem(
                    name = "Jamu Beras Kencur",
                    price = 10000,
                ),
                InvoiceItem(
                    name = "Jamu Beras Kencur",
                    price = 10000,
                )
            ),
            totalPrice = 30000
        )
    }
}

@Preview
@Composable
fun AddressPreview() {
    JamuInTheme {
        Address(navController = rememberNavController(), address = "Bogor, Indonesia")
    }
}
