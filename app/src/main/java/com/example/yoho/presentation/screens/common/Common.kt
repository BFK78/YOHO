package com.example.yoho.presentation.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.yoho.ui.theme.White
import com.example.yoho.R

@Composable
fun AppButton(
    text: String,
    modifier: Modifier,
    backgroundColor: Color = MaterialTheme.colors.primary,
    textColor: Color = Color.White,
    onClick: () -> Unit,
) {

    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor
        ),
        contentPadding = PaddingValues(16.dp),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp)
    ) {

        Text(
            text = text,
            color = textColor
        )

    }

}

@Composable
fun PinScreen(
    onClick: (String) -> Unit,
) {

    val focusRequester = remember {
        FocusRequester()
    }

    val focusManager = LocalFocusManager.current

    val p1 = remember {
        mutableStateOf("")
    }

    val p2 = remember {
        mutableStateOf("")
    }

    val p3 = remember {
        mutableStateOf("")
    }

    val p4 = remember {
        mutableStateOf("")
    }

    val pin = remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = true) {
        focusRequester.requestFocus()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Spacer(modifier = Modifier.width(24.dp))

        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .focusRequester(focusRequester),
            value = p1.value,
            onValueChange = {
                p1.value = it
                focusManager.moveFocus(FocusDirection.Next)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = MaterialTheme.colors.primary,
                backgroundColor = Color.Gray.copy(
                    alpha = 0.3f
                ),
            ),
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.width(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .weight(1f),
            value = p2.value,
            onValueChange = {
                p2.value = it
                focusManager.moveFocus(FocusDirection.Next)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = MaterialTheme.colors.primary,
                backgroundColor = Color.Gray.copy(
                    alpha = 0.3f
                )
            ),
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.width(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .weight(1f),
            value = p3.value,
            onValueChange = {
                p3.value = it
                focusManager.moveFocus(FocusDirection.Next)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = MaterialTheme.colors.primary,
                backgroundColor = Color.Gray.copy(
                    alpha = 0.3f
                )
            ),
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.width(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .weight(1f),
            value = p4.value,
            onValueChange = {
                p4.value = it
                focusManager.clearFocus()
                pin.value = p1.value + p2.value + p3.value + p4.value
                onClick(pin.value)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = MaterialTheme.colors.primary,
                backgroundColor = Color.Gray.copy(
                    alpha = 0.3f
                )
            ),
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.width(24.dp))

    }

}


@Composable
fun OtpScreen(
    onClick: (String) -> Unit,
) {

    val focusRequester = remember {
        FocusRequester()
    }

    val focusManager = LocalFocusManager.current

    val p1 = remember {
        mutableStateOf("")
    }

    val p2 = remember {
        mutableStateOf("")
    }

    val p3 = remember {
        mutableStateOf("")
    }

    val p4 = remember {
        mutableStateOf("")
    }

    val p5 = remember {
        mutableStateOf("")
    }

    val p6 = remember {
        mutableStateOf("")
    }

    val pin = remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = true) {
        focusRequester.requestFocus()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Spacer(modifier = Modifier.width(24.dp))

        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .focusRequester(focusRequester),
            value = p1.value,
            onValueChange = {
                p1.value = it
                focusManager.moveFocus(FocusDirection.Next)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = MaterialTheme.colors.primary,
                backgroundColor = Color.Gray.copy(
                    alpha = 0.3f
                ),
            ),
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.width(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .weight(1f),
            value = p2.value,
            onValueChange = {
                p2.value = it
                focusManager.moveFocus(FocusDirection.Next)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = MaterialTheme.colors.primary,
                backgroundColor = Color.Gray.copy(
                    alpha = 0.3f
                )
            ),
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.width(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .weight(1f),
            value = p3.value,
            onValueChange = {
                p3.value = it
                focusManager.moveFocus(FocusDirection.Next)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = MaterialTheme.colors.primary,
                backgroundColor = Color.Gray.copy(
                    alpha = 0.3f
                )
            ),
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.width(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .weight(1f),
            value = p4.value,
            onValueChange = {
                p4.value = it
                focusManager.moveFocus(FocusDirection.Next)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = MaterialTheme.colors.primary,
                backgroundColor = Color.Gray.copy(
                    alpha = 0.3f
                )
            ),
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.width(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .weight(1f),
            value = p5.value,
            onValueChange = {
                p5.value = it
                focusManager.moveFocus(FocusDirection.Next)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = MaterialTheme.colors.primary,
                backgroundColor = Color.Gray.copy(
                    alpha = 0.3f
                )
            ),
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.width(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .weight(1f),
            value = p6.value,
            onValueChange = {
                p6.value = it
                focusManager.clearFocus()
                pin.value = p1.value + p2.value + p3.value + p4.value + p5.value + p6.value
                onClick(pin.value)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = MaterialTheme.colors.primary,
                backgroundColor = Color.Gray.copy(
                    alpha = 0.3f
                )
            ),
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.width(24.dp))

    }

}

@Composable
fun LoadingDialog(
    openDialog: MutableState<Boolean>,
) {

    if (openDialog.value) {
        Dialog(onDismissRequest = { openDialog.value = false },
            properties = DialogProperties(dismissOnBackPress = false,
                dismissOnClickOutside = false)) {

            Box(
                modifier = Modifier
                    .size(200.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primary
                )
            }

        }
    }
}

@Composable
fun SuccessDialog(
    openDialog: MutableState<Boolean>,
) {
    if (openDialog.value) {
        Dialog(
            onDismissRequest = { /*TODO*/ }
        ) {
            Column(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(30.dp))
                    .background(color = White)
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_successbg),
                    contentDescription = "Success Background",
                    modifier = Modifier
                        .height(120.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Congratulations!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Your account is ready to use. You will be redirected to the home page in a few seconds.",
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                CircularProgressIndicator(
                    color = MaterialTheme.colors.primary
                )

            }
        }
    }
}