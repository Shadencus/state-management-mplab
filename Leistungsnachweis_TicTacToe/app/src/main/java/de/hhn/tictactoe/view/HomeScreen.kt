package de.hhn.tictactoe.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.hhn.tictactoe.GameViewModel
import de.hhn.tictactoe.model.Status
import de.hhn.tictactoe.R
import de.hhn.tictactoe.model.Field
import de.hhn.tictactoe.model.GameModel

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(gameViewModel: GameViewModel = viewModel()) {
    //Setting the GameModel and the GameField
    val currentGame by gameViewModel.gameModel.collectAsState()
    val gameField by gameViewModel.gameField.collectAsState()


    val currentPlayer = currentGame.currentPlayer
    val isGameEnding: Boolean = currentGame.isGameEnding
    val winningPlayer = currentGame.winningPlayer
    val winningPlayerColor = Field(winningPlayer).showColor()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 30.dp), //made the padding smaller so that the winningplayer-Text doens't need scrolling to be shown
            color = Color.Black
        )
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                if (currentPlayer == Status.PlayerX) {
                    Text(
                        text = Status.PlayerX.toString(),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 50.dp),
                        color = Color.Blue
                    )
                    Text(
                        text = Status.PlayerO.toString(),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 50.dp),
                        color = Color.Red
                    )
                } else {
                    Text(
                        text = Status.PlayerX.toString(),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 50.dp),
                        color = Color.Blue
                    )
                    Text(
                        text = Status.PlayerO.toString(),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 50.dp),
                        color = Color.Red
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                gameField.forEach { rows ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                    ) {
                        rows.forEach { field ->
                            Card(
                                modifier = Modifier
                                    .padding(all = 2.dp)
                                    .border(
                                        width = 2.dp,
                                        color = Color.Gray,
                                        shape = RoundedCornerShape(5.dp),
                                    )
                                    .height(111.dp)
                                    .width(111.dp),
                                onClick = {
                                    // TODO:
                                    //Call selectField function
                                    gameViewModel.selectField(field)
                                    //Call checkEndingGame function
                                    gameViewModel.checkEndingGame()
                                }
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(
                                        text = field.showText(),
                                        fontSize = 60.sp,
                                        color = field.showColor(),
                                    )
                                }
                            }
                        }
                    }
                }
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (isGameEnding) {
                        Text(
                            text = "Wining: $winningPlayer",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(vertical = 25.dp),
                            color = winningPlayerColor
                        )
                    }
                }
            }
        }
    }
}
