package com.example.cryptohack.network

data class CryptoCurrency(
    val id: String,
    val rank: Int,
    val symbol: String,
    val name: String,
    val supply: Double,
    val maxSupply: Double?,
    val marketCapUsd: Double,
    val volumeUsd24Hr: Double,
    val priceUsd: Double,
    val changePercent24Hr: Double,
    val vwap24Hr: Double?,
    val explorer: String?
)
