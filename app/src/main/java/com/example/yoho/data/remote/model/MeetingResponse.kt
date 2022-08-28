package com.example.yoho.data.remote.model

import com.example.yoho.data.remote.api.dto.MeetingDto

data class MeetingResponse(
    val status: Boolean,
    val message: String,
    val data: List<MeetingDto>
)
