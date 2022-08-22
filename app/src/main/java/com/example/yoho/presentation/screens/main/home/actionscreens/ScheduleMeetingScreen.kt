package com.example.yoho.presentation.screens.main.home.actionscreens

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.yoho.presentation.screens.authentication.util.checkDOB
import com.example.yoho.presentation.screens.common.AppButton
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScheduleMeetingScreen() {

    val context = LocalContext.current

    val calendar = Calendar.getInstance()

    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val month = calendar.get(Calendar.MONTH)

    val year = calendar.get(Calendar.YEAR)

    val meetingName = remember {
        mutableStateOf("")
    }

    val waitingRoom = remember {
        mutableStateOf(false)
    }

    val addToCalendar = remember {
        mutableStateOf(false)
    }

    val date = remember {
        mutableStateOf("$day/${month+1}/$year")
    }

    val from = remember {
        mutableStateOf("09:00")
    }

    val to = remember {
        mutableStateOf("10:00")
    }


    val timeZone = remember {
        mutableStateOf("")
    }

    val repeat = remember {
        mutableStateOf("")
    }

    val datePickerDialog = DatePickerDialog(context,
        { _: DatePicker, year: Int, month: Int, day: Int ->

            date.value = "$day/${month + 1}/$year"

        }, year, month, day
    )

    datePickerDialog.datePicker.minDate = calendar.timeInMillis

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Schedule Meeting",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow back icon"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu Icon")
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

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 24.dp
                    )
            ) {

                Text(
                    text = "Meeting Topic",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = meetingName.value,
                    onValueChange = {
                        meetingName.value = it
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        backgroundColor = Color.Gray.copy(alpha = 0.1f)
                    ),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    
                    Text(
                        text = "Date",
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = date.value,
                            fontWeight = FontWeight.Bold
                        )
                        IconButton(onClick = { datePickerDialog.show() }) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = "Arrow Forward"
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "From",
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = from.value,
                            fontWeight = FontWeight.Bold
                        )
                        IconButton(onClick = {
                            TimePickerDialog(
                                context,
                                TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                                    if (hour < 10) {
                                        if (minute < 10) {
                                            from.value = "0$hour:0$minute"
                                        } else {
                                            from.value = "0$hour:$minute"
                                        }
                                    } else {
                                        if (minute < 10) {
                                            from.value = "$hour:0$minute"
                                        } else {
                                            from.value = "$hour:$minute"
                                        }
                                    }
                                },
                                9,
                                0,
                                true
                            ).show()
                        }) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = "Arrow Forward"
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "To",
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = to.value,
                            fontWeight = FontWeight.Bold
                        )
                        IconButton(onClick = {
                            TimePickerDialog(
                                context,
                                TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                                    if (hour < 10) {
                                        if (minute < 10) {
                                            to.value = "0$hour:0$minute"
                                        } else {
                                            to.value = "0$hour:$minute"
                                        }
                                    } else {
                                        if (minute < 10) {
                                            to.value = "$hour:0$minute"
                                        } else {
                                            to.value = "$hour:$minute"
                                        }
                                    }
                                },
                                9,
                                0,
                                true
                            ).show()
                        }) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = "Arrow Forward"
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Time Zone",
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Chennai(+5:30)",
                            fontWeight = FontWeight.Bold
                        )
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = "Arrow Forward"
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Repeat",
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Never",
                            fontWeight = FontWeight.Bold
                        )
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = "Arrow Forward"
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Divider()

                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Meeting Options",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Enable Waiting Room",
                        fontWeight = FontWeight.SemiBold
                    )

                    RadioButton(
                        selected = waitingRoom.value,
                        onClick = { waitingRoom.value = !waitingRoom.value },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colors.primary,
                            unselectedColor = Color.Gray
                        )
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Add to Calendar",
                        fontWeight = FontWeight.SemiBold
                    )

                    RadioButton(
                        selected = addToCalendar.value,
                        onClick = { addToCalendar.value = !addToCalendar.value },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colors.primary,
                            unselectedColor = Color.Gray
                        )
                    )
                }
            }
            AppButton(
                text = "Join",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 24.dp, vertical = 24.dp)
                    .clip(shape = RoundedCornerShape(25.dp)),
            ) {

            }
        }
    }
}

@Composable
fun TimezoneDialog(
    openDialog: MutableState<Boolean>
) {
    if (openDialog.value) {
        Dialog(onDismissRequest = { /*TODO*/ }) {

        }
    }
}