package de.hhn.aib7.mplab.counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.hhn.aib7.mplab.counter.ui.theme.CounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var counterRememberSaveAble by rememberSaveable { mutableStateOf(0) }
            var counterRemember by remember { mutableStateOf(0) }
            CounterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
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
                                text = "RememberSavable:",
                                style = MaterialTheme.typography.displaySmall
                            )
                            Text(
                                text = counterRememberSaveAble.toString(),
                                style = MaterialTheme.typography.displaySmall
                            )
                        }
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            Text(
                                text = "Remember:",
                                style = MaterialTheme.typography.displaySmall
                            )
                            Text(
                                text = counterRemember.toString(),
                                style = MaterialTheme.typography.displaySmall
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                onClick = {
                                    counterRemember--
                                    counterRememberSaveAble--
                                },
                                modifier = Modifier.width(150.dp),
                                content = {
                                    Text(
                                        text = "-",
                                        style = MaterialTheme.typography.displayMedium
                                    )
                                }
                            )
                            Button(
                                onClick = {
                                    counterRemember++
                                    counterRememberSaveAble++
                                },
                                modifier = Modifier.width(150.dp),
                                content = {
                                    Text(
                                        text = "+",
                                        style = MaterialTheme.typography.displayMedium
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CounterTheme {
    }
}

