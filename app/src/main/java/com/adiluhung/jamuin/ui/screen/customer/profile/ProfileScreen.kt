package com.adiluhung.jamuin.ui.screen.customer.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.data.network.responses.UserResponse
import com.adiluhung.jamuin.data.sources.ProfileItem
import com.adiluhung.jamuin.route.Routes
import com.adiluhung.jamuin.ui.components.customer.BottomNavigationBar
import com.adiluhung.jamuin.ui.components.customer.PrimaryButton
import com.adiluhung.jamuin.ui.components.customer.TopBarCheckout
import com.adiluhung.jamuin.ui.screen.ViewModelFactory
import com.adiluhung.jamuin.ui.theme.DodgerBlue
import com.adiluhung.jamuin.ui.theme.JamuInTheme
import com.adiluhung.jamuin.ui.theme.NewWhite
import com.adiluhung.jamuin.ui.theme.RedFree

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory(LocalContext.current)
    )
) {
    val user = viewModel.user.observeAsState().value

    Scaffold(
        topBar = {
            TopBarCheckout(
                title = stringResource(id = R.string.profile)
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = NewWhite)
                .padding(paddingValues)
        ) {
            Header(navController = navController, user = user)
            BodyProfile(navController)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(onClick = {
                    viewModel.logout()
                    navController.navigate(Routes.Login.route) {
                        popUpTo(
                            navController.graph.startDestinationId
                        ) { inclusive = true }
                    }
                }) {
                    Text(
                        text = "Log Out",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = RedFree
                        )
                    )
                }
            }
        }
    }
}


@Composable
fun ImageProfile(image: String?, name: String?) {
    val avatarImage = image ?: "https://ui-avatars.com/api/?name=$name"

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(avatarImage).crossfade(true)
            .build(),
        contentDescription = "Image Profile",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clip(CircleShape)
            .border(width = 2.dp, color = DodgerBlue, CircleShape)
            .width(120.dp)
            .height(120.dp)
    )
}


@Composable
fun ItemMenuProfile(icon: Int, label: String, onClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(horizontal = 20.dp, vertical = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Icon(
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp),
                painter = painterResource(id = icon),
                contentDescription = "Icon",
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Composable
fun Header(navController: NavController, user: UserResponse?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = NewWhite)
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (user != null) {
            Box {
                ImageProfile(image = user.profileImg, name = user.fullName)
                IconButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .clip(CircleShape)
                        .border(width = 1.dp, color = LightGray, CircleShape),
                    colors = IconButtonDefaults.filledIconButtonColors(White),
                    onClick = { /*EDIT IMAGE*/ }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "edit image button"
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = user.fullName,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = user.email,
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton(
            text = stringResource(id = R.string.edit_profile),
            onClick = {
                navController.navigate(Routes.EditProfile.route) {
                    popUpTo(Routes.Profile.route) { inclusive = true }
                }
            }
        )
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    val navController = rememberNavController()
    JamuInTheme {
        ProfileScreen(navController = navController)
    }
}

@Preview
@Composable
fun HeaderPreview() {
    val navController = rememberNavController()
    Header(
        navController = navController, UserResponse(
            profileImg = null,
            fullName = "Adiluhung",
            address = "Jl. Raya Kedung Turi No. 1, RT 01 RW 01, Kedung Turi, Kec. Ngaliyan, Kota Semarang, Jawa Tengah 50186",
            phoneNumber = "081234567890",
            emailVerifiedAt = "2021-09-30T07:00:00.000000Z",
            id = 1,
            email = "",
            username = ""
        )
    )
}


@Composable
fun BodyProfile(navController: NavController) {
    Column(modifier = Modifier.padding(20.dp)) {
        Card(colors = CardDefaults.cardColors(containerColor = White)) {
            ProfileItem.data.forEach { item ->
                ItemMenuProfile(icon = item.Icon, label = item.Label, onClick = {
                    navController.navigate(item.Route) {
                        popUpTo(Routes.Profile.route) { inclusive = true }
                    }
                })
            }
        }
    }
}