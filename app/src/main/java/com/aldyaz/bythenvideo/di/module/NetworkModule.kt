package com.aldyaz.bythenvideo.di.module

import android.content.Context
import android.net.ConnectivityManager
import com.aldyaz.bythenvideo.BuildConfig
import com.aldyaz.bythenvideo.datasource.remote.MainInterceptor
import com.aldyaz.bythenvideo.datasource.upload.UploadVideoService
import com.aldyaz.bythenvideo.di.qualifier.ChuckerInterceptorQualifier
import com.aldyaz.bythenvideo.di.qualifier.HttpLoggingInterceptorQualifier
import com.aldyaz.bythenvideo.di.qualifier.MainInterceptorQualifier
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideConnectivityManager(
        @ApplicationContext context: Context
    ): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

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

    @ChuckerInterceptorQualifier
    @Provides
    fun provideChuckerInterceptor(
        @ApplicationContext context: Context
    ): Interceptor = ChuckerInterceptor.Builder(context)
        .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @MainInterceptorQualifier mainInterceptor: Interceptor,
        @HttpLoggingInterceptorQualifier loggingInterceptor: Interceptor,
        @ChuckerInterceptorQualifier chuckerInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(mainInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(chuckerInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
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