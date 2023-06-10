package com.adiluhung.jamuin.ui.components.seller

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.adiluhung.jamuin.ui.theme.JamuInTheme

@Composable
fun ProfileCard(
    modifier: Modifier = Modifier,
    navController: NavController,
    image: String,
    name: String,
    username: String,
    email: String,
    phoneNumber: String,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row() {
                Box(modifier = Modifier.weight(1f)) {
                    AsyncImage(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clip(CircleShape),
                        model = image,
                        contentDescription = "Photo Profile",
                        contentScale = ContentScale.Crop,
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(2f)) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        modifier = Modifier.padding(bottom = 8.dp),
                        text = "@$username",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(text = email, style = MaterialTheme.typography.bodySmall)
                    Text(text = phoneNumber, style = MaterialTheme.typography.bodySmall)
                }
            }
            Button(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                onClick = { /*TODO*/ }) {
                Text(text = "Edit Profil")
            }
        }
    }
}

@Preview
@Composable
fun ProfileCardPreview() {
    val navController = rememberNavController()

    JamuInTheme {
        ProfileCard(
            image = "https://picsum.photos/200/300",
            name = "John Doe",
            username = "johndoe",
            email = "johndoe@gmail.com",
            phoneNumber = "08123456789",
            navController = navController
        )
    }
}