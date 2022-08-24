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

}