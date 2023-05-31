package com.adiluhung.jamuin.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.ui.theme.DodgerBlue
import com.adiluhung.jamuin.ui.theme.JamuInTheme

@Preview
@Composable
fun Banner() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = DodgerBlue, contentColor = White)
    ) {
        Box(modifier = Modifier.size(width = 800.dp, height = 150.dp)) {
            Image(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(id = R.drawable.bannerbg),
                contentDescription = "banner"
            )
            Column(
                modifier = Modifier.padding(
                    start = 24.dp,
                    top = 30.dp,
                    bottom = 30.dp,
                    end = 24.dp
                )
            ) {
                JamuInTheme {
                    Text(
                        text = "10% untuk pengguna baru",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = White,
                            fontSize = 10.sp
                        )
                    )
                    Text(
                        text = "Hari Spesial",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = White,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Text(
                        text = "Temukan Jamu Alami dan \nTradisional untuk Kesehatan Keluarga Anda",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = White,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}