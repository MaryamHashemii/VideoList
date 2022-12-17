package com.videoList.test.domain.usecases

import com.videoList.test.domain.repository.AppRepository
import com.videoList.test.di.coroutin.FlowUseCase
import com.videoList.test.domain.model.ItemVideo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.videoList.test.result.Result

class GetVideosUseCase @Inject constructor(
    private val appRepository: AppRepository,
) : FlowUseCase<Pair<String, Int>, List<ItemVideo>>() {

    override suspend fun execute(parameters: Pair<String, Int>): Flow<Result<List<ItemVideo>>> {
        return appRepository.getFilterCategorizedVideos(parameters.first, parameters.second)
            .map {
                Result.Success(it)
            }
            .catch {
                if (it is Exception)
                    Result.Error(it)
            }
    }
}