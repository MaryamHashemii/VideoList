package com.videoList.test.domain.usecases

import com.videoList.test.data.repositories.FakeRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.videoList.test.result.Result

class CategoryUseCaseTest {
    private lateinit var categoryUseCase: CategoryUseCase
    private lateinit var fakeRepository: FakeRepositoryImpl

    @Before
    fun setUp() {
        fakeRepository = FakeRepositoryImpl()
        categoryUseCase = CategoryUseCase(fakeRepository)
    }

    @Test
    fun `get all category, when success`() = runBlocking {
        categoryUseCase(Unit).collect {
            when (it) {
                is Result.Success -> {
                    assertThat(it.data.size).isEqualTo(1)
                }
                else -> {}
            }
        }
    }
}