package com.videoList.test.domain.usecases

import com.videoList.test.domain.repository.AppRepository
import com.videoList.test.di.coroutin.FlowUseCase
import com.videoList.test.domain.model.LocalCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import  com.videoList.test.result.Result

class CategoryUseCase @Inject constructor(
    private val appRepository: AppRepository
) : FlowUseCase<Unit, List<LocalCategory>>() {

    override suspend fun execute(parameters: Unit): Flow<Result<List<LocalCategory>>> {
        return appRepository.getCategories()
            .map {
                Result.Success(it)
            }
            .catch {
                if (it is Exception)
                    Result.Error(it)
            }

    }
}