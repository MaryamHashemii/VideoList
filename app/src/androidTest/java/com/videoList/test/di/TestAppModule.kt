package com.videoList.test.di

import android.content.Context
import androidx.room.Room
import com.videoList.test.App
import com.videoList.test.BuildConfig
import com.videoList.test.data.dataStore.SaveDataStore
import com.videoList.test.data.local.dao.AppDao
import com.videoList.test.data.local.dao.AppDb
import com.videoList.test.data.remote.ApiService
import com.videoList.test.data.repositories.AppRepositoryImp
import com.videoList.test.data.repositories.FakeRepositoryImpl
import com.videoList.test.domain.repository.AppRepository
import com.videoList.test.domain.usecases.CategoryUseCase
import com.videoList.test.domain.usecases.GetVideosUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): App {
        return app as App
    }


    @Provides
    fun provideAppDAO(db: AppDb) = db.appDao()

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        val builder = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
        return builder.build()
    }

    @Provides
    fun provideHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)

        return builder.build()
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
    }

    @Provides
    @Singleton
    fun provideAppRepository(): AppRepository {
        return FakeRepositoryImpl()
    }


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, AppDb::class.java)
            .allowMainThreadQueries()
            .build()


}