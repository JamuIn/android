package com.adiluhung.jamuin.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adiluhung.jamuin.ui.theme.ButtonStyle
import com.adiluhung.jamuin.ui.theme.Shapes
import com.adiluhung.jamuin.ui.theme.DodgerBlue

@Composable
fun PrimaryButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(63.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = DodgerBlue),
        shape = Shapes.medium,
        elevation = ButtonDefaults.buttonElevation(0.dp)
    ) {
        Text(
            text = text,
            style = ButtonStyle.copy(color = White, fontSize = 19.sp)
        )
    }
}
