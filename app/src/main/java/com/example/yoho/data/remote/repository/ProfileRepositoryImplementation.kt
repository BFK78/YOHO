package com.example.yoho.data.remote.repository

import android.util.Log
import com.example.yoho.common.util.Resource
import com.example.yoho.data.local.dao.UserDao
import com.example.yoho.data.remote.api.ProfileApi
import com.example.yoho.data.remote.model.AuthResponse
import com.example.yoho.domain.model.local.User
import com.example.yoho.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProfileRepositoryImplementation(
    private val profileApi: ProfileApi,
    private val userDao: UserDao
): ProfileRepository {

    override suspend fun updateProfile(user: User): Flow<Resource<AuthResponse>> = flow {
        emit(Resource.Loading())

        try {
            Log.i("basim", "before response")
            val response = profileApi.updateUser(user = user)
            Log.i("basim", response.toString())

            emit(Resource.Success(data = response))

        }catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override suspend fun createPin(userId: String, pin: String): Flow<Resource<AuthResponse>> = flow {
        emit(Resource.Loading())

        try {
            val response = profileApi.createPin(userId = userId, pin = pin)

            emit(Resource.Success(data = response))

        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override suspend fun getUser(email: String): Flow<Resource<AuthResponse>> = flow {
        emit(Resource.Loading())

        try {
            val response = profileApi.getUser(email = email)
            emit(Resource.Success(data = response))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override suspend fun updatePassword(
        userId: String,
        password: String,
        token: String
    ): Flow<Resource<AuthResponse>> = flow {
        emit(Resource.Loading())
        try {
            val response = profileApi.updatePassword(token = token, userId = userId, password = password)
            emit(Resource.Success(data = response))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

}