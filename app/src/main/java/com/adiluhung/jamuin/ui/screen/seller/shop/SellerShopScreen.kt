package com.adiluhung.jamuin.ui.screen.seller.shop

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.ui.components.ProductCardEditable
import com.adiluhung.jamuin.ui.components.seller.SellerBottomNavbar
import com.adiluhung.jamuin.ui.theme.JamuInTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellerShopScreen(modifier: Modifier = Modifier, navController: NavController) {
    Scaffold(bottomBar = {
        SellerBottomNavbar(navController = navController)
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(
                    top = 16.dp, bottom = innerPadding.calculateBottomPadding(), start = 16.dp, end = 16.dp
                )
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Toko Anda",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )

            Text(
                text = "Profil Toko", style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )

            Card(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row() {
                        Box() {
                            Image(
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .align(Alignment.CenterStart)
                                    .clip(RoundedCornerShape(8.dp)),
                                painter = painterResource(id = R.drawable.detail_image_product),
                                contentDescription = "shop image"
                            )
                            IconButton(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .clip(CircleShape),
                                colors = IconButtonDefaults.filledIconButtonColors(Color.White),
                                onClick = { /*EDIT SHOPE IMAGE*/ }) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "edit image button"
                                )
                            }
                        }
                        Column(modifier = Modifier.padding(start = 8.dp)) {
                            Text(
                                text = "Toko Jaya Satu Kali",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                            Text(
                                text = "UMKM jamu yang dibuat oleh ngabers Luxemburg",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Pesanan Selesai: 10",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        )
                        OutlinedButton(
                            onClick = { /*TODO*/ },

                            ) {
                            Icon(
                                modifier = Modifier.padding(end = 4.dp),
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit Button"
                            )
                            Text(text = "Edit Toko")
                        }
                    }
                }
            }

            Button(
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Tambah Produk", fontWeight = FontWeight.Bold)
            }

            Text(
                text = "Produk Anda", style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
            ProductCardEditable(
                modifier = Modifier.padding(bottom = 4.dp),
                image = "",
                title = "jamu Beras Kencur",
                description = "baik untuk ginjal dan hati",
                mainIngredient = listOf("Jamu"),
                price = 30000,
                onClickDeleteButton = { /*TODO*/ }) {

            }
            ProductCardEditable(
                modifier = Modifier.padding(bottom = 4.dp),
                image = "",
                title = "jamu Beras Kencur",
                description = "baik untuk ginjal dan hati",
                mainIngredient = listOf("Jamu"),
                price = 30000,
                onClickDeleteButton = { /*TODO*/ }) {

            }
        }
    }
}

@Preview
@Composable
fun SellerShopScreenPreview() {
    JamuInTheme {
        val navController = rememberNavController()
        SellerShopScreen(navController = navController)
    }
}