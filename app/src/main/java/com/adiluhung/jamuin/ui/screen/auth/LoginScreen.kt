package com.adiluhung.jamuin.ui.screen.auth

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.route.Routes
import com.adiluhung.jamuin.ui.common.UiState
import com.adiluhung.jamuin.ui.components.customer.GreetingLogin
import com.adiluhung.jamuin.ui.components.customer.PasswordTextField
import com.adiluhung.jamuin.ui.components.customer.PrimaryButton
import com.adiluhung.jamuin.ui.components.customer.PrimaryTextField
import com.adiluhung.jamuin.ui.components.customer.SocialAuthentication
import com.adiluhung.jamuin.ui.screen.ViewModelFactory
import com.adiluhung.jamuin.ui.theme.DodgerBlue
import com.adiluhung.jamuin.ui.theme.JamuInTheme

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel(
        factory = ViewModelFactory(context = LocalContext.current)
    )
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    viewModel.uiState.observeAsState(initial = UiState.Empty).value.let { uiState ->
        when (uiState) {

            is UiState.Empty -> {
                isLoading = false
            }

            is UiState.Loading -> {
                isLoading = true
            }

            is UiState.Success -> {
                isLoading = false

                if(uiState.data == "customer"){
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                } else if(uiState.data == "seller"){
                    navController.navigate(Routes.SellerHome.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                }else{
                    Toast.makeText(context, "Unknown user type", Toast.LENGTH_SHORT).show()
                }
            }

            is UiState.Error -> {
                isLoading = false
                LaunchedEffect(uiState.errorMessage){
                    Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
                }
                Log.d("LoginScreen", uiState.errorMessage)
            }
        }
    }

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        if(isLoading){
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
        GreetingLogin()
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.email),
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        PrimaryTextField(
            text = email,
            onTextChange = { email = it },
            placeholder = stringResource(
                id = R.string.email,
                stringResource(id = R.string.email_address)
            ),
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = stringResource(id = R.string.password),
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        PasswordTextField(
            password = password,
            onPasswordChange = { password = it },
            placeholder = stringResource(id = R.string.password)
        )
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
            viewModel.login(email, password)
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
                navController.navigate(Routes.Register.route) {
                    popUpTo(Routes.Login.route) {
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