package com.adiluhung.jamuin.ui.screen.customer

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.data.sources.ProfileItem
import com.adiluhung.jamuin.route.Routes
import com.adiluhung.jamuin.ui.components.customer.PrimaryButton
import com.adiluhung.jamuin.ui.theme.DodgerBlue
import com.adiluhung.jamuin.ui.theme.NewWhite
import com.adiluhung.jamuin.ui.theme.RedFree

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold { paddingValues ->
        val padding = paddingValues
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = NewWhite)
        ) {
            item {
                Header(navController = navController)
                Body()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(onClick = { /*TODO*/ }) {
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
}


@Composable
fun ImageProfile() {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://cdn-icons-png.flaticon.com/512/3135/3135715.png").crossfade(true).build(),
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
fun ItemMenuProfile(icon: Int, label: String) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                Toast
                    .makeText(context, R.string.in_dev, Toast.LENGTH_SHORT)
                    .show()
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
fun Header(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = White)
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageProfile()
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Rizky Billar",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "rizkybillar@gmail.com",
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        PrimaryButton(
            text = stringResource(id = R.string.edit_profile),
            onClick = {
                // navController.navigate(Routes.Home.routes) {
                //     popUpTo(Routes.Login.routes) {
                //         inclusive = true
                //     }
                // }
            }
        )
    }
}

@Preview
@Composable
fun HeaderPreview() {
    val navController = rememberNavController()
    Header(navController = navController)
}


@Preview(showBackground = true)
@Composable
fun Body() {
    Column(modifier = Modifier.padding(20.dp)) {
        Card(colors = CardDefaults.cardColors(containerColor = White)) {
            ProfileItem.data.forEach { item ->
                ItemMenuProfile(icon = item.Icon, label = item.Label)
            }
        }
    }
}