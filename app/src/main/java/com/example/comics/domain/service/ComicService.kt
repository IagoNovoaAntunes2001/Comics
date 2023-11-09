package com.example.comics.domain.service

import com.example.comics.data.dto.ItemModelDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val ENDPOINT_COMICS = "comics"
private const val TS_QUERY = "ts"
private const val APIKEY_QUERY = "apikey"
private const val HASH_QUERY = "hash"

interface ComicService {

    @GET(ENDPOINT_COMICS)
    suspend fun getComics(
        @Query(TS_QUERY) ts: String,
        @Query(APIKEY_QUERY) apikey: String,
        @Query(HASH_QUERY) hash: String
    ): Response<ItemModelDTO>
}
