package com.example.yoho.domain.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
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
    val status: String = "Join",
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)