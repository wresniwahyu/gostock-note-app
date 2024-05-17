package com.gostock.data.di

import com.gostock.data.usecase.LoginUseCase
import com.gostock.data.usecase.LoginUseCaseImpl
import com.gostock.data.usecase.RegisterUseCase
import com.gostock.data.usecase.RegisterUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun provideRegisterUseCase(
        impl: RegisterUseCaseImpl
    ): RegisterUseCase

    @Binds
    abstract fun provideLoginUseCase(
        impl: LoginUseCaseImpl
    ): LoginUseCase

}