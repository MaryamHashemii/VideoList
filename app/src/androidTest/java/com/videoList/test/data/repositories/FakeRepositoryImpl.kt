package com.videoList.test.data.repositories

import com.videoList.test.domain.model.ItemVideo
import com.videoList.test.domain.model.LocalCategory
import com.videoList.test.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeRepositoryImpl : AppRepository {

    private val videoItem = mutableListOf(
        ItemVideo("1", "item1", "https://static.cdn.asset.aparat.com/avt/49278147-1729-b__4650.jpg", 10),
        ItemVideo("2", "item2", "https://static.cdn.asset.aparat.com/avt/49278147-1729-b__4650.jpg", 11),
        ItemVideo("3", "item3", "https://static.cdn.asset.aparat.com/avt/49278147-1729-b__4650.jpg", 12)
    )

    private val categoryItem = mutableListOf(
        LocalCategory("2", "action"),
        LocalCategory("3", "art")
    )

    override suspend fun getAllLocalVideos(): Flow<List<ItemVideo>> {
        return flowOf(videoItem)
    }

    override suspend fun getFilterCategorizedVideos(
        categoryId: String,
        page: Int
    ): Flow<List<ItemVideo>> {
        return flowOf(videoItem)
    }

    override suspend fun getCategories(): Flow<List<LocalCategory>> {
        return flowOf(categoryItem)
    }
}