package com.example.yoho.presentation.screens.authentication.verifyotp.numberverification

import android.annotation.SuppressLint
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.yoho.presentation.screens.authentication.viewmodel.AuthViewModel
import com.example.yoho.presentation.screens.common.AppButton
import com.example.yoho.presentation.screens.common.LoadingDialog
import com.example.yoho.presentation.screens.common.OtpScreen
import com.example.yoho.presentation.util.constants.Constants
import com.example.yoho.presentation.util.screens.Screens
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun VerifyOtpScreen(
    navHostController: NavHostController,
    userId: String?,
    name: String?,
    authViewModel: AuthViewModel = hiltViewModel()
) {

    val state = authViewModel.verifyOtpResponse

    val pinCode = remember {
        mutableStateOf("")
    }

    val openDialog = remember {
        mutableStateOf(false)
    }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    //Effect handlers
    LaunchedEffect(key1 = state.value.isLoading) {
        openDialog.value = state.value.isLoading
        if (state.value.data?.status == true && !state.value.isLoading) {
            if (name == Constants.FILL_PROFILE) {
            navHostController.navigate(Screens.CreatePinScreen.route + "/${userId}")
            } else {
                navHostController.navigate(Screens.ChangePasswordScreen.route + "/${userId}")
            }
        }
    }

    LaunchedEffect(key1 = state.value.isLoading) {
        state.value.data?.let {
            if (!it.status) {
                scaffoldState.snackbarHostState.showSnackbar(it.message)
            }
        }
    }
    LaunchedEffect(key1 = true) {
        authViewModel.sendOtp(userId = userId!!)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "OTP Verification")
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

            LoadingDialog(openDialog = openDialog)

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
                    text = "An OTP has been sent to your number",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Enter OTP",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(48.dp))

                OtpScreen() {
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
                        authViewModel.verifyOtp(userId = userId!!, pinCode.value)
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