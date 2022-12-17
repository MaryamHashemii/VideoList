package com.videoList.test.presentation.video

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.videoList.test.domain.usecases.CategoryUseCase
import com.videoList.test.domain.usecases.GetVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.videoList.test.domain.model.LocalCategory
import com.videoList.test.domain.model.ItemVideo
import com.videoList.test.domain.usecases.GetAllVideoUseCase
import com.videoList.test.result.Result


@HiltViewModel
class VideoViewModel @Inject constructor(
    private val getVideosUseCase: GetVideosUseCase,
    private val categoryUseCase: CategoryUseCase,
    private val getAllVideoUseCase: GetAllVideoUseCase,
    savedStateHandle: SavedStateHandle

) : ViewModel() {

    data class DataStateCategory(
        val data: List<LocalCategory> = emptyList(),
        val errorMessage: String? = null,
        val isLoading: Boolean = false
    )

    data class DataStateVideo(
        val data: List<ItemVideo> = emptyList(),
        val errorMessage: String? = null,
        val isLoading: Boolean = false
    )

    private val _localCategoryState: MutableState<DataStateCategory> =
        mutableStateOf(DataStateCategory())
    val localCategoryState: State<DataStateCategory> = _localCategoryState

    private val _videoState: MutableState<DataStateVideo> = mutableStateOf(DataStateVideo())
    val videoState: State<DataStateVideo> = _videoState

    private val _videoDetailState: MutableState<ItemVideo> = mutableStateOf(ItemVideo())
    val videoDetailState: State<ItemVideo> = _videoDetailState

    val selectedLocalCategory: MutableState<LocalCategory?> = mutableStateOf(null)


    init {
        getCategory()
        getVideos(null)

        savedStateHandle.get<String>("videoId").let { videoId ->
            if (!videoId.isNullOrEmpty()) {
                viewModelScope.launch {
                    getAllVideoUseCase(Unit).collect { result ->
                        when (result) {
                            is Result.Success -> {
                                _videoDetailState.value = result.data.first {
                                    it.id == videoId
                                }
                            }
                            else -> {}
                        }
                    }
                }
            }
        }
    }

    fun getVideos(localCategory: LocalCategory?) {
        selectedLocalCategory.value = localCategory
        viewModelScope.launch {
            getVideosUseCase(Pair(localCategory?.id ?: "0", 5)).collect {
                when (it) {
                    is Result.Success -> {
                        _videoState.value = videoState.value.copy(data = it.data)
                    }

                    is Result.Loading -> {
                        _videoState.value = videoState.value.copy(isLoading = true)

                    }
                    is Result.Error -> {
                        _videoState.value =
                            videoState.value.copy(errorMessage = it.exception.message)
                    }
                }

            }
        }
    }

    fun getCategory() {
        viewModelScope.launch {
            categoryUseCase(Unit).collect {
                when (it) {
                    is Result.Success -> {
                        _localCategoryState.value =
                            localCategoryState.value.copy(data = it.data)
                    }
                    is Result.Loading -> {
                        _localCategoryState.value =
                            localCategoryState.value.copy(isLoading = true)

                    }
                    is Result.Error -> {
                        _localCategoryState.value =
                            localCategoryState.value.copy(errorMessage = it.exception.message)

                    }
                }
            }

        }
    }

}

