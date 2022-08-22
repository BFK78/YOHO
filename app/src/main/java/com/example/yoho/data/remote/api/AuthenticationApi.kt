package com.example.yoho.data.remote.api

import com.example.yoho.common.network.EndPoints.GET_EMAIL
import com.example.yoho.common.network.EndPoints.GET_OTP
import com.example.yoho.common.network.EndPoints.LOGIN_URL
import com.example.yoho.common.network.EndPoints.REGISTRATION_URL
import com.example.yoho.common.network.EndPoints.VERIFY_EMAIL
import com.example.yoho.common.network.EndPoints.VERIFY_OTP
import com.example.yoho.data.remote.model.AuthResponse
import com.example.yoho.data.remote.model.AuthUser
import retrofit2.http.*

@JvmSuppressWildcards
interface AuthenticationApi {

    @POST(REGISTRATION_URL)
    suspend fun registerUser(
        @Body user: AuthUser
    ): AuthResponse

    @POST(LOGIN_URL)
    suspend fun loginUser(
        @Body user: AuthUser
    ): AuthResponse

    @FormUrlEncoded
    @POST(GET_OTP)
    suspend fun sendOtp(
        @Field("userId") userId: String
    ): AuthResponse

    @FormUrlEncoded
    @POST(VERIFY_OTP)
    suspend fun verifyOtp(
        @Field("userId") userId: String,
        @Field("otp") otp: String
    ): AuthResponse

    @FormUrlEncoded
    @POST(GET_EMAIL)
    suspend fun sendEmail(
        @Field("userId") userId: String
    ): AuthResponse

    @FormUrlEncoded
    @POST(VERIFY_EMAIL)
    suspend fun verifyEmail(
        @Field("userId") userId: String,
        @Field("otp") otp: String
    ): AuthResponse

}