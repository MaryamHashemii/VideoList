package com.videoList.test.data.local.dto

import android.os.Parcelable
import androidx.room.Entity
import com.videoList.test.domain.model.LocalCategory
import kotlinx.parcelize.Parcelize


@Entity(tableName = "category", primaryKeys = ["id"])
@Parcelize
data class ItemCategoryRepositoryModel(
    val id: String,
    val name: String,
) : Parcelable

fun List<ItemCategoryRepositoryModel>.toDomainModelList(): List<LocalCategory> {
    return map {
        LocalCategory(
            id = it.id,
            name = it.name
        )
    }
}