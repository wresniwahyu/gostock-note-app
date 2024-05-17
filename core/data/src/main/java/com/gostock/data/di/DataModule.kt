package com.gostock.data.di

import com.gostock.data.repository.Repository
import com.gostock.data.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun provideRepository(
        impl: RepositoryImpl
    ): Repository

}