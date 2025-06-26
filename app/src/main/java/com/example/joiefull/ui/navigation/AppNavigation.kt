package com.example.joiefull.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.joiefull.ui.screens.ClothesDetails
import com.example.joiefull.ui.screens.ClothesListScreen
import com.example.joiefull.ui.viewmodel.ClothesListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun JoieFullApp(navController: NavHostController = rememberNavController()) {
    val viewModel: ClothesListViewModel = koinViewModel()

    NavHost(
        navController = navController,
        startDestination = AppDestinations.CLOTHES_LIST_ROUTE,
    ) {
        composable(AppDestinations.CLOTHES_LIST_ROUTE) {
            ClothesListScreen(
                viewModel = viewModel,
                onClotheTap = { productId ->
                    navController.navigate("${AppDestinations.CLOTHES_DETAILS_ROUTE_BASE}/$productId")
                },
            )
        }
        composable(
            route = AppDestinations.CLOTHES_DETAILS_ROUTE_PATH,
            arguments =
                listOf(
                    navArgument(AppDestinations.PRODUCT_ID_ARG) {
                        type = NavType.StringType
                    },
                ),
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString(AppDestinations.PRODUCT_ID_ARG)
            if (productId != null) {
                ClothesDetails(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                    productId = productId,
                )
            }
        }
    }
}

object AppDestinations {
    const val CLOTHES_LIST_ROUTE = "clothesListHome"
    const val PRODUCT_ID_ARG = "productId"
    const val CLOTHES_DETAILS_ROUTE_BASE = "clothesDetails"
    const val CLOTHES_DETAILS_ROUTE_PATH = "$CLOTHES_DETAILS_ROUTE_BASE/{$PRODUCT_ID_ARG}"
}
