package com.adiluhung.jamuin.ui.screen.auth

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.models.RegisterUserModel
import com.adiluhung.jamuin.route.Routes
import com.adiluhung.jamuin.ui.common.UiState
import com.adiluhung.jamuin.ui.components.customer.GreetingRegister
import com.adiluhung.jamuin.ui.components.customer.PasswordTextField
import com.adiluhung.jamuin.ui.components.customer.PrimaryButton
import com.adiluhung.jamuin.ui.components.customer.PrimaryTextField
import com.adiluhung.jamuin.ui.components.customer.SocialAuthentication
import com.adiluhung.jamuin.ui.screen.ViewModelFactory
import com.adiluhung.jamuin.ui.theme.DodgerBlue
import com.adiluhung.jamuin.ui.theme.JamuInTheme
import com.adiluhung.jamuin.ui.theme.LightGray

@Composable
fun RegisterScreen(
    navController: NavController, viewModel: AuthViewModel = viewModel(
        factory = ViewModelFactory(context = LocalContext.current)
    ),
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    var fullname by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirmation by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
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
                LaunchedEffect(uiState.data) {
                    Toast.makeText(context, uiState.data, Toast.LENGTH_SHORT).show()
                    navController.navigate(Routes.Login.route)
                }
            }

            is UiState.Error -> {
                isLoading = false
                LaunchedEffect(uiState.errorMessage) {
                    Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
                }
                Log.d("LoginScreen", uiState.errorMessage)
            }
        }
    }

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(20.dp)
    ) {
        if (isLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
        GreetingRegister()
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.fullname),
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        PrimaryTextField(placeholder = stringResource(id = R.string.fullname),
            text = fullname,
            onTextChange = { fullname = it })

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.user_name),
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        PrimaryTextField(placeholder = stringResource(id = R.string.user_name),
            text = username,
            onTextChange = { username = it })

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = stringResource(id = R.string.email),
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        PrimaryTextField(placeholder = stringResource(id = R.string.email),
            text = email,
            onTextChange = { email = it })

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = stringResource(id = R.string.phone_number),
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        PrimaryTextField(placeholder = stringResource(id = R.string.phone_number),
            text = phoneNumber,
            onTextChange = { phoneNumber = it })


        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = stringResource(id = R.string.address),
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        PrimaryTextField(placeholder = stringResource(id = R.string.address),
            text = address,
            onTextChange = { address = it })

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = stringResource(id = R.string.password),
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp)
        )

        PasswordTextField(placeholder = stringResource(id = R.string.password),
            password = password,
            onPasswordChange = { password = it })

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = stringResource(id = R.string.password_confirmation),
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp)
        )

        Spacer(modifier = Modifier.height(3.dp))
        PasswordTextField(placeholder = stringResource(id = R.string.password_confirmation),
            password = passwordConfirmation,
            onPasswordChange = { passwordConfirmation = it })


        Spacer(modifier = Modifier.height(20.dp))
        PrimaryButton(text = stringResource(id = R.string.register), onClick = {
            val data = RegisterUserModel(
                fullname = fullname,
                username = username,
                email = email,
                password = password,
                passwordConfirmation = passwordConfirmation,
                address = address,
                phoneNumber = phoneNumber
            )
            navController.currentBackStackEntry?.savedStateHandle?.apply {
                set("registerData", data)
            }
            navController.navigate(Routes.Role.route)
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
                text = "Sudah punya akun?",
                style = MaterialTheme.typography.bodyMedium.copy(color = LightGray)
            )
            Spacer(modifier = Modifier.width(5.dp))
            TextButton(onClick = {
                navController.navigate(Routes.Login.route) {
                    popUpTo(Routes.Register.route) {
                        inclusive = true
                    }
                }
            }) {
                Text(
                    text = stringResource(id = R.string.sign_in),
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = DodgerBlue, fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    JamuInTheme {
        val navController = rememberNavController()
        RegisterScreen(navController = navController)
    }
}
