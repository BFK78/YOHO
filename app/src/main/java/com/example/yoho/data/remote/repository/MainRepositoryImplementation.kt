package com.example.yoho.data.remote.repository

import com.example.yoho.common.util.Resource
import com.example.yoho.data.local.dao.MeetingDao
import com.example.yoho.data.remote.api.MainApi
import com.example.yoho.data.remote.model.AuthResponse
import com.example.yoho.data.remote.model.MeetingResponse
import com.example.yoho.domain.model.local.Meeting
import com.example.yoho.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepositoryImplementation(
    private val mainApi: MainApi,
    private val meetingDao: MeetingDao
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

    override suspend fun getAllScheduledMeeting(): Flow<Resource<List<Meeting>>> = flow {
        emit(Resource.Loading(data = null))

        val meetings = meetingDao.getAllMeetings(status = "Join")

        emit(Resource.Loading(data = meetings))

        try {
            val response = mainApi.getScheduledMeetings().data
            val meeting = response.map {
                it.meetingDtoToMeeting()
            }
            meetingDao.deleteAllMeeting()
            meetingDao.insertMeetings(meeting)

            emit(Resource.Success(data = meeting))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString(), data = meetings))
        }
    }

    override suspend fun getAllCompletedMeeting(): Flow<Resource<List<Meeting>>> = flow {
        emit(Resource.Loading(data = null))

        val meetings = meetingDao.getAllMeetings(status = "Completed")

        emit(Resource.Loading(data = meetings))

        try {
            val response = mainApi.getCompletedMeeting()
            val meeting = response.data.map {
                it.meetingDtoToMeeting()
            }
            meetingDao.deleteAllMeeting()
            meetingDao.insertMeetings(meeting)

            emit(Resource.Success(data = meeting))

        }catch (e: Exception) {

            emit(Resource.Error(message = e.message.toString(), data = meetings))

        }
    }

    override suspend fun getMeetingById(meetingId: String): Flow<Resource<Meeting>> = flow {

        emit(Resource.Loading(data = null))

        try {

            val meeting = meetingDao.getMeetingId(meetingId = meetingId)

            emit(Resource.Success(data = meeting))

        } catch (e: Exception) {

            emit(Resource.Error(message = e.message.toString()))

        }
    }
}