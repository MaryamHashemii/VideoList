package com.videoList.test.di.coroutin

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import com.videoList.test.result.Result

abstract class FlowUseCase<in P, R> {
   suspend operator fun invoke(parameters: P): Flow<Result<R>> = execute(parameters)
        .catch { e -> emit(Result.Error(Exception(e))) }


    protected abstract suspend fun execute(parameters: P): Flow<Result<R>>
}
