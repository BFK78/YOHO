package com.example.yoho.presentation.screens.main.scheduled

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.yoho.presentation.navigation.BottomNavigationBar
import com.example.yoho.presentation.screens.main.home.MeetingModel
import com.example.yoho.presentation.screens.main.home.list


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScheduledScreen(
    navHostController: NavHostController
) {
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

        ListScheduleItem(list = list)

    }
}

@Composable
fun ListScheduleItem(
    list: List<MeetingModel>
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
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = list[it].time,
                    fontSize = 14.sp
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = list[it].meetingTitle,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Meeting ID : ${list[it].meetingId}",
                        fontSize = 14.sp
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