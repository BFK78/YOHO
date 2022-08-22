package com.example.yoho.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.yoho.BuildConfig
import com.example.yoho.common.constants.Constants.DATABASE_NAME
import com.example.yoho.common.network.EndPoints.BASE_URL
import com.example.yoho.data.local.dao.TokenDao
import com.example.yoho.data.local.dao.UserDao
import com.example.yoho.data.local.database.YoHoDatabase
import com.example.yoho.data.local.repository.TokenRepositoryImplementation
import com.example.yoho.data.remote.api.AuthenticationApi
import com.example.yoho.data.remote.api.ProfileApi
import com.example.yoho.data.remote.repository.AuthRepositoryImplementation
import com.example.yoho.data.remote.repository.ProfileRepositoryImplementation
import com.example.yoho.domain.repository.AuthRepository
import com.example.yoho.domain.repository.ProfileRepository
import com.example.yoho.domain.repository.TokenRepository
import com.example.yoho.domain.usecase.AuthenticationUseCase
import com.example.yoho.domain.usecase.GetTokenUseCase
import com.example.yoho.domain.usecase.ProfileUseCase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {

    //Database
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): YoHoDatabase {
        return Room.databaseBuilder(
            context,
            YoHoDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    //Dao
    @Provides
    @Singleton
    fun provideUserDao(
        database: YoHoDatabase
    ): UserDao {
        return database.userDao
    }

    @Provides
    @Singleton
    fun provideTokenDao(
        database: YoHoDatabase
    ): TokenDao {
        return database.tokenDao
    }

    //Gson
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    //Retrofit
    @Provides
    @Singleton
    fun provideRetrofit(
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().also {
                    if (BuildConfig.DEBUG) {
                        val logging = HttpLoggingInterceptor()
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        it.addInterceptor(logging)
                        it.connectTimeout(120, TimeUnit.SECONDS)
                        it.readTimeout(120, TimeUnit.SECONDS)
                        it.protocols(Collections.singletonList(Protocol.HTTP_1_1))
                    }
                }.build()
            )
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    //Api
    @Provides
    @Singleton
    fun provideAuthenticationApi(
        retrofit: Retrofit
    ): AuthenticationApi {
        return retrofit.create(AuthenticationApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileApi(
        retrofit: Retrofit
    ): ProfileApi {
        return retrofit.create(ProfileApi::class.java)
    }

    //Repository
    @Provides
    @Singleton
    fun provideAuthRepository(
        authApi: AuthenticationApi,
        tokenDao: TokenDao
    ): AuthRepository {
        return AuthRepositoryImplementation(authApi = authApi, tokenDao = tokenDao)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(
        profileApi: ProfileApi,
        userDao: UserDao
    ): ProfileRepository {
        return ProfileRepositoryImplementation(profileApi = profileApi, userDao = userDao)
    }

    @Provides
    @Singleton
    fun provideTokenRepository(
        tokenDao: TokenDao
    ): TokenRepository {
        return TokenRepositoryImplementation(
            tokenDao = tokenDao
        )
    }

    //UseCase
    @Provides
    @Singleton
    fun provideAuthenticationUseCase(
        authRepository: AuthRepository
    ): AuthenticationUseCase {
        return AuthenticationUseCase(repository = authRepository)
    }

    @Provides
    @Singleton
    fun provideProfileUseCase(
        profileRepository: ProfileRepository
    ): ProfileUseCase {
        return ProfileUseCase(repository = profileRepository)
    }

    @Provides
    @Singleton
    fun provideGetTokenUseCase(
        tokenRepository: TokenRepository
    ): GetTokenUseCase {
        return GetTokenUseCase(repository = tokenRepository)
    }

}