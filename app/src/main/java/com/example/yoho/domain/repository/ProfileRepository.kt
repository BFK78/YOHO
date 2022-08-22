package com.example.yoho.domain.repository

import com.example.yoho.common.util.Resource
import com.example.yoho.data.remote.model.AuthResponse
import com.example.yoho.domain.model.local.User
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun updateProfile(user: User): Flow<Resource<AuthResponse>>

    suspend fun createPin(userId: String, pin: String): Flow<Resource<AuthResponse>>

    suspend fun getUser(email: String): Flow<Resource<AuthResponse>>

    suspend fun updatePassword(userId: String, password: String, token: String): Flow<Resource<AuthResponse>>

}