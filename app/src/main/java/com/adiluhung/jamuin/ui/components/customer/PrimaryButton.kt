package com.adiluhung.jamuin.ui.components.customer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adiluhung.jamuin.ui.theme.ButtonStyle
import com.adiluhung.jamuin.ui.theme.DodgerBlue
import com.adiluhung.jamuin.ui.theme.Shapes

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

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight(),
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(contentColor = White),
        shape = Shapes.medium,
        border = BorderStroke(1.dp, Black)
    ) {
        Text(
            text = text,
            style = ButtonStyle.copy(color = Black)
        )
    }
}




