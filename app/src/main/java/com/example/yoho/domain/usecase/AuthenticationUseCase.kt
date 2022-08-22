package com.example.yoho.domain.usecase

import com.example.yoho.common.util.Resource
import com.example.yoho.data.remote.model.AuthResponse
import com.example.yoho.data.remote.model.AuthUser
import com.example.yoho.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthenticationUseCase(
    private val repository: AuthRepository
) {

    suspend fun login(user: AuthUser): Flow<Resource<AuthResponse>> {
        return repository.login(user = user)
    }

    suspend fun register(user: AuthUser): Flow<Resource<AuthResponse>> {
        return repository.register(user = user)
    }

    suspend fun sendOtp(userId: String): Flow<Resource<AuthResponse>> {
        return repository.sendOtp(userId = userId)
    }

    suspend fun verifyOtp(userId: String, otp: String): Flow<Resource<AuthResponse>> {
        return repository.verifyOtp(userId = userId, otp = otp)
    }

    suspend fun sendEmail(userId: String): Flow<Resource<AuthResponse>> {
        return repository.sendEmail(userId = userId)
    }

    suspend fun verifyEmail(userId: String, otp: String): Flow<Resource<AuthResponse>> {
        return repository.verifyEmail(userId = userId, otp = otp)
    }

}