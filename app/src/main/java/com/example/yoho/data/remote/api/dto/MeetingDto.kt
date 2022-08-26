package com.example.yoho.data.remote.api.dto

import com.example.yoho.domain.model.local.Meeting

data class MeetingDto(
    val _id: String = "",
    val meetingTopic: String,
    val date: String,
    val from: String,
    val to: String,
    val timeZone: String,
    val repeat: String,
    val waitingRoom: Boolean,
    val meetingLink: String,
    val meetingId: String,
    val status: String,
    val __v: Int = 0
) {
    fun meetingDtoToMeeting() = Meeting(
        meetingTopic, date, from, to, timeZone, repeat, waitingRoom, meetingLink, meetingId, status
    )
}