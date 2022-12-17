package com.videoList.test.domain.usecases

import com.videoList.test.data.repositories.FakeRepositoryImpl
import com.videoList.test.result.Result
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetVideoUseCaseTest {

    private lateinit var videoUseCase: GetVideosUseCase
    private lateinit var fakeRepository: FakeRepositoryImpl

    @Before
    fun setUp() {
        fakeRepository = FakeRepositoryImpl()
        videoUseCase = GetVideosUseCase(fakeRepository)
    }

    @Test
    fun `get all videos, when success`() = runBlocking {
        videoUseCase(Pair("2", 5)).collect {
            when (it) {
                is Result.Success -> {
                    Truth.assertThat(it.data.size).isEqualTo(1)
                }
                else -> {}
            }
        }
    }

    @Test
    fun `get all videos, when empty`() = runBlocking {
        videoUseCase(Pair("3", 5)).collect {
            when (it) {
                is Result.Success -> {
                    Truth.assertThat(it.data.size).isEqualTo(0)
                }
                else -> {}
            }
        }
    }
}