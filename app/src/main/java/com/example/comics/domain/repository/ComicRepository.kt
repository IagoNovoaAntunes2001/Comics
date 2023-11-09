package com.example.comics.domain.repository

import com.example.comics.data.dto.ItemModelDTO
import retrofit2.Response

internal interface ComicRepository {
    suspend fun getComics(): Response<ItemModelDTO>
}