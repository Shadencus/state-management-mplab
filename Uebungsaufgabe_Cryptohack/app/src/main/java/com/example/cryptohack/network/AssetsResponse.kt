package com.example.cryptohack.network

data class AssetsResponse(
    val data: List<CryptoCurrency>,
    val timestamp: Long,
)
