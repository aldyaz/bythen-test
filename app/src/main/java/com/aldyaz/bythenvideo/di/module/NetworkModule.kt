package com.aldyaz.bythenvideo.di.module

import com.aldyaz.bythenvideo.datasource.remote.MainInterceptor
import com.aldyaz.bythenvideo.datasource.upload.UploadVideoService
import com.aldyaz.bythenvideo.di.qualifier.HttpLoggingInterceptorQualifier
import com.aldyaz.bythenvideo.di.qualifier.MainInterceptorQualifier
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideGson(): Gson = Gson()

    @MainInterceptorQualifier
    @Provides
    fun provideMainInterceptor(): Interceptor = MainInterceptor()

    @HttpLoggingInterceptorQualifier
    @Provides
    fun provideHttpLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @MainInterceptorQualifier mainInterceptor: Interceptor,
        @HttpLoggingInterceptorQualifier loggingInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(mainInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideUploadVideoService(
        retrofit: Retrofit
    ): UploadVideoService {
        return retrofit.create(UploadVideoService::class.java)
    }
}