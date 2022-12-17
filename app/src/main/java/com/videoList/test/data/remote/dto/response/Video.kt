package com.videoList.test.data.remote.dto.response

import com.videoList.test.data.local.dto.ItemVideoRepositoryModel

data class Video(
    val big_poster: String = "",
    val id: String = "",
    val title: String = "",
    val visit_cnt: Int = 0
)

fun List<Video>.toLocalModelList(): List<ItemVideoRepositoryModel> {
    return map {
        ItemVideoRepositoryModel(
            id = it.id,
            image = it.big_poster,
            name = it.title,
            visit_cnt = it.visit_cnt
        )
    }
}
