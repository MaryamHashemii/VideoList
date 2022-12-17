package com.videoList.test.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemVideo(
    val id: String = "",
    val name: String = "",
    val images: String = "",
    val visit_cnt: Int = 0
) : Parcelable




