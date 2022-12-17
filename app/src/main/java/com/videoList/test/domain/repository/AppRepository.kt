package com.videoList.test.domain.repository

import com.videoList.test.domain.model.LocalCategory
import com.videoList.test.domain.model.ItemVideo
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    suspend fun getAllLocalVideos(): Flow<List<ItemVideo>>
    suspend fun getFilterCategorizedVideos(categoryId:String,page: Int): Flow<List<ItemVideo>>
    suspend fun getCategories():Flow<List<LocalCategory>>

}