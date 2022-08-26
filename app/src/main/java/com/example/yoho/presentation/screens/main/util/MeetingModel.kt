package com.example.yoho.presentation.screens.main.util

import com.example.yoho.data.remote.model.MeetingResponse

data class MeetingModel(
    val isLoading: Boolean = false,
    val data: MeetingResponse? = null,
    val message: String = ""
)