package com.tauan.loverscats.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val CLIENT_ID: String = "77740a842a73a8f"

object RetrofitInstance {

    val client: OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor { chain ->
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .addHeader(
                        "Authorization",
                        "Client-ID $CLIENT_ID"
                    )
                    .build()
            )
        }.build()

    val retrofit: Endpoint by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.imgur.com/3/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Endpoint::class.java)
    }
}