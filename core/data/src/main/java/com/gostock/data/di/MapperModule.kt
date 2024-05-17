package com.gostock.data.di

import com.gostock.data.mapper.BaseUiMapper
import com.gostock.data.mapper.GetNotesUiMapper
import com.gostock.data.util.Mapper
import com.gostock.network.model.BaseResponseDto
import com.gostock.data.model.BaseUiModel
import com.gostock.data.model.GetNotesUiModel
import com.gostock.network.model.GetNotesResponseDto
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun provideBaseMapper(
        mapper: BaseUiMapper
    ): Mapper<BaseResponseDto, BaseUiModel>

    @Binds
    abstract fun provideGetNotesMapper(
        mapper: GetNotesUiMapper
    ): Mapper<GetNotesResponseDto, GetNotesUiModel>

}