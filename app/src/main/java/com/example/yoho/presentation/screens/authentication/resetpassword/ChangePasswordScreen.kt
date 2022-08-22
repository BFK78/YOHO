package com.example.yoho.presentation.screens.authentication.resetpassword

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.yoho.R
import com.example.yoho.presentation.screens.authentication.util.checkPassword
import com.example.yoho.presentation.screens.authentication.viewmodel.AuthViewModel
import com.example.yoho.presentation.screens.common.AppButton
import com.example.yoho.presentation.screens.common.LoadingDialog
import com.example.yoho.presentation.screens.profile.viewmodel.ProfileViewModel
import com.example.yoho.presentation.util.screens.Screens
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ChangePassword(
    navHostController: NavHostController,
    userId: String?,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {

    val state = profileViewModel.passwordState

    val scaffoldState = rememberScaffoldState()

    val verticalScroll = rememberScrollState()
    
    val scope = rememberCoroutineScope()
    
    val openDialog = remember {
        mutableStateOf(false)
    }

    val password = remember {
        mutableStateOf("")
    }

    val passwordError = remember {
        mutableStateOf(false)
    }

    val passwordAlpha = animateFloatAsState(targetValue = if (passwordError.value) 1f else 0f)

    val passwordMessage = remember {
        mutableStateOf("")
    }

    val confirmPassword = remember {
        mutableStateOf("")
    }

    val confirmPasswordError = remember {
        mutableStateOf(false)
    }

    val confirmPasswordAlpha = animateFloatAsState(targetValue = if (confirmPasswordError.value) 1f else 0f)

    val confirmPasswordMessage = remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = state.value.isLoading) {
        openDialog.value = state.value.isLoading
        if (state.value.data?.status == true && !state.value.isLoading) {
            navHostController.navigate(Screens.HomeScreen.route)
        }
    }

    LaunchedEffect(key1 = state.value.isLoading) {
        state.value.data?.let {
            if (!it.status) {
                scaffoldState.snackbarHostState.showSnackbar(it.message)
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Create New Password"
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow back"
                        )
                    }
                },
                backgroundColor = Color.White,
                elevation = 0.dp
            )
        }
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            LoadingDialog(openDialog = openDialog)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = verticalScroll),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                
                Image(
                    painter = painterResource(id = R.drawable.ic_cp_bg),
                    contentDescription = "Change Password logo",
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Create Your New Password",
                    modifier = Modifier
                        .padding(horizontal = 24.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    value = password.value,
                    onValueChange = {
                        password.value = it
                        if (password.value.checkPassword()) {
                            passwordError.value = false
                        } else {
                            passwordError.value = true
                            passwordMessage.value =
                                "Password should have 8 characters and a number."
                        }
                    },
                    label = {
                        Text(
                            text = "Password",
                            color = Color.Black.copy(alpha = .3f)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = "Lock Icon",
                            tint = Color.Black.copy(alpha = 0.3f)
                        )
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "Info Icon",
                            tint = MaterialTheme.colors.error,
                            modifier = Modifier.alpha(
                                alpha = passwordAlpha.value
                            )
                        )
                    },
                    isError = passwordError.value,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        backgroundColor = Color.Gray.copy(alpha = 0.1f)
                    ),
                )

                AnimatedVisibility(visible = passwordError.value) {
                    Text(
                        text = passwordMessage.value,
                        color = MaterialTheme.colors.error,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    value = confirmPassword.value,
                    onValueChange = {
                        confirmPassword.value = it
//                        confirmPasswordError.value = confirmPassword.value != password.value
                        if (confirmPassword.value.checkPassword()) {
                            confirmPasswordError.value = false
                        } else {
                            confirmPasswordError.value = true
                            confirmPasswordMessage.value =
                                "Password should have 8 characters and a number."
                        }
                    },
                    label = {
                        Text(
                            text = "Password",
                            color = Color.Black.copy(alpha = .3f)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = "Lock Icon",
                            tint = Color.Black.copy(alpha = 0.3f)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "Info Icon",
                            tint = MaterialTheme.colors.error,
                            modifier = Modifier.alpha(
                                alpha = confirmPasswordAlpha.value
                            )
                        )
                    },
                    isError = confirmPasswordError.value,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        backgroundColor = Color.Gray.copy(alpha = 0.1f)
                    ),
                )

                AnimatedVisibility(visible = confirmPasswordError.value) {
                    Text(
                        text = confirmPasswordMessage.value,
                        color = MaterialTheme.colors.error,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                AppButton(
                    text = "Continue",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .clip(shape = RoundedCornerShape(25.dp)),
                ) {
                    if (!passwordError.value && !confirmPasswordError.value && confirmPassword.value == password.value) {
                        profileViewModel.updatePassword(userId = userId!!, password = password.value)
//                        navHostController.navigate(Screens.HomeScreen.route)
                    } else {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "Password Error")
                        }
                    }
                }

            }
        }
    }
}