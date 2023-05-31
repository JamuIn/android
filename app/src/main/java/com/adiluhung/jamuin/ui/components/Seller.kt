package com.adiluhung.jamuin.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.ui.theme.JamuInTheme

@Composable
fun Seller(seller: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = seller,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 9.sp,
                color = Color.LightGray
            )
        )
        Spacer(modifier = Modifier.width(5.dp))
        Icon(
            modifier = Modifier
                .width(12.dp)
                .height(12.dp),
            painter = painterResource(id = R.drawable.verified),
            contentDescription = "seller",
        )
    }
}

@Preview
@Composable
fun SellerPreview() {
    JamuInTheme {
        Seller(seller = "Ariel Noah")
    }
}
