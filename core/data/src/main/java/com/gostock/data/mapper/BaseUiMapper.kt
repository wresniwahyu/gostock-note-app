package com.gostock.data.mapper

import com.gostock.data.util.Mapper
import com.gostock.network.model.BaseResponseDto
import com.gostock.data.model.BaseUiModel
import javax.inject.Inject

class BaseUiMapper @Inject constructor() : Mapper<BaseResponseDto, BaseUiModel> {
    override fun map(input: BaseResponseDto): BaseUiModel {
        return BaseUiModel(
            success = input.success ?: false,
            message = input.message.orEmpty()
        )
    }
}