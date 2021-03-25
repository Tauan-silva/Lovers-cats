package com.tauan.loverscats.data.network

import com.tauan.loverscats.data.response.ResultCat
import retrofit2.Response
import retrofit2.http.GET

interface Endpoint {
    @GET("gallery/search/?q=cats")
    suspend fun getImageCats(): Response<ResultCat>
}