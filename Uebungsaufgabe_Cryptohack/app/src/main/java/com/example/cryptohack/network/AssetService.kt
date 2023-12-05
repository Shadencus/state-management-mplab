package com.example.cryptohack.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://api.coincap.io/v2/"

private const val ASSETS_PATH = "assets"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface AssetService {
    @GET(ASSETS_PATH)
    suspend fun getAllAssets(): AssetsResponse
}

object Asset {
    val assetService: AssetService by lazy {
        retrofit.create((AssetService::class.java))
    }
}