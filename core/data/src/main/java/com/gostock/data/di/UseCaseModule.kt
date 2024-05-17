package com.gostock.data.di

import com.gostock.data.usecase.AddNoteUseCase
import com.gostock.data.usecase.AddNoteUseCaseImpl
import com.gostock.data.usecase.GetNotesUseCase
import com.gostock.data.usecase.GetNotesUseCaseImpl
import com.gostock.data.usecase.LoginUseCase
import com.gostock.data.usecase.LoginUseCaseImpl
import com.gostock.data.usecase.LogoutUseCase
import com.gostock.data.usecase.LogoutUseCaseImpl
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

    @Binds
    abstract fun provideGetNotesUseCase(
        impl: GetNotesUseCaseImpl
    ): GetNotesUseCase

    @Binds
    abstract fun provideLogoutUseCase(
        impl: LogoutUseCaseImpl
    ): LogoutUseCase

    @Binds
    abstract fun provideAddNoteUseCase(
        impl: AddNoteUseCaseImpl
    ): AddNoteUseCase

}