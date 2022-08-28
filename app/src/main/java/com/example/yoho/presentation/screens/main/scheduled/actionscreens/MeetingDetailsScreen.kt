package com.example.yoho.presentation.screens.main.scheduled.actionscreens

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.yoho.presentation.screens.common.AppButton
import com.example.yoho.presentation.screens.main.home.BottomSheetContent
import com.example.yoho.presentation.screens.main.scheduled.viewmodel.ScheduledMeetingViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MeetingDetailsScreen(
    meetingId: String?,
    scheduledMeetingViewModel: ScheduledMeetingViewModel = hiltViewModel(),
) {

    val meeting = scheduledMeetingViewModel.meetingState

    val scope = rememberCoroutineScope()

    val modalBottomSheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden
    )

    LaunchedEffect(key1 = true) {
        scheduledMeetingViewModel.getMeetingById(meetingId = meetingId!!)
    }

    val context = LocalContext.current

    ModalBottomSheetLayout(
        sheetContent = {
            DeleteModalSheet(modalBottomSheetState = modalBottomSheetState)
        },
        sheetElevation = 16.dp,
        sheetShape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
        sheetState = modalBottomSheetState,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Meeting Details",
                            fontWeight = FontWeight.Bold
                        )
                    },
                    backgroundColor = Color.White,
                    elevation = 0.dp,
                    navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Arrow back"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit Icon"
                            )
                        }
                    }
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
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "Date", modifier = Modifier.width(80.dp))
                        Spacer(modifier = Modifier.width(50.dp))
                        Text(
                            text = ": ${meeting.value?.date}",
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Divider()

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "Hours", modifier = Modifier.width(80.dp))
                        Spacer(modifier = Modifier.width(50.dp))
                        Text(
                            text = ": ${meeting.value?.from} - ${meeting.value?.to}",
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Divider()

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "Topic", modifier = Modifier.width(80.dp))
                        Spacer(modifier = Modifier.width(50.dp))
                        Text(
                            text = ": ${meeting.value?.meetingTopic}",
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Divider()

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "Meeting ID", modifier = Modifier.width(80.dp))
                        Spacer(modifier = Modifier.width(50.dp))
                        Text(
                            text = ": ${meeting.value?.meetingId}",
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Divider()

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "Duration", modifier = Modifier.width(80.dp))
                        Spacer(modifier = Modifier.width(50.dp))
                        Text(
                            text = ": 90 minutes",
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Divider()

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "Time Zone", modifier = Modifier.width(80.dp))
                        Spacer(modifier = Modifier.width(50.dp))
                        Text(
                            text = ": ${meeting.value?.timeZone}",
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Divider()

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "Repeat", modifier = Modifier.width(80.dp))
                        Spacer(modifier = Modifier.width(50.dp))
                        Text(
                            text = ": ${meeting.value?.repeat}",
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Divider()

                    Spacer(modifier = Modifier.height(16.dp))

                    AppButton(
                        text = "Join",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                            .clip(shape = RoundedCornerShape(25.dp)),
                    ) {

                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    AppButton(
                        text = "Add to Calendar",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                            .clip(shape = RoundedCornerShape(25.dp)),
                        textColor = MaterialTheme.colors.primary,
                        backgroundColor = Color.Gray.copy(alpha = 0.1f)
                    ) {

                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    AppButton(
                        text = "Invite",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                            .clip(shape = RoundedCornerShape(25.dp)),
                        textColor = MaterialTheme.colors.primary,
                        backgroundColor = Color.Gray.copy(alpha = 0.1f)
                    ) {
                        val link = "https://www.instagram.com/"

                        val intent = Intent(Intent.ACTION_SEND)
                        intent.type = "text/plain"
                        intent.putExtra(Intent.EXTRA_TEXT, link)
                        intent.putExtra(Intent.EXTRA_TITLE, "Invite people")

                        context.startActivity(Intent.createChooser(intent, "Share Link"))
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    AppButton(
                        text = "Delete",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                            .clip(shape = RoundedCornerShape(25.dp)),
                        textColor = MaterialTheme.colors.primary,
                        backgroundColor = Color.Gray.copy(alpha = 0.1f)
                    ) {
                        scope.launch {
                            modalBottomSheetState.show()
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DeleteModalSheet(
    modalBottomSheetState: ModalBottomSheetState
) {

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .height(5.dp)
                .width(30.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = Color.Gray.copy(alpha = 0.5f))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Delete",
            color = MaterialTheme.colors.error,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Divider(
            color = Color.Gray.copy(alpha = 0.1f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Are you sure you want to delete this scheduled meeting?",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        Divider(
            color = Color.Gray.copy(alpha = 0.1f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                  scope.launch {
                      modalBottomSheetState.hide()
                  }
                },
                modifier = Modifier
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(25.dp)),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Gray.copy(alpha = .1f),
                    contentColor = MaterialTheme.colors.primary
                ),
                contentPadding = PaddingValues(vertical = 16.dp),
                elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp)
            ) {
                Text(text = "Cancel")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(25.dp)),
                contentPadding = PaddingValues(vertical = 16.dp),
                elevation = ButtonDefaults.elevation(defaultElevation = 0.dp)
            ) {
                Text(text = "Yes, Delete")
            }
        }

    }
}