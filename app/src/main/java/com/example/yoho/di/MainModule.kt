package com.example.yoho.di

import com.example.yoho.data.remote.api.MainApi
import com.example.yoho.data.remote.repository.MainRepositoryImplementation
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
        mainApi: MainApi
    ): MainRepository {
        return MainRepositoryImplementation(mainApi = mainApi)
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