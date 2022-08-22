package com.example.yoho.presentation.navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.yoho.presentation.screens.authentication.login.LoginScreen
import com.example.yoho.presentation.screens.authentication.register.RegisterScreen
import com.example.yoho.presentation.screens.authentication.resetpassword.ChangePassword
import com.example.yoho.presentation.screens.authentication.resetpassword.ResetPasswordScreen
import com.example.yoho.presentation.screens.authentication.verifyotp.emailverification.EmailVerificationScreen
import com.example.yoho.presentation.screens.authentication.verifyotp.numberverification.VerifyOtpScreen
import com.example.yoho.presentation.screens.main.home.HomeScreen
import com.example.yoho.presentation.screens.main.chat.ChatScreen
import com.example.yoho.presentation.screens.main.contacts.ContactScreen
import com.example.yoho.presentation.screens.main.home.actionscreens.JoinMeetingScreen
import com.example.yoho.presentation.screens.main.home.actionscreens.MeetingHistoryScreen
import com.example.yoho.presentation.screens.main.home.actionscreens.ScheduleMeetingScreen
import com.example.yoho.presentation.screens.main.scheduled.ScheduledScreen
import com.example.yoho.presentation.screens.main.scheduled.actionscreens.MeetingDetailsScreen
import com.example.yoho.presentation.screens.main.settings.SettingsScreen
import com.example.yoho.presentation.screens.profile.authentication.createpin.CreatePinScreen
import com.example.yoho.presentation.screens.profile.authentication.fillprofile.FillProfileScreen
import com.example.yoho.presentation.util.constants.Constants.AUTHENTICATION_ROUTE
import com.example.yoho.presentation.util.constants.Constants.MAIN_ROUTE
import com.example.yoho.presentation.util.constants.Constants.WELCOME_ROUTE
import com.example.yoho.presentation.util.screens.Screens


//Welcome Navigation
fun NavGraphBuilder.welcomeNavigation(
    navHostController: NavHostController
) {

    navigation(
        startDestination = Screens.SplashScreen.route,
        route = WELCOME_ROUTE
    ) {

        composable(
            route = Screens.SplashScreen.route
        ) {

        }

    }

}

//Authentication Navigation
fun NavGraphBuilder.authenticationNavigation(
    navHostController: NavHostController
) {

    navigation(
        startDestination = Screens.LoginScreen.route,
        route = AUTHENTICATION_ROUTE,
    ) {

        composable(
            route = Screens.LoginScreen.route
        ) {
            LoginScreen(navHostController = navHostController)
        }

        composable(
            route = Screens.RegistrationScreen.route
        ) {
            RegisterScreen(navHostController = navHostController)
        }

        composable(
            route = Screens.OtpVerificationScreen.route + "/{id}/{name}"
        ) {
            val userId = it.arguments?.getString("id")
            val name = it.arguments?.getString("name")
            VerifyOtpScreen(navHostController = navHostController, userId = userId, name = name)
        }

        composable(
            route = Screens.ProfileFillScreen.route + "/{id}"
        ) {
            val userId = it.arguments?.getString("id")
            FillProfileScreen(navHostController = navHostController, userId = userId)
        }

        composable(
            route = Screens.CreatePinScreen.route + "/{id}"
        ) {
            val userId = it.arguments?.getString("id")
            CreatePinScreen(navHostController = navHostController, userId = userId!!)
        }

        composable(route = Screens.ForgotPassword.route + "/{email}") {
            val email = it.arguments?.getString("email")
            ResetPasswordScreen(navHostController = navHostController, email = email)
        }

        composable(route = Screens.EmailVerificationScreen.route + "/{id}") {
            val userId = it.arguments?.getString("id")
            EmailVerificationScreen(navHostController = navHostController, userId = userId)
        }

        composable(route = Screens.ChangePasswordScreen.route + "/{id}") {
            val userId = it.arguments?.getString("id")
            ChangePassword(navHostController = navHostController, userId = userId)
        }
    }
}

//Main Navigation Routes
fun NavGraphBuilder.mainNavigation(
    navHostController: NavHostController
) {
    navigation(
        startDestination = Screens.HomeScreen.route,
        route = MAIN_ROUTE
    ) {

        //#1
        composable(route = Screens.HomeScreen.route) {
            HomeScreen(navHostController = navHostController)
        }

        //Home Screen Action Screens
        composable(route = Screens.JoinMeetingScreen.route) {
            JoinMeetingScreen()
        }

        composable(route = Screens.MeetingHistoryScreen.route) {
            MeetingHistoryScreen(navHostController = navHostController)
        }

        composable(route = Screens.ScheduleMeetingScreen.route) {
            ScheduleMeetingScreen()
        }

        //#2
        composable(route = Screens.ScheduledScreen.route) {
            ScheduledScreen(navHostController = navHostController)
        }

        //Scheduled Screen Action Screens
        composable(route = Screens.MeetingDetailsScreen.route) {
            MeetingDetailsScreen()
        }

        //#3
        composable(route = Screens.ChatScreen.route) {
            ChatScreen(navHostController = navHostController)
        }

        //#4
        composable(route = Screens.ContactScreen.route) {
            ContactScreen(navHostController = navHostController)
        }

        //#5
        composable(route = Screens.SettingsScreen.route) {
            SettingsScreen(navHostController = navHostController)
        }

    }
}

//Bottom Navigation Bar
@Composable
fun BottomNavigationBar(
    navHostController: NavHostController
) {

    val items = listOf(
        Screens.HomeScreen,
        Screens.ScheduledScreen,
        Screens.ChatScreen,
        Screens.ContactScreen,
        Screens.SettingsScreen
    )

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Blue
    ) {

        items.forEach { it ->

//            navHostController.currentDestination?.route!! == it.route
            BottomNavigationItem(
                icon = { Icon(imageVector = it.imageIcon!!, contentDescription = "",) },
                selected = true,
                label = {
                    Text(text = it.title)
                },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = Color.Gray,
                alwaysShowLabel = false,
                onClick = {
                    navHostController.navigate(it.route) {
                        navHostController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}