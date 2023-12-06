package com.example.cryptohack.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cryptohack.network.Asset
import com.example.cryptohack.network.CryptoCurrency
import com.example.cryptohack.viewmodel.KryptoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun HomeScreen(kryptoViewModel: KryptoViewModel = viewModel()) {

    val cryptos by kryptoViewModel.kryptoList.collectAsState()
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "CryptoHack",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 50.dp)
        )
        var showMenu by remember { mutableStateOf(false) }
        Box(modifier = Modifier.align(Alignment.End)){
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(Icons.Default.MoreVert, "")
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }) {
                DropdownMenuItem(text = { Text("Price") }, onClick = {kryptoViewModel.sortDataPriceDecending() })
                DropdownMenuItem(text = { Text("MarketCap") }, onClick = { kryptoViewModel.sortDataMarkCapDecending()})
            }
        }
        Button(
            onClick = {
                kryptoViewModel.loadData()
            }
        ){
            Text("add Data")
        }

        LazyColumn {
            items(cryptos.size, key = { index -> cryptos[index].id }) {

                index ->
                CryptoRow(cryptos[index])
                Spacer(modifier = Modifier.height(10.dp))
            }
        }


    }
}

@Composable
fun CryptoRow(cryptoCurrency: CryptoCurrency) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(0.9f)
    ) {
        Column {
            Text(text = cryptoCurrency.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
            Text(text = cryptoCurrency.symbol, style = MaterialTheme.typography.titleSmall)
        }
        Text(((cryptoCurrency.priceUsd * 100).roundToInt().toDouble() /100).toString(), fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
    }
}

