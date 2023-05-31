package com.adiluhung.jamuin.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
import com.adiluhung.jamuin.ui.theme.LightGray

@Composable
fun RegisterScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(20.dp)
    ) {
        GreetingRegister()
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.user_name),
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        PrimaryTextField(placeholder = stringResource(id = R.string.user_name))

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = stringResource(id = R.string.email),
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        PrimaryTextField(placeholder = stringResource(id = R.string.email))

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = stringResource(id = R.string.password),
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        PasswordTextField(placeholder = stringResource(id = R.string.password))

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = stringResource(id = R.string.address),
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        PrimaryTextField(placeholder = stringResource(id = R.string.address))

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = stringResource(id = R.string.phone_number),
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        PrimaryTextField(placeholder = stringResource(id = R.string.phone_number))


        Spacer(modifier = Modifier.height(20.dp))
        PrimaryButton(text = stringResource(id = R.string.register), onClick = {})

        Spacer(modifier = Modifier.height(20.dp))

        SocialAuthentication()

        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sudah punya akun?",
                style = MaterialTheme.typography.bodyMedium.copy(color = LightGray)
            )
            Spacer(modifier = Modifier.width(5.dp))
            TextButton(onClick = {
                navController.navigate(Routes.Login.routes) {
                    popUpTo(Routes.Register.routes) {
                        inclusive = true
                    }
                }
            }) {
                Text(
                    text = stringResource(id = R.string.sign_in),
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = DodgerBlue,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun RegisterScreenPreview() {
    JamuInTheme {
        val navController = rememberNavController()
        RegisterScreen(navController = navController)
    }
}
