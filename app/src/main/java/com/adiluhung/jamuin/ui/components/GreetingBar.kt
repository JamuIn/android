package com.adiluhung.jamuin.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.route.Routes
import com.adiluhung.jamuin.ui.theme.DodgerBlue
import com.adiluhung.jamuin.ui.theme.JamuInTheme
import com.adiluhung.jamuin.ui.theme.Transparant

@Composable
fun GreetingBar(navController: NavController) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                    text = "Halo, Rizky Billar",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = DodgerBlue
                )
            )
            Text(
                text = "Senang bertemu lagi hari ini \nApa yang kamu cari?",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
        }
        FilledIconButton(
            onClick = {
                navController.navigate(Routes.Chat.routes)
            },
            colors = IconButtonDefaults.filledIconButtonColors(containerColor = Transparant)
        ) {
            Icon(
                modifier = Modifier.width(20.dp),
                painter = painterResource(id = R.drawable.chat),
                contentDescription = "Chat"
            )
        }
    }
}

@Composable
fun GreetingLogin() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            //modifier = Modifier.padding(16.dp),
            //verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            Text(
                text = "Masuk Akun",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = DodgerBlue
                )
            )
            Text(
                text = "Gunakan akun dan login terlebih dahulu \nuntuk mengakses fitur",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
        }
    }
}

@Composable
fun GreetingRegister() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
//            modifier = Modifier.padding(16.dp),
//            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            Text(
                text = "Selamat Datang",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = DodgerBlue
                )
            )
            Text(
                text = "Untuk membuat akun Anda,\nsilakan lanjutkan dengan proses daftar",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
        }
    }
}

@Composable
fun GreetingChooseRole() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
        ) {
            Text(
                text = "Selangkah Lagi",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = DodgerBlue
                )
            )
            Text(
                text = "Untuk mendapat pengalaman terbaik,\nberitahukan kepada kami tujuan Anda sebagai",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
        }
    }
}
@Preview
@Composable
fun GreetingBarPreview() {
    JamuInTheme {
        val navController = rememberNavController()
        GreetingBar(navController = navController)
    }
}

@Preview
@Composable
fun GreetingLoginPreview() {
    JamuInTheme {
        GreetingLogin()
    }
}

@Preview
@Composable
fun GreetingRegisterPreview() {
    JamuInTheme {
        GreetingRegister()
    }
}

@Preview
@Composable
fun GreetingChooseRolePreview() {
    JamuInTheme {
        GreetingChooseRole()
    }
}
