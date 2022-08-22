package com.example.yoho.presentation.screens.profile.authentication.createpin

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.yoho.presentation.screens.common.AppButton
import com.example.yoho.presentation.screens.common.PinScreen
import com.example.yoho.presentation.screens.profile.viewmodel.ProfileViewModel
import com.example.yoho.presentation.util.screens.Screens
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CreatePinScreen(
    navHostController: NavHostController,
    userId: String,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {

    val openDialog = remember {
        mutableStateOf(false)
    }

    val pinCode = remember {
        mutableStateOf("")
    }

    val state = profileViewModel.createPinState

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = state.value.isLoading) {
        openDialog.value = state.value.isLoading
        if (state.value.data?.status == true && !state.value.isLoading) {
            navHostController.navigate(Screens.HomeScreen.route)
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Create New Pin")
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Arrow Back"
                        )
                    }
                },
                backgroundColor = Color.White,
                elevation = 0.dp
            )
        }
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(
                        state = rememberScrollState()
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Add a PIN number to make your account\n more secure.",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(48.dp))

                PinScreen() {
                    pinCode.value = it
                }

                Spacer(modifier = Modifier.height(48.dp))

                AppButton(
                    text = "Continue",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .clip(shape = RoundedCornerShape(25.dp)),
                ) {
                    if (pinCode.value.isNotEmpty()) {
                        profileViewModel.createPin(userId = userId, pin = pinCode.value)
                    } else {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Invalid inputs")
                        }
                    }
                }
            }
        }
    }
}