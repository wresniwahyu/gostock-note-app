package com.gostock.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.gostock.local.UserPref
import com.gostock.network.BuildConfig
import com.gostock.network.service.ApiService
import com.gostock.network.util.AppInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideKotlinJsonAdapterFactory(): KotlinJsonAdapterFactory = KotlinJsonAdapterFactory()

    @Provides
    @Singleton
    fun provideMoshi(
        kotlinJsonAdapterFactory: KotlinJsonAdapterFactory
    ): Moshi = Moshi.Builder()
        .add(kotlinJsonAdapterFactory)
        .build()

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(
        moshi: Moshi
    ): MoshiConverterFactory = MoshiConverterFactory.create(moshi)

    @Provides
    @Singleton
    fun provideChuckerInterceptor(
        @ApplicationContext appContext: Context
    ) = ChuckerInterceptor.Builder(appContext)
        .alwaysReadResponseBody(true)
        .build()

    @Provides
    @Singleton
    fun provideAppInterceptor(
        userPref: UserPref
    ): AppInterceptor {
        return AppInterceptor(userPref)
    }

    @Provides
    @Singleton
    fun provideOkHttp(
        chuckerInterceptor: ChuckerInterceptor,
        appInterceptor: AppInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(chuckerInterceptor)
        .addInterceptor(appInterceptor)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofitClient(
        okHttp: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(moshiConverterFactory)
        .client(okHttp)
        .baseUrl(BuildConfig.BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}