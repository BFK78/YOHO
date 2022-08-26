package com.example.yoho.presentation.screens.main.util

import com.example.yoho.presentation.screens.main.home.MeetingModel

data class ScheduledMeetingModel(
    val isLoading: Boolean = false,
    val data: List<MeetingModel> = emptyList(),
    val message: String = ""
)