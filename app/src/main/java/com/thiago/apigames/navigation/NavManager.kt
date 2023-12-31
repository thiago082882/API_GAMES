package com.thiago.apigames.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.thiago.apigames.viewmodel.GamesViewModel
import com.thiago.apigames.views.DetailView
import com.thiago.apigames.views.HomeView
import com.thiago.apigames.views.SearchGameView


@Composable
fun NavManager(viewModel: GamesViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeView(viewModel, navController)
        }
        composable("DetailView/{id}", arguments = listOf(
            navArgument("id"){ type = NavType.IntType }
        )){
            val id = it.arguments?.getInt("id") ?: 0
            DetailView(viewModel = viewModel, navController = navController,id)
        }
      composable("SearchGameView"){
          SearchGameView(viewModel = viewModel, navController = navController)
          
      }

    }
}


