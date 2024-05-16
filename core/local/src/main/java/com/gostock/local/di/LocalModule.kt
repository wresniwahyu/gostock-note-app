package com.gostock.local.di

import android.content.Context
import android.content.SharedPreferences
import com.gostock.local.UserPref
import com.gostock.local.UserPrefImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext appContext: Context
    ): SharedPreferences {
        return appContext.getSharedPreferences("SharedPreferenceFile", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideTokenRepository(
        sharedPref: SharedPreferences
    ): UserPref {
        return UserPrefImpl(sharedPref)
    }

}