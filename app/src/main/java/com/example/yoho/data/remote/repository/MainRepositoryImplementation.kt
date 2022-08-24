package com.example.yoho.data.remote.repository

import com.example.yoho.common.util.Resource
import com.example.yoho.data.remote.api.MainApi
import com.example.yoho.data.remote.model.AuthResponse
import com.example.yoho.data.remote.model.MeetingResponse
import com.example.yoho.domain.model.local.Meeting
import com.example.yoho.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepositoryImplementation(
    private val mainApi: MainApi
): MainRepository {
    override suspend fun scheduleMeeting(meeting: Meeting): Flow<Resource<MeetingResponse>> = flow {
        emit(Resource.Loading())
        try {
            val response = mainApi.scheduleMeeting(meeting = meeting)
            emit(Resource.Success(data = response))
        }catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}