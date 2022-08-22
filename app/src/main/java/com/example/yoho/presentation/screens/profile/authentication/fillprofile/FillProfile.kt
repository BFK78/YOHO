package com.example.yoho.presentation.screens.profile.authentication.fillprofile

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.yoho.R
import com.example.yoho.domain.model.local.User
import com.example.yoho.presentation.screens.authentication.util.checkDOB
import com.example.yoho.presentation.screens.common.AppButton
import com.example.yoho.presentation.screens.authentication.util.checkNumber
import com.example.yoho.presentation.screens.authentication.util.checkUsername
import com.example.yoho.presentation.screens.common.LoadingDialog
import com.example.yoho.presentation.screens.profile.viewmodel.ProfileViewModel
import com.example.yoho.presentation.util.constants.Constants
import com.example.yoho.presentation.util.screens.Screens
import com.google.accompanist.permissions.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class, ExperimentalCoilApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FillProfileScreen(
    navHostController: NavHostController,
    userId: String?,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {

    val scaffoldState = rememberScaffoldState()

    val imageUri = remember {
        mutableStateOf<Uri?>(null)
    }

    val readStoragePermissionState =
        rememberPermissionState(permission = android.Manifest.permission.READ_EXTERNAL_STORAGE)

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner) {

        val eventObserver = LifecycleEventObserver { _, event ->

            when (event) {
                Lifecycle.Event.ON_START -> {
                    readStoragePermissionState.launchPermissionRequest()
                }
                else -> {

                }
            }
        }

        lifecycleOwner.lifecycle.addObserver(eventObserver)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(eventObserver)
        }
    }

    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            imageUri.value = it
        }
    )

    val settings = remember {
        mutableStateOf(false)
    }

    if (settings.value) {
        context.startActivity(Intent(Settings.ACTION_SETTINGS))
    }

    val fullName = remember {
        mutableStateOf("")
    }

    val nickName = remember {
        mutableStateOf("")
    }

    val number = remember {
        mutableStateOf("")
    }

    val fullNameError = remember {
        mutableStateOf(false)
    }

    val nickNameError = remember {
        mutableStateOf(false)
    }

    val dobError = remember {
        mutableStateOf(false)
    }

    val numberError = remember {
        mutableStateOf(false)
    }

    val fullNameMessage = remember {
        mutableStateOf("")
    }

    val nickNameMessage = remember {
        mutableStateOf("")
    }

    val dobMessage = remember {
        mutableStateOf("")
    }

    val numberMessage = remember {
        mutableStateOf("")
    }

    val date = remember {
        mutableStateOf("")
    }

    val datePickerDialog = DatePickerDialog(context,
        { _: DatePicker, year: Int, month: Int, day: Int ->

            date.value = "$day/${month + 1}/$year"
            dobError.value = !date.value.checkDOB()

            dobMessage.value = "You should be more than 10 years."

        }, 1999, 11, 24
    )

    val fullNameAlpha = animateFloatAsState(targetValue = if (fullNameError.value) 1f else 0f)

    val nickNameAlpha = animateFloatAsState(targetValue = if (nickNameError.value) 1f else 0f)

    val numberAlpha = animateFloatAsState(targetValue = if (numberError.value) 1f else 0f)

    val state = profileViewModel.profileUpdateState

    val profilePainter = rememberImagePainter(data = imageUri.value ?: imageUri) {
        crossfade(600)
        error(R.drawable.ic_profile)
    }

    val openDialog = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = state.value.isLoading) {
        openDialog.value = state.value.isLoading
        if (state.value.data?.status == true && !state.value.isLoading) {
            navHostController.navigate(Screens.OtpVerificationScreen.route + "/${userId}/${Constants.FILL_PROFILE}")
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
                    Text(text = "Fill Your Profile")
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back Arrow"
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
                    .verticalScroll(state = rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Spacer(modifier = Modifier.height(32.dp))

                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(shape = CircleShape)
                ) {

                    Image(
                        painter = profilePainter,
                        contentDescription = "Profile Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    IconButton(
                        onClick = {

                            when (readStoragePermissionState.status) {

                                PermissionStatus.Granted -> {
                                    launcher.launch("image/*")
                                }

                                is PermissionStatus.Denied -> {
                                    if ((readStoragePermissionState.status as PermissionStatus.Denied).shouldShowRationale) {
                                        Toast.makeText(context,
                                            "Hey you rejected me",
                                            Toast.LENGTH_SHORT).show()
                                        readStoragePermissionState.launchPermissionRequest()
                                    } else {
                                        scope.launch {
                                            val snackBarResult =
                                                scaffoldState.snackbarHostState.showSnackbar(
                                                    message = "You need to grant permission through settings",
                                                    actionLabel = "Settings"
                                                )
                                            when (snackBarResult) {
                                                SnackbarResult.ActionPerformed -> {
                                                    settings.value = !settings.value
                                                }
                                                else -> {}
                                            }
                                        }
                                    }
                                }
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(color = MaterialTheme.colors.primary)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Edit Profile",
                            tint = Color.White,
                        )
                    }

                }

                Spacer(modifier = Modifier.height(32.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    value = fullName.value,
                    onValueChange = {
                        fullName.value = it
                        if (fullName.value.checkUsername()) {
                            fullNameError.value = false
                        } else {
                            fullNameError.value = true
                            fullNameMessage.value = "Full name must have more than 5 characters"
                        }
                    },
                    label = {
                        Text(
                            text = "Full Name",
                            color = Color.Black.copy(alpha = 0.3f)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "Info Icon",
                            tint = MaterialTheme.colors.error,
                            modifier = Modifier.alpha(
                                alpha = fullNameAlpha.value
                            )
                        )
                    },
                    isError = fullNameError.value,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        backgroundColor = Color.Gray.copy(alpha = 0.1f),
                    )
                )

                AnimatedVisibility(visible = fullNameError.value) {
                    Text(
                        text = fullNameMessage.value,
                        color = MaterialTheme.colors.error,
                        fontSize = 12.sp
                    )
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    value = nickName.value,
                    onValueChange = {
                        nickName.value = it
                        if (nickName.value.checkUsername()) {
                            nickNameError.value = false
                        } else {
                            nickNameError.value = true
                            nickNameMessage.value = "Nick name must have more than 5 characters"
                        }
                    },
                    label = {
                        Text(
                            text = "Nick Name",
                            color = Color.Black.copy(alpha = 0.3f)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "Info Icon",
                            tint = MaterialTheme.colors.error,
                            modifier = Modifier.alpha(
                                alpha = nickNameAlpha.value
                            )
                        )
                    },
                    isError = nickNameError.value,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        backgroundColor = Color.Gray.copy(alpha = 0.1f),
                    )
                )

                AnimatedVisibility(visible = nickNameError.value) {
                    Text(
                        text = nickNameMessage.value,
                        color = MaterialTheme.colors.error,
                        fontSize = 12.sp
                    )
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    value = date.value,
                    onValueChange = {
                    },
                    label = {
                        Text(
                            text = "dob",
                            color = Color.Black.copy(alpha = 0.3f)
                        )
                    },
                    trailingIcon = {
                        IconButton(
                            onClick =
                            {
                                datePickerDialog.show()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.DateRange,
                                contentDescription = "CalenderIcon"
                            )
                        }
                    },
                    isError = dobError.value,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        backgroundColor = Color.Gray.copy(alpha = 0.1f),
                    ),
                    enabled = true,
                    readOnly = true
                )
                AnimatedVisibility(visible = dobError.value) {
                    Text(
                        text = dobMessage.value,
                        color = MaterialTheme.colors.error,
                        fontSize = 12.sp
                    )
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    value = number.value,
                    onValueChange = {
                        number.value = it
                        if (number.value.checkNumber()) {
                            numberError.value = false
                        } else {
                            numberError.value = true
                            numberMessage.value = "Please enter a valid number."
                        }
                    },
                    label = {
                        Text(
                            text = "number",
                            color = Color.Black.copy(alpha = 0.3f)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Phone,
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
                                alpha = numberAlpha.value
                            )
                        )
                    },
                    isError = numberError.value,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        backgroundColor = Color.Gray.copy(alpha = 0.1f),
                    )
                )

                AnimatedVisibility(visible = numberError.value) {
                    Text(
                        text = numberMessage.value,
                        color = MaterialTheme.colors.error,
                        fontSize = 12.sp
                    )
                }

                Spacer(
                    modifier = Modifier.height(64.dp)
                )

                AppButton(
                    text = "Continue",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .clip(shape = RoundedCornerShape(25.dp)),
                ) {

                    if (!nickNameError.value && !fullNameError.value && !dobError.value && !numberError.value) {

                        val user = User(
                            _id = userId!!,
                            email = "",
                            password = "",
                            fullName = fullName.value,
                            nickName = nickName.value,
                            dob = date.value,
                            number = number.value,
                            pinCode = "",
                            image = imageUri.value.toString()
                        )

                        profileViewModel.updateProfile(user = user)

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