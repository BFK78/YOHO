package com.example.yoho.presentation.screens.main.scheduled

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.AppLaunchChecker
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.yoho.domain.model.local.Meeting
import com.example.yoho.presentation.navigation.BottomNavigationBar
import com.example.yoho.presentation.screens.main.home.MeetingModel
import com.example.yoho.presentation.screens.main.home.list
import com.example.yoho.presentation.screens.main.scheduled.viewmodel.ScheduledMeetingViewModel
import com.example.yoho.presentation.util.screens.Screens


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScheduledScreen(
    navHostController: NavHostController,
    scheduledMeetingViewModel: ScheduledMeetingViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = true) {
        scheduledMeetingViewModel.getAllScheduledMeeting()
    }

    val meetingState = scheduledMeetingViewModel.scheduledMeetingState

    val verticalScrollState = rememberScrollState()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navHostController = navHostController)
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Scheduled Meeting",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Placeholder icon"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu button"
                        )
                    }
                },
                elevation = 0.dp,
                backgroundColor = Color.White
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(verticalScrollState)
        ) {
            if (meetingState.value.data != null) {
                ListScheduleItem(list = meetingState.value.data!!, navHostController = navHostController)
            }

        }
    }
}

@Composable
fun ListScheduleItem(
    list: List<Meeting>,
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(
            text = "Today",
            fontWeight = FontWeight.Bold
        )

        repeat(list.size) {

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
                    .clickable {
                       navHostController.navigate(Screens.MeetingDetailsScreen.route + "/${list[it].meetingId}")
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = list[it].from,
                    fontSize = 14.sp
                )
                Column(
                    modifier = Modifier
                        .width(220.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = list[it].meetingTopic,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Text(
                        text = "Meeting ID : ${list[it].meetingId}",
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
                Text(
                    text = "Join",
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(15.dp))
                        .background(color = MaterialTheme.colors.primary)
                        .padding(vertical = 4.dp, horizontal = 12.dp)
                        .clickable {

                        },
                    color = Color.White,
                    fontSize = 12.sp,
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider(
            modifier = Modifier,
            color = Color.Black.copy(
                alpha = 0.1f
            )
        )
    }
}