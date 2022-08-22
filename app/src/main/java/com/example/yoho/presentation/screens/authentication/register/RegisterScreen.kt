package com.example.yoho.presentation.screens.authentication.register

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.yoho.R
import com.example.yoho.data.remote.model.AuthUser
import com.example.yoho.presentation.screens.common.AppButton
import com.example.yoho.presentation.screens.authentication.util.checkEmail
import com.example.yoho.presentation.screens.authentication.util.checkPassword
import com.example.yoho.presentation.screens.authentication.viewmodel.AuthViewModel
import com.example.yoho.presentation.screens.common.LoadingDialog
import com.example.yoho.presentation.util.screens.Screens
import com.example.yoho.ui.theme.rubik
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegisterScreen(
    navHostController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel(),
) {

    val state = authViewModel.registerResponse

    val email = remember {
        mutableStateOf("")
    }

    val password = remember {
        mutableStateOf("")
    }

    val emailError = remember {
        mutableStateOf(false)
    }

    val passwordError = remember {
        mutableStateOf(false)
    }

    val emailMessage = remember {
        mutableStateOf("")
    }

    val passwordMessage = remember {
        mutableStateOf("")
    }

    val openDialog = remember {
        mutableStateOf(false)
    }

    val errorMessage = remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState()

    val emailAlpha = animateFloatAsState(targetValue = if (emailError.value) 1f else 0f)

    val passwordAlpha = animateFloatAsState(targetValue = if (passwordError.value) 1f else 0f)

    LaunchedEffect(key1 = state.value.isLoading) {
        openDialog.value = state.value.isLoading
        if (state.value.data?.status == true && !state.value.isLoading) {
            navHostController.navigate(Screens.ProfileFillScreen.route + "/${(state.value.data!!.data?.userId)}")
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
        modifier = Modifier
            .fillMaxSize(),
        backgroundColor = Color.White,
        topBar = {
            Row {
                IconButton(onClick = {
                    navHostController.popBackStack()
                    TODO("how to do it correctly")
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back Arrow"
                    )
                }
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            LoadingDialog(openDialog = openDialog)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "Application Logo",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(
                        size = 96.dp,
                    )
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Create New Account",
                    fontSize = 26.sp,
                    fontFamily = rubik,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    value = email.value,
                    onValueChange = {
                        email.value = it
                        if (email.value.checkEmail()) {
                            emailError.value = false
                        } else {
                            emailError.value = true
                            emailMessage.value = "Please enter a valid email."
                        }
                    },
                    label = {
                        Text(
                            text = "Email",
                            color = Color.Black.copy(alpha = 0.3f)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Email,
                            contentDescription = "Mail Icon",
                            tint = Color.Black.copy(alpha = 0.3f)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "Info Icon",
                            tint = MaterialTheme.colors.error,
                            modifier = Modifier.alpha(
                                alpha = emailAlpha.value
                            )
                        )
                    },
                    isError = emailError.value,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        backgroundColor = Color.Gray.copy(alpha = 0.1f)
                    )
                )

                AnimatedVisibility(visible = emailError.value) {
                    Text(
                        text = emailMessage.value,
                        color = MaterialTheme.colors.error,
                        fontSize = 12.sp
                    )
                }

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
                            passwordMessage.value = "Please enter a valid email."
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

                AppButton(
                    text = "Sign up",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .clip(shape = RoundedCornerShape(25.dp)),

                    ) {

                    if (!emailError.value && !passwordError.value && email.value != "" && password.value != "") {
                        val user = AuthUser(
                            email = email.value,
                            password = password.value
                        )
                        authViewModel.registerUser(user = user)
                    } else {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Invalid inputs")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Divider(
                        color = Color.Black.copy(
                            alpha = 0.1f
                        ),
                        thickness = 1.dp,
                        modifier = Modifier
                            .weight(1f)
                    )

                    Text(
                        text = "or continue with",
                        modifier = Modifier
                            .padding(horizontal = 8.dp),
                        color = Color.Black.copy(alpha = .5f)
                    )

                    Divider(
                        color = Color.Black.copy(
                            alpha = 0.1f
                        ),
                        thickness = 1.dp,
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row {

                    Box(
                        modifier = Modifier
                            .clip(
                                shape = RoundedCornerShape(
                                    10.dp
                                )
                            )
                            .border(
                                width = 1.dp,
                                color = Color.Gray.copy(
                                    alpha = .3f
                                ),
                                shape = RoundedCornerShape(10.dp)
                            )
                    ) {

                        IconButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_facebook),
                                contentDescription = "Sign up with facebook",
                                tint = Color.Unspecified,
                                modifier = Modifier.size(
                                    24.dp
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Box(
                        modifier = Modifier
                            .clip(
                                shape = RoundedCornerShape(
                                    10.dp
                                )
                            )
                            .border(
                                width = 1.dp,
                                color = Color.Gray.copy(
                                    alpha = 0.3f
                                ),
                                shape = RoundedCornerShape(
                                    10.dp
                                )
                            )
                    ) {

                        IconButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .padding(horizontal = 12.dp, vertical = 2.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_google),
                                contentDescription = "Sign up with google",
                                tint = Color.Unspecified,
                                modifier = Modifier.size(
                                    24.dp
                                )
                            )
                        }

                    }

                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Already have an account?",
                        color = Color.Black.copy(alpha = 0.3f)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    ClickableText(text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Sign in")
                        }
                    },
                        onClick = {
                            navHostController.popBackStack()
                            navHostController.navigate(Screens.LoginScreen.route)
                        }
                    )
                }
            }
        }
    }
}

