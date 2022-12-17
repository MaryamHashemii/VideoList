package com.videoList.test.data.local.dto

import android.os.Parcelable
import androidx.room.Entity
import com.videoList.test.domain.model.ItemVideo
import kotlinx.parcelize.Parcelize

@Entity(tableName = "video", primaryKeys = ["id"])
@Parcelize
data class ItemVideoRepositoryModel(
    val id: String,
    val name: String,
    val image: String,
    val visit_cnt: Int
) : Parcelable

fun List<ItemVideoRepositoryModel>.toDomainModelList(): List<ItemVideo> {
    return map {
        ItemVideo(
            id = it.id,
            name = it.name,
            images = it.image,
            visit_cnt = it.visit_cnt
        )
    }
}
