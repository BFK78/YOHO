package com.example.yoho.data.remote.api

import com.example.yoho.common.network.EndPoints
import com.example.yoho.data.remote.model.AuthResponse
import com.example.yoho.domain.model.local.User
import retrofit2.http.*

@JvmSuppressWildcards
interface ProfileApi {

    @POST(EndPoints.UPDATE_PROFILE)
    suspend fun updateUser(
        @Body user: User
    ): AuthResponse

    @FormUrlEncoded
    @POST(EndPoints.CREATE_PIN)
    suspend fun createPin(
        @Field("userId") userId: String,
        @Field("pin") pin: String
    ): AuthResponse

    @GET(EndPoints.GET_USER)
    suspend fun getUser(
        @Query("email") email: String
    ): AuthResponse

    @POST(EndPoints.UPDATE_PASSWORD)
    @FormUrlEncoded
    suspend fun updatePassword(
        @Header("auth-token") token: String,
        @Field("userId") userId: String,
        @Field("password") password: String
    ): AuthResponse

}