package com.adiluhung.jamuin.ui.components.customer

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
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
fun SellerCard(navController: NavController, seller: String) {
    val interactionSource = remember { MutableInteractionSource() }
    Surface(
        color = White,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        val avatarImage = "https://ui-avatars.com/api/?name=$seller"

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

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(avatarImage)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Seller Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(60.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = seller,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                FilledIconButton(
                    onClick = {
                        navController.navigate(Routes.Chat.route)
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


@Preview(showBackground = true)
@Composable
fun SellerCardPreview() {
    JamuInTheme() {
        // Create a dummy NavController for preview
        val navController = rememberNavController()

        SellerCard(navController = navController, seller = "Halo")
    }
}

@Preview
@Composable
fun SellerPreview() {
    JamuInTheme {
        Seller(seller = "Ariel Noah")
    }
}
