package com.videoList.test.data.repositories

import com.videoList.test.data.dataStore.SaveDataStore
import com.videoList.test.data.local.dao.AppDao
import com.videoList.test.data.local.dto.toDomainModelList
import com.videoList.test.data.remote.ApiService
import javax.inject.Inject
import com.videoList.test.data.remote.dto.response.toLocalModelList
import com.videoList.test.domain.model.LocalCategory
import com.videoList.test.domain.repository.AppRepository
import com.videoList.test.domain.model.ItemVideo
import kotlinx.coroutines.flow.*

const val STOREKEY="savingData"

class AppRepositoryImp @Inject constructor(
    private val apiService: ApiService,
    private val appDao: AppDao,
    private val saveDataStore: SaveDataStore
) : AppRepository {

    override suspend fun getAllLocalVideos(): Flow<List<ItemVideo>> {
        return appDao.getCategorizedVideosRepositories().map {
            it.toDomainModelList()
        }
    }

    override suspend fun getFilterCategorizedVideos(
        categoryId: String,
        page: Int
    ): Flow<List<ItemVideo>> {
        try {
            if (!saveDataStore.get(STOREKEY)) {
                val result = apiService.getCategorizedVideo(
                    categoryId,
                    page
                ).categoryvideos

                appDao.saveCategorizedVideoRepositories(
                    result.toLocalModelList()
                )
                saveDataStore.save(STOREKEY, true)
            }
        } catch (e: Exception) {

        }
        return flowOf(
            apiService.getCategorizedVideo(
                categoryId,
                page
            ).categoryvideos.toLocalModelList().toDomainModelList()
        )

    }


    override suspend fun getCategories(): Flow<List<LocalCategory>> {
        try {
            val result = apiService.getCategories().categories.toLocalModelList()
            appDao.saveCategoriesRepositories(
                result
            )
        } catch (e: Exception) {

        }
//        return appDao.getCategoriesRepositories().map {
//            it.toDomainModelList()
//        }
        return flowOf(
            apiService.getCategories().categories.toLocalModelList().toDomainModelList()
        )
    }
}

