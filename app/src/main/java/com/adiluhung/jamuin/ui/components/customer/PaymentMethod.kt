package com.adiluhung.jamuin.ui.components.customer

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adiluhung.jamuin.data.sources.PaymentMethod
import com.adiluhung.jamuin.ui.theme.JamuInTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentMethodDropDown(
    modifier: Modifier = Modifier,
    onChange: (Int) -> Unit
) {
    var selectedPaymentMethod by remember { mutableIntStateOf(0) }
    var expanded by remember { mutableStateOf(false) }

    if (!expanded) {
        if (selectedPaymentMethod == 0) {
            Card(
                modifier = Modifier.fillMaxSize(),
                elevation = CardDefaults.cardElevation(1.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                onClick = {
                    expanded = true
                }
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Pilih Metode Pembayaran")
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown Icon"
                    )
                }
            }
        } else {
            Card(
                modifier = Modifier.fillMaxSize(),
                elevation = CardDefaults.cardElevation(1.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                onClick = {
                    expanded = true
                }
            ) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = PaymentMethod.data[selectedPaymentMethod - 1].imageResId),
                        contentDescription = "Payment Method Icon",
                        modifier = Modifier
                            .wrapContentSize()
                            .height(40.dp)
                            .padding(end = 8.dp)
                            .weight(1f),
                        contentScale = ContentScale.Crop
                    )
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = PaymentMethod.data[selectedPaymentMethod - 1].title)
                        Text(text = PaymentMethod.data[selectedPaymentMethod - 1].accoutNumber)
                    }
                    Icon(
                        modifier = Modifier.weight(1f),
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown Icon"
                    )
                }
            }
        }
    } else {
        Card(
            modifier = Modifier.fillMaxSize(),
            elevation = CardDefaults.cardElevation(1.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            onClick = {
                expanded = false
            }
        ) {
            PaymentMethod.data.forEachIndexed { index, paymentMethod ->
                Column {
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable {
                                selectedPaymentMethod = index + 1
                                expanded = false
                                onChange(paymentMethod.id)
                            },
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            painter = painterResource(id = paymentMethod.imageResId),
                            contentDescription = "Payment Method Icon",
                            modifier = Modifier
                                .wrapContentSize()
                                .height(40.dp)
                                .padding(end = 8.dp)
                                .weight(1f),
                            contentScale = ContentScale.Crop
                        )
                        Column(modifier = Modifier.weight(3f)) {
                            Text(text = paymentMethod.title)
                            Text(text = paymentMethod.accoutNumber)
                        }
                    }
                    Divider()

                }

            }
        }
    }
}

@Preview
@Composable
fun PaymentMethodDropDownPreview() {
    JamuInTheme {
        PaymentMethodDropDown(onChange = {})
    }
}