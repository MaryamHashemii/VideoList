package com.videoList.test.data.local.dao

import androidx.room.*
import com.videoList.test.data.local.dto.ItemCategoryRepositoryModel
import com.videoList.test.data.local.dto.ItemVideoRepositoryModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Query("SELECT * FROM video")
    fun getCategorizedVideosRepositories(): Flow<List<ItemVideoRepositoryModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCategorizedVideoRepositories(videoList: List<ItemVideoRepositoryModel>)

    @Query("SELECT * FROM category")
    fun getCategoriesRepositories(): Flow<List<ItemCategoryRepositoryModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCategoriesRepositories(categoryList: List<ItemCategoryRepositoryModel>)

}