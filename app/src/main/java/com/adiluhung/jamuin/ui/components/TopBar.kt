package com.adiluhung.jamuin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.ui.theme.Dark
import com.adiluhung.jamuin.ui.theme.DodgerBlue
import com.adiluhung.jamuin.ui.theme.JamuInTheme
import com.adiluhung.jamuin.ui.theme.Poppins

@Composable
fun TopBar(modifier: Modifier = Modifier, title: String, icon: Int, onClick: () -> Unit = {}) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = White)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium.copy(
                color = DodgerBlue,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold
            )
        )
        IconButton(
            modifier = Modifier.width(18.dp), onClick = { onClick() }) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "JamuIN", tint = Dark
            )
        }
    }
}

@Preview
@Composable
fun TopBarPreview() {
    JamuInTheme {
        TopBar(
            title = "JamuIN",
            icon = R.drawable.ic_baseline_local_drink_24
        )
    }
}