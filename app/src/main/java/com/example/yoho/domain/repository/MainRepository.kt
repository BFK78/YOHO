package com.example.yoho.domain.repository

import com.example.yoho.common.util.Resource
import com.example.yoho.data.remote.model.AuthResponse
import com.example.yoho.data.remote.model.MeetingResponse
import com.example.yoho.domain.model.local.Meeting
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun scheduleMeeting(meeting: Meeting): Flow<Resource<MeetingResponse>>

    suspend fun getAllScheduledMeeting(): Flow<Resource<MeetingResponse>>

}