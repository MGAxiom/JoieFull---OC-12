package com.example.joiefull

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.joiefull.model.SplashViewModel
import com.example.joiefull.ui.navigation.JoieFullApp
import com.example.joiefull.ui.theme.JoieFullTheme

class MainActivity : ComponentActivity() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { viewModel.isLoading.value }
        enableEdgeToEdge()

        setContent {
            JoieFullTheme {
                JoieFullApp()
            }
        }
    }
}
