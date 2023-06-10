package com.adiluhung.jamuin.ui.components.customer

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.ui.theme.LightGray
import com.adiluhung.jamuin.ui.theme.MaxYellow

@Composable
fun Star(rating: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.width(15.dp),
            painter = painterResource(id = R.drawable.star_icon),
            contentDescription = "Star",
            tint = MaxYellow
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = rating,
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = LightGray,
                lineHeight = 0.sp
            )
        )
    }
}

@Composable
fun ReviewStar(rating: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        repeat(5) {
            Icon(
                modifier = Modifier.width(15.dp),
                painter = painterResource(id = R.drawable.star_icon),
                contentDescription = "Star",
                tint = MaxYellow
            )
            Spacer(modifier = Modifier.width(5.dp))
        }
        Text(
            text = rating,
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = LightGray,
                lineHeight = 0.sp
            )
        )
    }
}

@Preview
@Composable
fun ReviewStarPreview() {
    ReviewStar(rating = "5")
}

