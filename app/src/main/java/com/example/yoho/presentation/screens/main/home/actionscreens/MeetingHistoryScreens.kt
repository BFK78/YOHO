package com.example.yoho.presentation.screens.main.home.actionscreens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.yoho.domain.model.local.Meeting
import com.example.yoho.presentation.screens.main.home.SingleMeetingHistory
import com.example.yoho.presentation.screens.main.home.list
import com.example.yoho.presentation.screens.main.home.viewmodel.HomeViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MeetingHistoryScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val meetingList = viewModel.scheduledMeetingState

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Meeting History",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow back button"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Button"
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu Button"
                        )
                    }
                },
                backgroundColor = Color.White,
                elevation = 0.dp
            )
        }
    ) {

        if (meetingList.value.data != null) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(meetingList.value.data!!) {
                    SingleMeetingHistory(model = it)
                }
            }
        }
    }
}