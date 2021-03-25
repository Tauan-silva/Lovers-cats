package com.tauan.loverscats.data.response

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("images")
    val images: List<Image>
)