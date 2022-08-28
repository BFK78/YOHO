package com.example.yoho.di

import com.example.yoho.data.local.dao.MeetingDao
import com.example.yoho.data.local.database.YoHoDatabase
import com.example.yoho.data.remote.api.MainApi
import com.example.yoho.data.remote.repository.MainRepositoryImplementation
import com.example.yoho.domain.model.local.Meeting
import com.example.yoho.domain.repository.MainRepository
import com.example.yoho.domain.usecase.MeetingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    //Dao
    @Provides
    @Singleton
    fun provideMeetingDao(
        database: YoHoDatabase
    ): MeetingDao {
        return database.meetingDao
    }

    //Api
    @Provides
    @Singleton
    fun provideMeetingApi(
        retrofit: Retrofit
    ): MainApi {
        return retrofit.create(MainApi::class.java)
    }

    //Repository
    @Provides
    @Singleton
    fun provideMainRepository(
        mainApi: MainApi,
        meetingDao: MeetingDao
    ): MainRepository {
        return MainRepositoryImplementation(
            mainApi = mainApi,
            meetingDao = meetingDao
        )
    }

    //UseCase
    @Provides
    @Singleton
    fun provideMeetingUseCase(
        mainRepository: MainRepository
    ): MeetingUseCase {
        return MeetingUseCase(mainRepository = mainRepository)
    }

}