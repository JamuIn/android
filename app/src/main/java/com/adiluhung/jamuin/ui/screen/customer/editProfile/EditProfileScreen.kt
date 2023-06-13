package com.adiluhung.jamuin.ui.screen.customer.editProfile

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.route.Routes
import com.adiluhung.jamuin.ui.components.customer.PrimaryButton
import com.adiluhung.jamuin.ui.components.customer.PrimaryTextField
import com.adiluhung.jamuin.ui.screen.ViewModelFactory
import com.adiluhung.jamuin.ui.theme.Dark
import com.adiluhung.jamuin.ui.theme.DodgerBlue
import com.adiluhung.jamuin.ui.theme.JamuInTheme

@Composable
fun EditProfileScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: EditProfileViewModel = viewModel(
        factory = ViewModelFactory(
            LocalContext.current
        )
    )
) {
    val context = LocalContext.current
    val user = viewModel.user.value
    val isLoading = viewModel.isLoading.observeAsState().value

    if (isLoading == false) {
        val userId = user?.id ?: 0
        val userImage = user?.profileImg ?: ""
        var fullName by remember { mutableStateOf(user?.fullName ?: "") }
        var username by remember { mutableStateOf(user?.username ?: "") }
        var email by remember { mutableStateOf(user?.email ?: "") }
        var address by remember { mutableStateOf(user?.address ?: "") }
        var phoneNumber by remember { mutableStateOf(user?.phoneNumber ?: "") }

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Button(
                modifier = Modifier.padding(bottom = 24.dp), onClick = {
                    navController.popBackStack()
                }, colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White, contentColor = Dark
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = stringResource(id = R.string.back)
                    )
                    Text(text = stringResource(id = R.string.back))
                }
            }

            Text(
                text = "Edit Profil", style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold, color = DodgerBlue
                )
            )

            Text(
                text = stringResource(id = R.string.fullname),
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp)
            )
            Spacer(modifier = Modifier.height(3.dp))
            PrimaryTextField(placeholder = stringResource(id = R.string.fullname),
                text = fullName,
                onTextChange = {
                    fullName = it
                })

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.user_name),
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            PrimaryTextField(placeholder = stringResource(id = R.string.user_name),
                text = username,
                onTextChange = { username = it })

            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = stringResource(id = R.string.email),
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            PrimaryTextField(placeholder = stringResource(id = R.string.email),
                text = email,
                onTextChange = { email = it })

            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = stringResource(id = R.string.phone_number),
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            PrimaryTextField(placeholder = stringResource(id = R.string.phone_number),
                text = phoneNumber,
                onTextChange = { phoneNumber = it })


            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = stringResource(id = R.string.address),
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            PrimaryTextField(placeholder = stringResource(id = R.string.address),
                text = address,
                onTextChange = { address = it })
            Spacer(modifier = Modifier.height(16.dp))
            PrimaryButton(text = "Simpan", onClick = {
                viewModel.updateProfile(
                    id = userId,
                    fullName = fullName,
                    username = username,
                    email = email,
                    phoneNumber = phoneNumber,
                    address = address,
                    image = userImage,
                )
                Toast.makeText(context, "Profile berhasil diupdate", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            })
        }
    } else {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    val navController = rememberNavController()
    JamuInTheme {
        EditProfileScreen(navController = navController)
    }
}