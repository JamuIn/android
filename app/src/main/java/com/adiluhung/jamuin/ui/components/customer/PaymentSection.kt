package com.adiluhung.jamuin.ui.components.customer

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.data.sources.PaymentMethod
import com.adiluhung.jamuin.helper.ComposeFileProvider
import com.adiluhung.jamuin.helper.toRupiah
import com.adiluhung.jamuin.ui.theme.JamuInTheme
import com.adiluhung.jamuin.ui.theme.NewGreen
import com.adiluhung.jamuin.ui.theme.SoftGray

@Composable
fun SendTo() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Kirim Ke",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
    }
}

@Composable
fun MustPay() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Yang Harus Anda bayar",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
    }
}

@Composable
fun PaymentProve() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Kirim Bukti Pembayaran",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
    }
}

@Composable
fun PaymentMethodCard(paymentMethodId: Int) {
    val paymentMethod = PaymentMethod.data[paymentMethodId]

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(color = Color.White),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = paymentMethod.imageResId),
            contentDescription = "Payment method logo",
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                .size(60.dp)
        )
        Row(
            modifier = Modifier.padding(end = 165.dp)
        ) {
            Column(
                modifier = Modifier
            ) {
                Text(
                    text = "Metode ${paymentMethod.title}",
                    style = MaterialTheme.typography.bodyMedium.copy()
                )
                Text(
                    text = paymentMethod.accoutNumber,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
fun PaymentPriceCard(price: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            //.padding(horizontal = 20.dp, vertical = 20.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(color = Color.White),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = price.toRupiah(),
            style = MaterialTheme.typography.bodyLarge.copy(),
            fontWeight = FontWeight.Bold,
            color = NewGreen,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ImagePayment() {
    val context = LocalContext.current

    var hasImage by remember {
        mutableStateOf(false)
    }

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            hasImage = success
        }
    )

    Card(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .clickable {
                val uri = ComposeFileProvider.getImageUri(context)
                imageUri = uri
                cameraLauncher.launch(uri)
            },
        border = BorderStroke(2.dp, SolidColor(SoftGray)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // 4
            if (hasImage && imageUri != null) {
                // 5
                AsyncImage(
                    model = imageUri,
                    modifier = Modifier.fillMaxWidth(),
                    contentDescription = "Selected image",
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = "Add image",
                    modifier = Modifier
                        .size(80.dp)
                        .padding(top = 20.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Unggah Bukti",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(bottom = 20.dp)
                )
            }


        }
    }
}


@Preview
@Composable
fun SendToPreview() {
    JamuInTheme {
        SendTo()
    }
}

@Preview
@Composable
fun MustPayPreview() {
    JamuInTheme {
        MustPay()
    }
}

@Preview
@Composable
fun PaymentProvePreview() {
    JamuInTheme {
        PaymentProve()
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentMethodCardPreview() {
    JamuInTheme() {
        PaymentMethodCard(1)
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentPriceCardPreview(price: Int = 50000) {
    JamuInTheme {
        PaymentPriceCard(price)
    }
}

@Preview(showBackground = true)
@Composable
fun ImagePaymentPreview() {
    JamuInTheme {
        ImagePayment()
    }
}
