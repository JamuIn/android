package com.adiluhung.jamuin.ui.components.customer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.adiluhung.jamuin.ui.theme.JamuInTheme

@Composable
fun ChatCard(
    modifier: Modifier = Modifier,
    image: String,
    from: String,
    lastChat: String,
    lastUpdate: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = image,
                contentDescription = "avatar",
                modifier = Modifier
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .weight(1f)
            )
            Column {
                Text(
                    text = from,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = lastChat,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Text(
                text = lastUpdate,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.align(Alignment.Top)
            )
        }
    }
}

@Preview
@Composable
fun ChatCard() {
    JamuInTheme {
        ChatCard(
            image = "",
            from = "Penjual Jamu55",
            lastChat = "Anda: Jamunya masih ready kak?",
            lastUpdate = "Kemarin",
            onClick = {}
        )
    }
}