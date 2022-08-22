package com.example.yoho

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.yoho.presentation.navigation.authenticationNavigation
import com.example.yoho.presentation.navigation.mainNavigation
import com.example.yoho.presentation.screens.main.home.HomeScreen
import com.example.yoho.presentation.screens.main.home.actionscreens.JoinMeetingScreen
import com.example.yoho.presentation.screens.main.home.actionscreens.ScheduleMeetingScreen
import com.example.yoho.presentation.screens.main.scheduled.ScheduledScreen
import com.example.yoho.presentation.screens.main.scheduled.actionscreens.MeetingDetailsScreen
import com.example.yoho.presentation.util.constants.Constants.AUTHENTICATION_ROUTE
import com.example.yoho.ui.theme.YOHOTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YOHOTheme {

                val navHostController = rememberNavController()

                val openDialog = remember {
                    mutableStateOf(true)
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        ScheduleMeetingScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun ApplicationNavigation(
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        startDestination = AUTHENTICATION_ROUTE
    ) {

        authenticationNavigation(navHostController)
        mainNavigation(navHostController)
    }

}

