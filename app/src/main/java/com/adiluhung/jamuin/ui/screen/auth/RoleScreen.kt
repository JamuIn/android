package com.adiluhung.jamuin.ui.screen.auth

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.models.RegisterUserModel
import com.adiluhung.jamuin.route.Routes
import com.adiluhung.jamuin.ui.common.UiState
import com.adiluhung.jamuin.ui.components.customer.GreetingChooseRole
import com.adiluhung.jamuin.ui.screen.ViewModelFactory
import com.adiluhung.jamuin.ui.theme.JamuInTheme

@Composable
fun RoleScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel(
        factory = ViewModelFactory(context = LocalContext.current)
    ),
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }
    val userData =
        navController.previousBackStackEntry?.savedStateHandle?.get<RegisterUserModel>("registerData")
    Log.d("RoleScreen", userData.toString())

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
                    Toast.makeText(context, "Register berhasil", Toast.LENGTH_SHORT).show()
                    navController.navigate(Routes.Login.routes)
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
        GreetingChooseRole()

        Column(
            modifier = Modifier
                .padding(top = 52.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        viewModel.register(
                            userData?.fullname.toString(),
                            userData?.username.toString(),
                            userData?.email.toString(),
                            userData?.phoneNumber.toString(),
                            userData?.password.toString(),
                            userData?.passwordConfirmation.toString(),
                            userData?.address.toString(),
                            "customer"
                        )
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.buyer),
                    contentDescription = "User",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(bottom = 8.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    text = "User",
                    style = MaterialTheme.typography.titleSmall,
                )
            }

            Column(
                modifier = Modifier
                    .padding(bottom = 36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        viewModel.register(
                            userData?.fullname.toString(),
                            userData?.username.toString(),
                            userData?.email.toString(),
                            userData?.phoneNumber.toString(),
                            userData?.password.toString(),
                            userData?.passwordConfirmation.toString(),
                            userData?.address.toString(),
                            "seller"
                        )
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.seller),
                    contentDescription = "Seller",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(bottom = 8.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    text = "Seller",
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoleScreenPreview() {
    JamuInTheme {
        val navController = rememberNavController()
        RoleScreen(navController = navController)
    }
}