package com.example.yoho.domain.model.local

data class Meeting(
    val meetingTopic: String,
    val date: String,
    val from: String,
    val to: String,
    val timeZone: String,
    val repeat: String,
    val waitingRoom: Boolean,
    val meetingLink: String,
    val meetingId: String,
    val status: String = "Join"
)