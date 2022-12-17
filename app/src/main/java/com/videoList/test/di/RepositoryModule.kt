package com.videoList.test.di

import com.videoList.test.data.repositories.AppRepositoryImp
import com.videoList.test.data.dataStore.SaveDataStore
import com.videoList.test.data.local.dao.AppDao
import com.videoList.test.data.remote.ApiService
import com.videoList.test.domain.repository.AppRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.Provides
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAppRepository(
        apiService: ApiService,
        appDao: AppDao,
        saveDataStore: SaveDataStore
    ): AppRepository {
        return AppRepositoryImp(apiService, appDao, saveDataStore)
    }

}