package com.adiluhung.jamuin.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.route.Routes
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

@Composable
fun SellerCard(navController: NavController) {
    val interactionSource = remember { MutableInteractionSource() }
    Surface(
        color = White,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Seller",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.your_image),
                    contentDescription = "Seller Image",
                    modifier = Modifier
                        .padding(16.dp)
                        .size(60.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Official Jamu 88",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                    Text(
                        text = "Rizky Billar",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                FilledIconButton(
                    onClick = {
                        navController.navigate(Routes.Chat.routes)
                    },
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.Transparent)
                ) {
                    Icon(
                        modifier = Modifier.width(20.dp),
                        painter = painterResource(id = R.drawable.chat),
                        contentDescription = "Chat"
                    )
                }
            }
        }
    }
}



@Preview (showBackground = true)
@Composable
fun SellerCardPreview() {
    JamuInTheme() {
        // Create a dummy NavController for preview
        val navController = rememberNavController()

        SellerCard(navController = navController)
    }
}

@Preview
@Composable
fun SellerPreview() {
    JamuInTheme {
        Seller(seller = "Ariel Noah")
    }
}
