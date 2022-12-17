package com.videoList.test.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.videoList.test.data.local.dao.AppDao
import com.videoList.test.data.local.dao.AppDb
import com.videoList.test.data.remote.dto.response.Category
import com.videoList.test.data.remote.dto.response.toLocalModelList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.videoList.test.data.local.dto.ItemCategoryRepositoryModel
import com.videoList.test.di.AppModule
import com.videoList.test.di.DatabaseModule
import com.videoList.test.di.NetworkModule
import com.videoList.test.di.RepositoryModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.Rule
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@HiltAndroidTest
@UninstallModules(
    AppModule::class,
    DatabaseModule::class,
    NetworkModule::class,
    RepositoryModule::class
)
class AppDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var dataBase: AppDb

    private lateinit var appDao: AppDao


    @Before
    fun setUp() {
        hiltRule.inject()
        appDao = dataBase.appDao()
    }

    @After
    fun teardown() {
        dataBase.close()
    }

    @Test
    fun insertCategoryList() = runBlocking {
        var result = listOf<ItemCategoryRepositoryModel>()
        val categories = listOf(Category("1", "test1"))
        appDao.saveCategoriesRepositories(categories.toLocalModelList())
        appDao.getCategoriesRepositories().collect {
            result = it
        }
        assertThat(result).contains(categories.toLocalModelList())
    }

    @Test
    fun getAllCategories() = runBlocking {
        var result = listOf<ItemCategoryRepositoryModel>()
        for (i in 1..5) {
            appDao.saveCategoriesRepositories(listOf(ItemCategoryRepositoryModel("$i", "test$i")))
        }
        appDao.getCategoriesRepositories().collect {
            result = it
        }
        assertThat(result.size).isEqualTo(5)
    }
}
















