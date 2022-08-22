package com.example.yoho.presentation.screens.authentication.resetpassword

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.yoho.R
import com.example.yoho.presentation.screens.common.AppButton
import com.example.yoho.presentation.screens.common.LoadingDialog
import com.example.yoho.presentation.screens.profile.viewmodel.ProfileViewModel
import com.example.yoho.presentation.util.constants.Constants
import com.example.yoho.presentation.util.screens.Screens
import kotlinx.coroutines.launch

sealed class Chip(val value: String, var notification: String) {
    object Sms: Chip(value = Constants.VIA_SMS, notification = "")
    object Email: Chip(value = Constants.VIA_EMAIL, notification =  "")
}

fun getAllChip() = listOf(Chip.Sms, Chip.Email)

fun getChip(value: String): Chip {

    getAllChip().forEach {
        if (it.value == value) {
            return it
        }
    }
    return Chip.Sms
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ResetPasswordScreen(
    navHostController: NavHostController,
    email: String?,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {

    val userState = profileViewModel.userState

    val openDialog = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = true) {
        profileViewModel.getUser(email = email!!)
    }

    val list = getAllChip()

    LaunchedEffect(key1 = userState.value.isLoading) {
        if (userState.value.data?.status == true && !userState.value.isLoading) {
            list[0].notification = userState.value.data?.data?.user!!.number
            list[1].notification = userState.value.data?.data?.user!!.email
            openDialog.value = false
        }

        if (userState.value.message != "") {
            navHostController.popBackStack()
        }

    }

    val selectedChip: MutableState<Chip?> = remember { mutableStateOf(null) }

    val scaffoldState = rememberScaffoldState()

    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Forgot Password")
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow Back")
                    }
                },
                backgroundColor = Color.White,
                elevation = 0.dp
            )
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (openDialog.value) {
                LoadingDialog(openDialog = openDialog)
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(state = rememberScrollState())
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.ic_fg_bg1),
                        contentDescription = "Background design Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Select which contact details should we use to reset your password",
                        modifier = Modifier
                            .padding(horizontal = 24.dp),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ChipGroup(
                        selectedChip.value,
                        list
                    ) {
                        selectedChip.value = getChip(it)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    AppButton(
                        text = "Continue",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                            .clip(shape = RoundedCornerShape(25.dp)),
                    ) {
                        if (selectedChip.value?.value == Constants.VIA_SMS) {
                            navHostController.navigate(Screens.OtpVerificationScreen.route + "/${userState.value.data?.data?.userId}/nothing")
                        } else if (selectedChip.value?.value == Constants.VIA_EMAIL) {
                            navHostController.navigate(Screens.EmailVerificationScreen.route + "/${userState.value.data?.data?.userId}")
                        } else {
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Please select a way to verify"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ForgotPasswordBox(
   selected: Boolean,
    text: String,
    icon: ImageVector,
    notification: String,
    onSelectionChanged: (String) -> Unit
) {

    val borderColor = animateColorAsState(targetValue = if (selected) MaterialTheme.colors.primary.copy(.7f) else Color.Gray.copy(alpha = 0.1f))

    val shadow = animateDpAsState(targetValue = if (selected) 8.dp else 4.dp)

    Surface(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .border(
                color = borderColor.value,
                shape = RoundedCornerShape(25.dp),
                width = 2.dp
            )
            .fillMaxWidth(),
        elevation = shadow.value,
        shape = RoundedCornerShape(25.dp)
    ) {


        Box(
            modifier = Modifier
                .toggleable(
                    value = selected,
                    onValueChange = {
                        onSelectionChanged(text)
                    }
                )
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(20.dp)
                        .clip(CircleShape)
                        .background(color = Color.Gray.copy(0.1f))
                ) {

                    Icon(
                        imageVector = icon,
                        contentDescription = "Notification Icon",
                        tint = MaterialTheme.colors.primary
                    )

                }
                Column {
                    Text(
                        text = text,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    if (text == Constants.VIA_SMS) {
                        Text(
                            text = "+91 ${notification.substring(0, 2)}******${notification.substring(notification.length - 2, notification.length)}",
                            fontWeight = FontWeight.Bold
                        )
                    } else {
                        Text(
                            text = "${notification.substring(0, 2)}*******${notification.substring(notification.length - 5, notification.length)}",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun ChipGroup(
    selectedChip: Chip?,
    chipList: List<Chip>,
    onSelectionChanged: (String) -> Unit
) {

    Column(
        modifier = Modifier
    ) {
        repeat(getAllChip().size) {
            ForgotPasswordBox(
                selected = chipList[it] == selectedChip,
                text = chipList[it].value,
                icon = Icons.Default.Notifications,
                notification = chipList[it].notification) { value ->
                onSelectionChanged(value)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}