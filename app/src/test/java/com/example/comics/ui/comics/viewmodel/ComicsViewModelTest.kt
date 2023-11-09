package com.example.comics.ui.comics.viewmodel

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import com.example.comics.ComicsMock.mockError
import com.example.comics.ComicsMock.mockItemDTO
import com.example.comics.ComicsMock.mockItemsVOList
import com.example.comics.data.converter.ComicDataConverter
import com.example.comics.domain.repository.ComicRepository
import com.example.comics.util.Result
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ComicsViewModelTest {

    private val comicRepository = mockk<ComicRepository>()
    private val comicsDataConverter = mockk<ComicDataConverter>()

    private val comicsViewModel = ComicsViewModel(comicRepository, comicsDataConverter)

    @Before
    fun setup() {
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) = runnable.run()
            override fun postToMainThread(runnable: Runnable) = runnable.run()
            override fun isMainThread() = true
        })
        Dispatchers.setMain(Dispatchers.Unconfined)
        coEvery { comicRepository.getComics() } returns mockk(relaxed = true)
        every { comicsDataConverter.convert(any()) } returns mockk(relaxed = true)
    }

    @Test
    fun `getComics should set _comicResult to Success when response is successful`() = runBlocking {
        coEvery { comicRepository.getComics() } returns Response.success(mockItemDTO())
        every { comicsDataConverter.convert(any()) } returns mockItemsVOList()

        comicsViewModel.getComics()

        val result = comicsViewModel.comicResult.value
        assertTrue(result is Result.Success)
        assertEquals(mockItemsVOList(), (result as Result.Success).data)
    }

    @Test
    fun `getComics should set _comicResult to Error when response is not successful`() =
        runBlocking {
            val errorMessage = "Some error message"
            coEvery { comicRepository.getComics() } throws mockError(errorMessage)

            comicsViewModel.getComics()

            val result = comicsViewModel.comicResult.value
            assertTrue(result is Result.Error)
            assertEquals(errorMessage, (result as Result.Error).exception.message)
        }
}
