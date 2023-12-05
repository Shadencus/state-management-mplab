package de.hhn.aib7.mplab.counter

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import de.hhn.aib7.mplab.counter.ui.theme.CounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CounterView()
                }
            }
        }
    }
}

@Composable
fun CounterView(counterViewModel: CounterViewModel = viewModel(), savedStateViewModel: CounterSavedViewModel = viewModel()) {
    val counter by counterViewModel.counter.collectAsState() // Access counter value from ViewModel
    val savedCounter by savedStateViewModel.counter.collectAsState() // Access counter value from ViewModel
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "Counter",
            style = MaterialTheme.typography.displayLarge,
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "ViewModel:",
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = counter.toString(),
                style = MaterialTheme.typography.displaySmall
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "VM + SavedState:",
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = savedCounter.toString(),
                style = MaterialTheme.typography.displaySmall
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Button(
                // Execute decrement function of ViewModel
                onClick = {
                    counterViewModel.decrement()
                    savedStateViewModel.decrement()
                },
                modifier = Modifier.width(150.dp),
                content = { Text(text = "-", style = MaterialTheme.typography.displayMedium)}
            )
            Button(
                // Execute increment function of ViewModel
                onClick = {
                    counterViewModel.increment()
                    savedStateViewModel.increment()
                },
                modifier = Modifier.width(150.dp),
                content = { Text(text = "+", style = MaterialTheme.typography.displayMedium)})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CounterTheme {
        CounterView()
    }
}