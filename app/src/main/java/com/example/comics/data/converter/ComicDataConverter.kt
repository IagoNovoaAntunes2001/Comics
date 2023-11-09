package com.example.comics.data.converter

import com.example.comics.data.dto.ItemModelDTO
import com.example.comics.domain.entity.ItemVO

class ComicDataConverter {
    fun convert(dto: ItemModelDTO): List<ItemVO> {
        return dto.data.results.map {
            ItemVO(
                it.thumbnail.path ?: "",
                it.title ?: "",
                it.description ?: ""
            )
        }
    }
}