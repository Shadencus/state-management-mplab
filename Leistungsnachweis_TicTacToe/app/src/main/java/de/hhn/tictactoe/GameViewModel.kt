package de.hhn.tictactoe

import androidx.lifecycle.ViewModel
import de.hhn.tictactoe.model.Field
import de.hhn.tictactoe.model.GameModel
import de.hhn.tictactoe.model.Status
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel : ViewModel() {

    //Init StateFlow for gameModel
    private val _gameModel = MutableStateFlow(GameModel())
    val gameModel : StateFlow<GameModel> = _gameModel.asStateFlow()

    //Init StateFlow for gameField
    private val _gameField = MutableStateFlow(Array(3){Array(3){Field()}})
    val gameField: StateFlow<Array<Array<Field>>> = _gameField.asStateFlow()

    //resetting everything, when the GameViewModel is initialized, to ensure the game starts correct
    init {
        resetGame()
    }

    // Hier wird das Spielfeld und Gamemodel resettet
    // In HomeScreen.kt TODO beachten
    // In MainActivity.kt TODO beachten
    fun resetGame(){
        //Setting the fields with the right coordinates in the 2d-Array and ensure that they are empty
        for (row in 0 until _gameField.value.size){
            for (col in 0 until _gameField.value.size){
                _gameField.value[col][row].indexColumn = col
                _gameField.value[col][row].indexRow = row
                _gameField.value[col][row].status = Status.Empty
            }
        }

        //Switch the old gameModel against a new one
        _gameModel.value = GameModel()

    }

    // Hier besteht die Aufgabe darin, die Aktiverung der Boxen zu implementieren.
    // In HomeScreen.kt TODO beachten
    fun selectField(field: Field){
        //Checking if field is empty
        if (field.status == Status.Empty && !gameModel.value.isGameEnding){
            //Giving the selected field their new value
            _gameField.value[field.indexColumn][field.indexRow] = Field(gameModel.value.currentPlayer,field.indexColumn, field.indexRow)
            //Updating the currentplayer of the gameModel
            _gameModel.value = _gameModel.value.copy(currentPlayer = _gameModel.value.currentPlayer.next())
        }
    }

    // Hier werden die Spalten, Reihen und Diagonalen überprüft und das Game beendet, falls ein Spieler gewonnen hat.
    fun checkEndingGame(){
        if (!gameModel.value.isGameEnding){
            //Checking if there are 3 identical Symbols in a row
            for(rows in 0 until gameField.value.size){
                if(gameField.value[rows][0].status == gameField.value[rows][1].status &&//check first part of line
                    gameField.value[rows][1].status == gameField.value[rows][2].status &&//check second part of line
                    gameField.value[rows][1].status != Status.Empty){//ensure line is not empty
                    //updating the complete gameModel
                    _gameModel.value = _gameModel.value.copy(winningPlayer = gameField.value[rows][0].status, isGameEnding = true, currentPlayer = Status.Empty)
                    break
                }
            }

            //Checking there are 3 identical Symbols in a column
            for(columns in 0 until gameField.value.size){
                if(gameField.value[0][columns].status == gameField.value[1][columns].status && //check first part of line
                    gameField.value[1][columns].status == gameField.value[2][columns].status&& //check second part of line
                    gameField.value[1][columns].status != Status.Empty){ //ensure line is not empty
                    //updating the complete gameModel
                    _gameModel.value = _gameModel.value.copy(winningPlayer = gameField.value[0][columns].status, isGameEnding = true, currentPlayer = Status.Empty)
                    break
                }
            }

            //Checking there are 3 identical Symbols in the first diagonal
            if (gameField.value[1][1].status!=Status.Empty &&
                gameField.value[0][0].status == gameField.value[1][1].status &&
                gameField.value[2][2].status == gameField.value[1][1].status){
                //updating the complete gameModel
                _gameModel.value = _gameModel.value.copy(winningPlayer = gameField.value[1][1].status, isGameEnding = true, currentPlayer = Status.Empty)
            }

            //Checking there are 3 identical Symbols in the second diagonal
            if (gameField.value[1][1].status!=Status.Empty &&
                gameField.value[0][2].status == gameField.value[1][1].status &&
                gameField.value[2][0].status == gameField.value[1][1].status){
                //updating the complete gameModel
                _gameModel.value = _gameModel.value.copy(winningPlayer = gameField.value[1][1].status, isGameEnding = true, currentPlayer = Status.Empty)
            }
        }
    }
}