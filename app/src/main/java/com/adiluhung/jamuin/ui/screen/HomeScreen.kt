package com.adiluhung.jamuin.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.route.Routes
import com.adiluhung.jamuin.ui.components.*
import com.adiluhung.jamuin.ui.theme.JamuInTheme
import com.adiluhung.jamuin.ui.theme.NewWhite

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    navController: NavController,
) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = NewWhite),
            contentPadding = paddingValues
        ) {
            item {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    GreetingBar(navController = navController)
                    Spacer(modifier = Modifier.height(15.dp))
                    SearchField(
                        placeholder = stringResource(id = R.string.placeholder_search),
                        enable = false,
                        onClick = {
                            navController.navigate(Routes.Search.routes)
                        },
                        value = ""
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Banner()
                    Spacer(modifier = Modifier.height(15.dp))
                    TopProductCategory()

                    LazyRow(modifier = Modifier.padding(start = 16.dp, end = 0.dp)) {
                        item {
                            ProdutCard(
                                title = "Jamu Beras Kencur",
                                price = 28500,
                                mainIngredient = "Jahe",
                                description = "Jamu yang berguna untuk menyehatkan jiwa raga",
                                image = ""
                            )
                        }
                        item {
                            ProdutCard(
                                title = "Jamu Beras Kencur",
                                price = 28500,
                                mainIngredient = "Jahe",
                                description = "Jamu yang berguna untuk menyehatkan jiwa raga",
                                image = ""
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(15.dp))
                    RecipeArticleCategory()
                    Spacer(modifier = Modifier.padding(start = 16.dp, end = 0.dp))
                    RecipeArticle(
                        item = JamuDummy(
                            id = "1",
                            banner = "",
                            title = "Jamu Kencur"
                        ),
                        navController = navController
                    )
                }
            }
            item {

            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    JamuInTheme {
        val paddingValues = PaddingValues(16.dp)
        val navController = rememberNavController()
        HomeScreen(paddingValues, navController)
    }
}
