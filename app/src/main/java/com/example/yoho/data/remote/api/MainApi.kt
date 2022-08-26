package com.example.yoho.data.remote.api

import com.example.yoho.common.network.EndPoints
import com.example.yoho.data.remote.model.AuthResponse
import com.example.yoho.data.remote.model.MeetingResponse
import com.example.yoho.domain.model.local.Meeting
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

@JvmSuppressWildcards
interface MainApi {

    @POST(EndPoints.SCHEDULE_MEETING)
    suspend fun scheduleMeeting(
        @Body meeting: Meeting
    ): MeetingResponse

    @GET(EndPoints.SCHEDULED_MEETING)
    suspend fun getScheduledMeetings(): MeetingResponse

}