package com.videoList.test.domain.usecases

import com.videoList.test.di.coroutin.FlowUseCase
import com.videoList.test.domain.model.ItemVideo
import com.videoList.test.domain.repository.AppRepository
import com.videoList.test.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllVideoUseCase @Inject constructor(
    private val appRepository: AppRepository,
) : FlowUseCase<Unit, List<ItemVideo>>() {

    override suspend fun execute(parameters: Unit): Flow<Result<List<ItemVideo>>> {
        return appRepository.getAllLocalVideos()
            .map {
                Result.Success(it)
            }
            .catch {
                if (it is Exception)
                    Result.Error(it)
            }
    }
}