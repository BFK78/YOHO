package com.example.yoho.presentation.screens.main.util

import com.example.yoho.domain.model.local.Meeting

data class ScheduledMeetingModel(
    val isLoading: Boolean = false,
    val data: List<Meeting>? = null,
    val message: String = ""
)