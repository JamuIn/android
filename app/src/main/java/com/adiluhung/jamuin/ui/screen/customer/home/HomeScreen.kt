package com.adiluhung.jamuin.ui.screen.customer.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.route.Routes
import com.adiluhung.jamuin.ui.components.*
import com.adiluhung.jamuin.ui.components.customer.Banner
import com.adiluhung.jamuin.ui.components.customer.BottomNavigationBar
import com.adiluhung.jamuin.ui.components.customer.GreetingBar
import com.adiluhung.jamuin.ui.components.customer.ProductCardBig
import com.adiluhung.jamuin.ui.components.customer.RecipeCard
import com.adiluhung.jamuin.ui.components.customer.RecipeArticleCategory
import com.adiluhung.jamuin.ui.components.customer.SearchField
import com.adiluhung.jamuin.ui.components.customer.TopProductCategory
import com.adiluhung.jamuin.ui.screen.ViewModelFactory
import com.adiluhung.jamuin.ui.theme.JamuInTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(context = LocalContext.current)
    )
) {
    val name = viewModel.name.observeAsState().value ?: ""
    val firstName = name.split(" ")[0]

    val isLoading = viewModel.isLoading.observeAsState().value ?: false

    val listRecipe = viewModel.listRecipe.observeAsState().value ?: emptyList()
    val listProduct = viewModel.listProduct.observeAsState().value ?: emptyList()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    bottom = innerPadding.calculateBottomPadding(),
                    start = 16.dp,
                    end = 16.dp
                )
                .verticalScroll(rememberScrollState())
        ) {
            GreetingBar(
                navController = navController,
                name = firstName,
                greeting = "Senang bertemu lagi hari ini\nApa yang kamu cari?"
            )
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
            RecipeArticleCategory()

            Spacer(modifier = Modifier.padding(start = 16.dp, end = 0.dp))
            isLoading.let {
                if (it) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                listRecipe.forEach { recipe ->
                    RecipeCard(
                        modifier = Modifier.padding(bottom = 8.dp),
                        id = recipe.id,
                        image = recipe.image,
                        title = recipe.name,
                        description = recipe.description,
                        navController = navController
                    )
                }
            }


            Spacer(modifier = Modifier.height(15.dp))
            TopProductCategory()

            LazyRow {
                items(listProduct.size) { index ->
                    val product = listProduct[index]
                    ProductCardBig(
                        modifier = Modifier.padding(end = 8.dp),
                        image = product.image,
                        title = product.name,
                        description = product.description,
                        price = product.price,
                        mainIngredient = product.mainIngredient,
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    JamuInTheme {
        val navController = rememberNavController()
        HomeScreen(navController)
    }
}
