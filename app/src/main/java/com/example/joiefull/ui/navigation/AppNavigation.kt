package com.example.joiefull.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.joiefull.ui.screens.ClothesDetails
import com.example.joiefull.ui.screens.ClothesListScreen

@Composable
fun JoieFullApp(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.CLOTHES_LIST_ROUTE,
    ) {
        composable(AppDestinations.CLOTHES_LIST_ROUTE) {
            ClothesListScreen(
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
                    onBack = { navController.popBackStack() },
                    productId = productId,
                )
            }
        }
    }
}

object AppDestinations {
    const val CLOTHES_LIST_ROUTE = "clothesListHome" // String route

    const val PRODUCT_ID_ARG = "productId"
    const val CLOTHES_DETAILS_ROUTE_BASE = "clothesDetails"

    // Route for details screen, with a placeholder for the argument
    const val CLOTHES_DETAILS_ROUTE_PATH = "$CLOTHES_DETAILS_ROUTE_BASE/{$PRODUCT_ID_ARG}"
}
