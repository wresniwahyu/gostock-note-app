package com.gostock.data.mapper

import com.gostock.data.util.Mapper
import com.gostock.data.model.GetNotesUiModel
import com.gostock.data.model.NoteDataUiModel
import com.gostock.network.model.GetNotesResponseDto
import javax.inject.Inject

class GetNotesUiMapper @Inject constructor() : Mapper<GetNotesResponseDto, GetNotesUiModel> {
    override fun map(input: GetNotesResponseDto): GetNotesUiModel {
        return GetNotesUiModel(
            success = input.success ?: false,
            message = input.message.orEmpty(),
            data = input.data?.map {
                NoteDataUiModel(
                    id = it.id.orEmpty(),
                    title = it.title.orEmpty(),
                    note = it.note.orEmpty(),
                    creator = it.creator.orEmpty(),
                    createdAt = it.createdAt.orEmpty(),
                    updatedAt = it.updatedAt.orEmpty(),
                )
            }.orEmpty()
        )
    }
}