package com.example.yoho.domain.usecase

import com.example.yoho.common.util.Resource
import com.example.yoho.data.remote.model.MeetingResponse
import com.example.yoho.domain.model.local.Meeting
import com.example.yoho.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow

class MeetingUseCase(
    private val mainRepository: MainRepository
) {

    suspend fun scheduleMeeting(meeting: Meeting): Flow<Resource<MeetingResponse>> {
        return mainRepository.scheduleMeeting(meeting = meeting)
    }

    suspend fun getAllScheduledMeeting(): Flow<Resource<List<Meeting>>> {
        return mainRepository.getAllScheduledMeeting()
    }

    suspend fun getAllCompletedMeeting(): Flow<Resource<List<Meeting>>> {
        return mainRepository.getAllCompletedMeeting()
    }

    suspend fun getMeetingById(meetingId: String): Flow<Resource<Meeting>> {
        return mainRepository.getMeetingById(meetingId = meetingId)
    }

}