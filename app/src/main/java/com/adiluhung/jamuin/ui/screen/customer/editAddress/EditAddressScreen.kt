package com.adiluhung.jamuin.ui.screen.customer.editAddress

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
import com.adiluhung.jamuin.ui.components.customer.PrimaryButton
import com.adiluhung.jamuin.ui.components.customer.PrimaryTextField
import com.adiluhung.jamuin.ui.screen.ViewModelFactory
import com.adiluhung.jamuin.ui.screen.customer.editProfile.EditProfileViewModel
import com.adiluhung.jamuin.ui.theme.Dark
import com.adiluhung.jamuin.ui.theme.DodgerBlue
import com.adiluhung.jamuin.ui.theme.JamuInTheme

@Composable
fun EditAddressScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: EditAddressViewModel = viewModel(
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
        var address by remember { mutableStateOf(user?.address ?: "") }

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
                text = "Edit Alamat", style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold, color = DodgerBlue
                )
            )

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
                    fullName = user?.fullName ?: "",
                    username = user?.username ?: "",
                    email = user?.email ?: "",
                    phoneNumber = user?.phoneNumber ?: "",
                    address = address,
                    image = user?.profileImg ?: "",
                )
                Toast.makeText(context, "Alamat berhasil diupdate", Toast.LENGTH_SHORT).show()
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
fun EditAddressScreenPreview() {
    val navController = rememberNavController()
    JamuInTheme {
        EditAddressScreen(navController = navController)
    }
}