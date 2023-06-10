package com.adiluhung.jamuin.ui.components.customer

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.adiluhung.jamuin.ui.theme.Dark
import com.adiluhung.jamuin.ui.theme.Shapes

@Composable
fun PrimaryOutlineButton(modifier: Modifier, label: String, onClick: () -> Unit = {}) {
    OutlinedButton(
        modifier = modifier,
        shape = Shapes.medium,
        onClick = onClick
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(
                color = Dark,
                fontWeight = FontWeight.SemiBold, fontSize = 11.sp
            )
        )
    }
}

@Preview
@Composable
fun PrimaryOutlineButtonPreview() {
    PrimaryOutlineButton(
        modifier = Modifier,
        label = "Button",
        onClick = {}
    )
}