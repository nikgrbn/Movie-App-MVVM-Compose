package com.example.shutterflyassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shutterflyassignment.ui.theme.ShutterflyAssignmentTheme
import com.example.shutterflyassignment.navigation.Routes
import com.example.shutterflyassignment.ui.genres.GenresScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShutterflyAssignmentTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Routes.GENRES) {
                    composable(Routes.GENRES) {
                        GenresScreen()
                    }
                }
            }
        }
    }
}