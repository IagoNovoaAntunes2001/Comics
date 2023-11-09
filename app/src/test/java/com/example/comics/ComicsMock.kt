package com.example.comics

import com.example.comics.data.dto.DataModelDTO
import com.example.comics.data.dto.ItemModelDTO
import com.example.comics.data.dto.ResultModelDTO
import com.example.comics.data.dto.ThumbnailModelDTO
import com.example.comics.domain.entity.ItemVO

internal object ComicsMock {

    fun mockItemDTO() = ItemModelDTO(
        DataModelDTO(
            listOf(
                ResultModelDTO(
                    "title",
                    "description",
                    ThumbnailModelDTO(
                        "pathString"
                    )
                )
            )
        )
    )


    fun mockItemsVOList() = listOf(
        ItemVO(
            "image",
            "title",
            "subtitle"
        )
    )

    fun mockError(errorMessage: String) =
        Exception(errorMessage)
}