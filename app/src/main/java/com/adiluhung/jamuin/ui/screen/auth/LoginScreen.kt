package com.adiluhung.jamuin.ui.screen.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.route.Routes
import com.adiluhung.jamuin.ui.components.*
import com.adiluhung.jamuin.ui.theme.DodgerBlue
import com.adiluhung.jamuin.ui.theme.JamuInTheme

@Composable
fun LoginScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        GreetingLogin()
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.email),
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        PrimaryTextField(
            placeholder = stringResource(id = R.string.email, stringResource(id = R.string.email_address)),
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = stringResource(id = R.string.password),
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        PasswordTextField(placeholder = stringResource(id = R.string.password))

        Spacer(modifier = Modifier.height(5.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            TextButton(
                onClick = {},
            ) {
                Text(
                    text = stringResource(id = R.string.forgot_password),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 15.sp,
                        color = Color.LightGray
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(5.dp))
        PrimaryButton(text = stringResource(id = R.string.login), onClick = {
            navController.navigate(Routes.Dashboard.routes) {
                popUpTo(Routes.Login.routes) {
                    inclusive = true
                }
            }
        })

        Spacer(modifier = Modifier.height(20.dp))

        SocialAuthentication()

        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Belum punya akun?",
                style = MaterialTheme.typography.bodySmall.copy(color = Color.LightGray)
            )
            Spacer(modifier = Modifier.width(5.dp))
            TextButton(onClick = {
                navController.navigate(Routes.Register.routes) {
                    popUpTo(Routes.Login.routes) {
                        inclusive = true
                    }
                }
            }) {
                Text(
                    text = stringResource(id = R.string.sign_up),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = DodgerBlue,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    JamuInTheme {
        val navController = rememberNavController()
        LoginScreen(navController = navController)
    }
}