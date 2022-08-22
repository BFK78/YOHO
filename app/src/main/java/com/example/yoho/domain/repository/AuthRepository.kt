package com.example.yoho.domain.repository

import com.example.yoho.common.util.Resource
import com.example.yoho.data.remote.model.AuthResponse
import com.example.yoho.data.remote.model.AuthUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun login(user: AuthUser): Flow<Resource<AuthResponse>>

    suspend fun register(user: AuthUser): Flow<Resource<AuthResponse>>

    suspend fun sendOtp(userId: String): Flow<Resource<AuthResponse>>

    suspend fun verifyOtp(userId: String, otp: String): Flow<Resource<AuthResponse>>

    suspend fun sendEmail(userId: String): Flow<Resource<AuthResponse>>

    suspend fun verifyEmail(userId: String, otp: String): Flow<Resource<AuthResponse>>
}