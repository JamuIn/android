package com.adiluhung.jamuin.ui.components.customer

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.ui.theme.LightGray
import com.adiluhung.jamuin.ui.theme.SoftGray


@Composable
fun SocialAuthentication() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(
            text = "Daftar dengan Google atau Facebook",
            style = MaterialTheme.typography.bodySmall.copy(color = LightGray)
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    Row(modifier = Modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier
                .border(width = 2.dp, color = SoftGray, shape = RoundedCornerShape(12.dp))
                .padding(vertical = 20.dp, horizontal = 10.dp)
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "Google Icon",
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
            )
            Spacer(modifier = Modifier.width(7.dp))
            Text(
                text = "Google",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
            )
        }

        Spacer(modifier = Modifier.width(5.dp))

        Row(
            modifier = Modifier
                .border(width = 2.dp, color = SoftGray, shape = RoundedCornerShape(12.dp))
                .padding(vertical = 20.dp, horizontal = 10.dp)
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.facebook),
                contentDescription = "Facebook Icon",
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
            )
            Spacer(modifier = Modifier.width(7.dp))
            Text(
                text = "Facebook",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
            )
        }
    }
}

@Preview
@Composable
fun SocialAuthenticationPreview() {
    SocialAuthentication()
}
