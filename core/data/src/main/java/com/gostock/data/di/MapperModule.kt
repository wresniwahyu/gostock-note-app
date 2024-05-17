package com.gostock.data.di

import com.gostock.data.mapper.BaseUiMapper
import com.gostock.data.util.Mapper
import com.gostock.network.model.BaseResponseDto
import com.gostock.data.model.BaseUiModel
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

}