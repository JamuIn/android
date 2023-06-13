package com.adiluhung.jamuin.ui.screen.customer.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.R
import com.adiluhung.jamuin.ui.components.customer.*
import com.adiluhung.jamuin.ui.screen.ViewModelFactory
import com.adiluhung.jamuin.ui.theme.JamuInTheme
import com.adiluhung.jamuin.ui.theme.NewWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    navController: NavController,
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(
            LocalContext.current
        )
    )
) {
    val listFavorite = viewModel.listFavorite.observeAsState().value ?: emptyList()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarCheckout(
                title = stringResource(id = R.string.favorite)
            )
        },
        content = { paddingValues: PaddingValues ->
            Box(
                modifier = Modifier
                    .background(color = NewWhite)
                    .fillMaxHeight()
                    .padding(
                        top = paddingValues.calculateTopPadding() + 5.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = paddingValues.calculateBottomPadding() + 16.dp
                    )
            ) {
                if (listFavorite.isNotEmpty()) {

                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(listFavorite.size) { index ->
                            RecipeCard(
                                id = listFavorite[index].id,
                                image = listFavorite[index].image,
                                title = listFavorite[index].name,
                                description = listFavorite[index].description,
                                navController = navController,
                            )
                        }
                    }
                } else {
                    Text(
                        text = "Belum ada produk favorit", modifier = Modifier.fillMaxSize(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun FavoriteScreenPreview() {
    JamuInTheme {
        // Create a NavController instance (for preview purposes)
        val navController = rememberNavController()
        FavoriteScreen(navController = navController)
    }
}
