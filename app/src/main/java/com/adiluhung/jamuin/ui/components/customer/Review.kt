package com.adiluhung.jamuin.ui.components.customer

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.ui.theme.JamuInTheme
import com.adiluhung.jamuin.ui.theme.MaxYellow

@Composable
fun Review(review: String, star: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = review,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color.LightGray
            )
        )
        Spacer(modifier = Modifier.width(5.dp))
        Icon(
            modifier = Modifier
                .width(18.dp)
                .height(18.dp),
            painter = painterResource(id = R.drawable.star_icon),
            tint = MaxYellow,
            contentDescription = "review",
        )
        Text(
            text = star,
            style = MaterialTheme.typography.bodySmall.copy(
                color = Color.LightGray
            )
        )
    }
}

@Composable
fun ReviewerCard() {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.your_image),
            contentDescription = "Seller Image",
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                .size(60.dp)
        )
        Column(
            modifier = Modifier.padding(start = 20.dp, end = 90.dp)
        ) {
            Text(
                text = "Pembeli123",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                )
            )
            Text(
                text = "Barangnya bagus sekali",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            ReviewStar(rating = "5")
        }
    }
}




@Preview
@Composable
fun ReviewPreview() {
    JamuInTheme {
        Review(review = "Review", star = "5 (2)")
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewerCardPreview() {
    JamuInTheme() {
        ReviewerCard()
    }
}