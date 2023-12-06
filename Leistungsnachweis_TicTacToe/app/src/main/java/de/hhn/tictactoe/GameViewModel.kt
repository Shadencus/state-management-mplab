package de.hhn.tictactoe

import androidx.lifecycle.ViewModel
import de.hhn.tictactoe.model.Field
import de.hhn.tictactoe.model.GameModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel : ViewModel() {
    private val _gamemodel = MutableStateFlow(GameModel())
    val gameModel : StateFlow<GameModel> = _gamemodel.asStateFlow()

    private val _gameField = MutableStateFlow(Array(3){Array(3){Field()}})
    val gameField: StateFlow<Array<Array<Field>>> = _gameField.asStateFlow()

    // Hier wird das Spielfeld und Gamemodel resettet
    // In HomeScreen.kt TODO beachten
    // In MainActivity.kt TODO beachten
    fun resetGame(){

    }

    // Hier besteht die Aufgabe darin, die Aktiverung der Boxen zu implementieren.
    // In HomeScreen.kt TODO beachten
    fun selectField(field: Field){

    }


    // Hier werden die Spalten, Reihen und Diagonalen überprüft und das Game beendet, falls ein Spieler gewonnen hat.
    fun checkEndingGame(){

    }
}