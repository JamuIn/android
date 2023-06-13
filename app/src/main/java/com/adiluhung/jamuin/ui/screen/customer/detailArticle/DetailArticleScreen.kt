package com.adiluhung.jamuin.ui.screen.customer.detailArticle

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adiluhung.jamuin.ui.components.customer.ArticleBanner
import com.adiluhung.jamuin.ui.components.customer.ArticleCard
import com.adiluhung.jamuin.ui.screen.ViewModelFactory
import com.adiluhung.jamuin.ui.theme.JamuInTheme
import com.adiluhung.jamuin.ui.theme.NewWhite

@Composable
fun DetailArticleScreen(
    navController: NavController, articleId: Int,
    viewModel: DetailArticleViewModel = viewModel(
        factory = ViewModelFactory(LocalContext.current)
    )
) {
    viewModel.getDetailArticle(articleId)
    viewModel.checkIsFavorited(articleId)

    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val article = viewModel.article.observeAsState().value
    val isFavorited = viewModel.isFavorited.observeAsState().value
    val message = viewModel.message.observeAsState().value

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .background(color = NewWhite)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            if (article != null) {
                ArticleBanner(navController = navController, image = article.jamu.image)
                Spacer(modifier = Modifier.height(16.dp))
                ArticleCard(
                    title = article.jamu.name,
                    description = article.jamu.description,
                    mainIngredient = listOf(article.jamu.mainIngredient),
                    ingredientItem = article.jamu.ingredients,
                    steps = article.steps,
                    isLiked = isFavorited ?: false,
                    onLikeClicked = {
                        if (isFavorited == true) {
                            viewModel.deleteFavorite(articleId)
                        } else {
                            viewModel.addFavorite(articleId)
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            if (message != null) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleScreenPreview() {
    JamuInTheme {
        DetailArticleScreen(navController = rememberNavController(), articleId = 1)
    }
}