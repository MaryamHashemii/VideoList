package com.videoList.test.data.remote

import com.videoList.test.data.remote.dto.response.CategoryResponse
import com.videoList.test.data.remote.dto.response.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("categoryVideos/cat/{categoryId}/perpage/{page}")
    suspend fun getCategorizedVideo(
        @Path("categoryId") categoryId: String ,
        @Path("page") page: Int
    ): VideoResponse

    @GET("categories")
    suspend fun getCategories(): CategoryResponse


}