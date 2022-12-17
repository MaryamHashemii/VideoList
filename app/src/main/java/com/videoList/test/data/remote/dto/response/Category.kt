package com.videoList.test.data.remote.dto.response

import com.videoList.test.data.local.dto.ItemCategoryRepositoryModel

data class Category(
    val id: String = "",
    val name: String = ""
)

fun List<Category>.toLocalModelList(): List<ItemCategoryRepositoryModel> {
    return map {
        ItemCategoryRepositoryModel(
            id = it.id,
            name = it.name,
        )
    }
}

