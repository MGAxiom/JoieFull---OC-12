package com.example.joiefull.ui.navigation

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.rememberPaneExpansionState
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.joiefull.ui.screens.ClothesDetails
import com.example.joiefull.ui.screens.ClothesListScreen
import com.example.joiefull.ui.viewmodel.ClothesViewModel
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun JoieFullApp(navController: NavHostController = rememberNavController()) {
    val viewModel: ClothesViewModel = koinViewModel()
    val configuration = LocalConfiguration.current
    val isTablet = configuration.screenWidthDp >= 600

    if (isTablet) {
        val scaffoldNavigator = rememberListDetailPaneScaffoldNavigator<String>()
        val paneExpansionState = rememberPaneExpansionState()
        val scope = rememberCoroutineScope()

        LaunchedEffect(Unit) {
            paneExpansionState.setFirstPaneProportion(0.5f)
        }

        NavigableListDetailPaneScaffold(
            navigator = scaffoldNavigator,
            paneExpansionState = paneExpansionState,
            listPane = {
                ClothesListScreen(
                    viewModel = viewModel,
                    onClotheTap = { productId ->
                        scope.launch {
                            scaffoldNavigator.navigateTo(
                                ListDetailPaneScaffoldRole.Detail,
                                productId,
                            )
                        }
                    },
                )
            },
            detailPane = {
                val currentDetail = scaffoldNavigator.currentDestination?.contentKey
                if (currentDetail != null) {
                    ClothesDetails(
                        productId = currentDetail,
                        viewModel = viewModel,
                        onBack = { scope.launch { scaffoldNavigator.navigateBack() } },
                    )
                }
            },
        )
    } else {
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
}

object AppDestinations {
    const val CLOTHES_LIST_ROUTE = "clothesListHome"
    const val PRODUCT_ID_ARG = "productId"
    const val CLOTHES_DETAILS_ROUTE_BASE = "clothesDetails"
    const val CLOTHES_DETAILS_ROUTE_PATH = "$CLOTHES_DETAILS_ROUTE_BASE/{$PRODUCT_ID_ARG}"
}
