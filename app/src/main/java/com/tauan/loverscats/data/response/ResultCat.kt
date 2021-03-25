package com.tauan.loverscats.data.response

import com.google.gson.annotations.SerializedName

data class ResultCat(
    @SerializedName("data")
    val data: List<Data>,
    val status: Int,
    val success: Boolean
)