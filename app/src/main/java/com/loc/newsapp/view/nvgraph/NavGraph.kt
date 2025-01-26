package com.loc.newsapp.view.nvgraph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.loc.newsapp.view.home.HomeScreen
import com.loc.newsapp.view.home.HomeViewModel
import com.loc.newsapp.view.onboarding.OnBoardingScreen
import com.loc.newsapp.view.onboarding.OnBoardingViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController(  )

    NavHost(navController = navController, startDestination = startDestination ){


        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ){

            composable(
                route = Route.OnBoardingScreen.route
            ){
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = viewModel:: onEvent
                )
            }
        }



        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ){
            composable(route = Route.NewsNavigatorScreen.route){
                println("Navigating to NewsNavigatorScreen")
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(articles = articles, navigate = {})
            }
        }


    }

}