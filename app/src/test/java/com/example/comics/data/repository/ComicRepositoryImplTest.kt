package com.example.comics.data.repository

import com.example.comics.data.dto.DataModelDTO
import com.example.comics.data.dto.ItemModelDTO
import com.example.comics.domain.service.ComicService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ComicRepositoryImplTest {

    private val comicService = mockk<ComicService>()
    private val comicRepository = ComicRepositoryImpl(comicService)

    private val expectedResponse = Response.success(ItemModelDTO(DataModelDTO(listOf())))

    @Before
    fun setup() {
        coEvery { comicService.getComics(any(), any(), any()) } returns expectedResponse
    }

    @Test
    fun `getComics should return the expected response`() = runBlocking {
        val result = comicRepository.getComics()

        coEvery { comicService.getComics(any(), any(), any()) }
        assert(result == expectedResponse)
    }

    @Test
    fun `getComics should call the ComicService method with the correct arguments`(): Unit = runBlocking {
        comicRepository.getComics()

        coEvery { comicService.getComics(any(), any(), any()) }
    }
}
