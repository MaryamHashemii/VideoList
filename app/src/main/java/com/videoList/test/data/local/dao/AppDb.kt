package com.videoList.test.data.local.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.videoList.test.BuildConfig
import com.videoList.test.data.local.dto.ItemCategoryRepositoryModel
import com.videoList.test.data.local.dto.ItemVideoRepositoryModel

@Database(entities = [ItemVideoRepositoryModel::class,ItemCategoryRepositoryModel::class], version = 1)
abstract class AppDb : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {
        @Volatile
        private var INSTANCE: AppDb? = null

        fun getDatabase(context: Context): AppDb {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDb::class.java,
                    BuildConfig.APPLICATION_ID
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}