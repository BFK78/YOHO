package com.example.yoho.presentation.screens.starter.splash

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.yoho.presentation.util.screens.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navHostController: NavHostController
) {

    LaunchedEffect(key1 = true) {

        delay(3000)
        navHostController.navigate(route = Screens.LoginScreen.route)

    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {


    }

}