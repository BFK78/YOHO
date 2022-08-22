package com.example.yoho.presentation.util.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val title: String, val route: String, val imageIcon: ImageVector? = null) {

    //Welcome Screens
    object SplashScreen: Screens(title = "Splash Screen", route = "splash-screen")

    //Authentication Screens
    object LoginScreen: Screens(title = "Login Screen", route = "login-screen")
    object RegistrationScreen: Screens(title = "Registration Screen", route = "registration-screen")
    object OtpVerificationScreen: Screens(title = "Otp Verification", route = "otp-verification-screen")
    object EmailVerificationScreen: Screens(title = "Email Verification", route = "email-verification-screen")
    object ChangePasswordScreen: Screens(title = "Change Password", route = "change-password-screen")

    //Profile Authentication Screens
    object ProfileFillScreen: Screens(title = "Profile Fill Screen", route = "profile-fill-screen")
    object CreatePinScreen: Screens(title = "Create Pin Screen", route = "create-pin-screen")
    object ForgotPassword: Screens(title = "Forgot Password Screen", route = "forgot-password-screen")

    //Main Screens
    object HomeScreen: Screens(title = "Home", route = "home-screen", imageIcon = Icons.Rounded.Home)
    object ScheduledScreen: Screens(title = "Schedule", route = "scheduled-screen",imageIcon = Icons.Rounded.DateRange)
    object ChatScreen: Screens(title = "Chats", route = "chat-screen", imageIcon = Icons.Rounded.Email)
    object ContactScreen: Screens(title = "Contacts", route = "contacts-screen", imageIcon = Icons.Rounded.Call)
    object SettingsScreen: Screens(title = "Settings", route = "settings-screen", imageIcon = Icons.Rounded.Settings)

    //MainScreen's Action Screens
    object MeetingHistoryScreen: Screens(title = "Meeting History", route = "meeting-history-screen")
    object JoinMeetingScreen: Screens(title = "Join Meeting", route = "join-meeting-screen")
    object ScheduleMeetingScreen: Screens(title = "Schedule Meeting", route = "schedule-meeting-screen")

    //Scheduled Action Screen
    object MeetingDetailsScreen: Screens(title = "Meeting Details", route = "meeting-detail-screen")

}
