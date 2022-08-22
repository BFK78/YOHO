package com.example.yoho.data.remote.repository

import android.util.Log
import com.example.yoho.common.util.Resource
import com.example.yoho.data.local.dao.TokenDao
import com.example.yoho.data.remote.api.AuthenticationApi
import com.example.yoho.data.remote.model.AuthResponse
import com.example.yoho.data.remote.model.AuthUser
import com.example.yoho.domain.model.local.Token
import com.example.yoho.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class AuthRepositoryImplementation(
    private val authApi: AuthenticationApi,
    private val tokenDao: TokenDao
): AuthRepository {

    override suspend fun login(user: AuthUser) = flow {

        emit(Resource.Loading())

        var response: AuthResponse? = null

        try {
            response = authApi.loginUser(user = user)
            emit(Resource.Success(data = response))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
            e.printStackTrace()
        }
    }

    override suspend fun register(user: AuthUser) = flow {

        emit(Resource.Loading())

        try {
            val response = authApi.registerUser(user = user)

            emit(Resource.Success(data = response))

        } catch (e: Exception) {

            e.printStackTrace()

            emit(Resource.Error(message = e.message.toString()))

        }

    }

    override suspend fun sendOtp(userId: String): Flow<Resource<AuthResponse>> = flow {
        emit(Resource.Loading())

        try {
            val response = authApi.sendOtp(userId = userId)
            emit(Resource.Success(data = response))

        } catch(e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override suspend fun verifyOtp(userId: String, otp: String): Flow<Resource<AuthResponse>> = flow {
        emit(Resource.Loading())

        try {
            val response = authApi.verifyOtp(userId = userId, otp = otp)
            val token = Token(
                token = response.data?.token!!,
                userId = response.data.userId!!
            )
            tokenDao.inertToken(token = token)
            emit(Resource.Success(data = response))
        } catch(e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override suspend fun sendEmail(userId: String): Flow<Resource<AuthResponse>> = flow {
        emit(Resource.Loading())

        try {
            val response = authApi.sendEmail(userId = userId)
            emit(Resource.Success(data = response))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override suspend fun verifyEmail(userId: String, otp: String): Flow<Resource<AuthResponse>> = flow {
        emit(Resource.Loading())

        try {
            val response = authApi.verifyEmail(userId = userId, otp = otp)
            val token = Token(
                token = response.data?.token!!,
                userId = response.data.userId!!
            )
            tokenDao.inertToken(token = token)
            emit(Resource.Success(data = response))
        }catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

}