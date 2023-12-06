package com.example.cryptohack.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.cryptohack.network.Asset
import com.example.cryptohack.network.CryptoCurrency
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class KryptoViewModel : ViewModel() {
    private val _kryptoList = MutableStateFlow(emptyList<CryptoCurrency>())
     var kryptoList : StateFlow<List<CryptoCurrency>> = _kryptoList.asStateFlow()
     private fun addData(data: List<CryptoCurrency>){
         var tempList = mutableListOf<CryptoCurrency>()
         tempList.addAll(data)
         _kryptoList.value = tempList
     }



    fun loadData() {
        val assetService = Asset
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        try {
            scope.launch {
                val res = assetService.assetService.getAllAssets().data
                addData(res)
                println(res)
            }
        } catch (err: Error) {
            println(err)
        }
    }


    fun sortDataPriceDecending(){
        _kryptoList.value = kryptoList.value.sortedByDescending { it.priceUsd }
    }

    fun sortDataMarkCapDecending(){
        _kryptoList.value = kryptoList.value.sortedByDescending { it.marketCapUsd }
    }

}