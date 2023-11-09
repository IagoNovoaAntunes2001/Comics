package com.example.comics.ui.comics.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comics.data.converter.ComicDataConverter
import com.example.comics.data.dto.ItemModelDTO
import com.example.comics.domain.entity.ItemVO
import com.example.comics.domain.repository.ComicRepository
import com.example.comics.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

internal class ComicsViewModel(
    private val comicRepository: ComicRepository,
    private val comicsDataConverter: ComicDataConverter
) : ViewModel() {

    private val _comicResult = MutableLiveData<Result<List<ItemVO>>>()
    val comicResult: LiveData<Result<List<ItemVO>>>
        get() = _comicResult

    fun getComics() {
        viewModelScope.launch {
            try {
                val response = comicRepository.getComics()
                isResponseSuccessfully(response)
            } catch (exception: Exception) {
                exception.message?.let { showError(it) }
            }
        }
    }

    private suspend fun isResponseSuccessfully(res: Response<ItemModelDTO>) = when {
        res.isSuccessful -> withContext(Dispatchers.Main) {
            showSuccess(res)
        }

        else -> showError(res.message())
    }

    private fun showSuccess(itemVO: Response<ItemModelDTO>) {
        itemVO.body()?.let { itemModel ->
            val itemsVO = comicsDataConverter.convert(itemModel)
            _comicResult.value = Result.Success(itemsVO)
        } ?: showError(itemVO.message())
    }

    private fun showError(messageError: String) {
        _comicResult.postValue(Result.Error(Exception(messageError)))
    }
}