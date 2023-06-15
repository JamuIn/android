package com.adiluhung.jamuin.ui.screen.customer.scanResult

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.helper.titlecaseFirstChar
import com.adiluhung.jamuin.route.Routes
import com.adiluhung.jamuin.ui.components.*
import com.adiluhung.jamuin.ui.components.customer.ProductCardWithRating
import com.adiluhung.jamuin.ui.components.customer.RecipeArticleCategory
import com.adiluhung.jamuin.ui.components.customer.RecipeCard
import com.adiluhung.jamuin.ui.components.customer.TopBarCheckout
import com.adiluhung.jamuin.ui.components.customer.TopProductCategory
import com.adiluhung.jamuin.ui.theme.Dark
import com.adiluhung.jamuin.ui.theme.JamuInTheme
import com.adiluhung.jamuin.ui.theme.NewWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanResultScreen(
    navController: NavController,
    viewModel: ScanResultViewModel = ScanResultViewModel(),
    scanResult: String?
) {
    var initialize by remember { mutableStateOf(false) }
    val isLoading = viewModel.isLoading.observeAsState().value
    val isLoading2 = viewModel.isLoading2.observeAsState().value

    if (!initialize) {
        viewModel.getListArticle(scanResult.toString())
        viewModel.getListProduct(scanResult.toString())

        if (isLoading == false && isLoading2 == false) {
            initialize = true
        }
    }

    val listArticle = viewModel.listArticle.observeAsState().value
    val listProduct = viewModel.listProduct.observeAsState().value

    Log.d("ScanResultScreen", "listArticle: $listArticle")
    Log.d("ScanResultScreen", "listProduct: $listProduct")

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Button(
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, bottom = 24.dp), onClick = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.ScanResult.route) {
                            inclusive = false
                        }
                    }
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
        },
        content = { paddingValues: PaddingValues ->

            Column(modifier = Modifier.padding(top = paddingValues.calculateTopPadding() - 16.dp)) {
                TopBarCheckout(
                    title = stringResource(id = R.string.scan_result)
                )

                Box(
                    modifier = Modifier
                        .background(color = NewWhite)
                        .fillMaxHeight()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = paddingValues.calculateBottomPadding() + 16.dp
                        )
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        item {

                            Text(
                                "Hasil Scan Anda: $scanResult",
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )

                            RecipeArticleCategory(isWithOther = false)
                        }

                        if (listArticle != null) {
                            items(listArticle.size) {
                                RecipeCard(
                                    modifier = Modifier.padding(bottom = 8.dp),
                                    id = listArticle[it].id,
                                    image = listArticle[it].image,
                                    title = listArticle[it].name,
                                    description = listArticle[it].description,
                                    navController = navController,
                                    mainIngredient = listArticle[it].mainIngredient.titlecaseFirstChar(),
                                )
                            }
                        }

                        item {
                            TopProductCategory(isWithOther = false)
                        }

                        if (listProduct != null) {
                            items(listProduct.size) {
                                ProductCardWithRating(
                                    modifier = Modifier
                                        .padding(end = 8.dp),
                                    image = listProduct[it].image,
                                    title = listProduct[it].name,
                                    description = listProduct[it].description,
                                    mainIngredient = listProduct[it].mainIngredient.titlecaseFirstChar(),
                                    price = listProduct[it].price,
                                    rating = 4.5,
                                    ratingNum = 25,
                                    openDetailProduct = {
                                        navController.navigate(
                                            Routes.DetailProduct.createRoute(
                                                listProduct[it].id
                                            )
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }


        }
    )
}


@Preview
@Composable
fun ScanResultScreenPreview() {
    JamuInTheme {
        // Create a NavController instance (for preview purposes)
        val navController = rememberNavController()

        // Call your CartScreen composable with the required parameters
        ScanResultScreen(
            navController = navController,
            scanResult = "Jamu Beras Kencur"
        )
    }
}
