package com.example.comics.data.repository

import com.example.comics.data.dto.ItemModelDTO
import com.example.comics.domain.repository.ComicRepository
import com.example.comics.domain.service.ComicService
import retrofit2.Response

private const val APIKEY = "b7e14bab409c70a5c4e7c2b319c09d7b"
private const val TS = "1682982412"
private const val HASH = "3482f01e9bf207a437a4b621c91339ad"

internal class ComicRepositoryImpl(
    private val comicService: ComicService
) : ComicRepository {

    override suspend fun getComics(): Response<ItemModelDTO> {
        return comicService.getComics(
            apikey = APIKEY,
            ts = TS,
            hash = HASH
        )
    }
}
