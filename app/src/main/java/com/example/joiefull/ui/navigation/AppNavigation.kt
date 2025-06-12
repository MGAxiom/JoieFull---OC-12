package com.example.joiefull.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.joiefull.model.Category
import com.example.joiefull.model.Product
import com.example.joiefull.ui.screens.ClothesDetails
import com.example.joiefull.ui.screens.ClothesListScreen

@Composable
fun JoieFullApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "clothesHome",
    ) {
        composable("clothesHome") {
            ClothesListScreen(
                onClotheTap = { navController.navigate("details") },
            )
        }
        composable("details") {
            ClothesDetails(
                onBack = { navController.navigateUp() },
                product = Product(
                    id = "1",
                    name = "Veste Urbaine",
                    price = 34.0,
                    strikedPrice = 55.1,
                    rate = 0.0,
                    imageUrl = "",
                    category = Category.TOPS,
                    imageDescription = "",
                )
            )
        }
    }
}
