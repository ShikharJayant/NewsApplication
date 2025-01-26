package com.loc.newsapp.view.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import com.loc.newsapp.R
import com.loc.newsapp.viewmodel.model1.Article
//import com.loc.newsapp.presentation.common.SearchBar
import com.loc.newsapp.view.nvgraph.Route
import com.loc.newsapp.view.onboarding.Dimens.MediumPadding1
import com.loc.newsapp.view.common.ArticlesList
import com.loc.newsapp.view.common.SearchBar


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(articles: LazyPagingItems<Article>, navigate:(String) -> Unit) {

    val viewModel: HomeViewModel = hiltViewModel()
    val topHeadlines by viewModel.topHeadlines.collectAsState()

    val titles = remember {
        derivedStateOf {
            topHeadlines.joinToString(separator = " \uD83D\uDFE5 ") { it.title }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.newlogo1),
            contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .height(30.dp)
                .padding(horizontal = MediumPadding1)
        )


        Spacer(modifier = Modifier.height(MediumPadding1))

        SearchBar(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = {
                      navigate(Route.SearchScreen.route)
            },
            onSearch = {}

        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        Text(
            text = titles.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MediumPadding1)
                .basicMarquee(),
            fontSize = 13.sp,
            color = colorResource(id = R.color.placeholder)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        ArticlesList(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            articles = articles,
            onClick = {
                navigate(Route.DetailsScreen.route)
            }
        )
    }
}