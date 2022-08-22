package com.example.yoho.domain.usecase

import android.util.Log
import com.example.yoho.common.util.Resource
import com.example.yoho.data.remote.model.AuthResponse
import com.example.yoho.domain.model.local.User
import com.example.yoho.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow

class ProfileUseCase(
    private val repository: ProfileRepository
) {

    suspend fun updateProfile(user: User): Flow<Resource<AuthResponse>> {
        Log.i("user", user.toString())
        return repository.updateProfile(user = user)
    }

    suspend fun createPin(userId: String, pin: String): Flow<Resource<AuthResponse>> {
        return repository.createPin(userId = userId, pin = pin)
    }

    suspend fun getUser(email: String): Flow<Resource<AuthResponse>> {
        return repository.getUser(email = email)
    }

    suspend fun updatePassword(userId: String, password: String, token: String): Flow<Resource<AuthResponse>> {
        return repository.updatePassword(userId = userId, password = password, token = token)
    }

}