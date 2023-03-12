package com.example.desembaralhadordepalavras.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.desembaralhadordepalavras.ui.GameUIState
import com.example.desembaralhadordepalavras.ui.MAX_WORD_COUNT
import com.example.desembaralhadordepalavras.ui.SCORE_INC
import com.example.desembaralhadordepalavras.ui.words
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    private val _gameUiState = MutableStateFlow(GameUIState())
    val gameUiState: StateFlow<GameUIState> = _gameUiState.asStateFlow()


    private var usedWords: MutableSet<String> = mutableSetOf()
    private lateinit var currentWord: String

    var userGuess by mutableStateOf("")
        private set

    init {
        startGame()
    }


    private fun updateGame(score: Int) {
        if (usedWords.size == MAX_WORD_COUNT) {
            _gameUiState.update { state ->
                state.copy(
                    isGuessedWordWrong = false,
                    score = score,
                    isGameOver = true
                )
            }
        } else {
            _gameUiState.update { state ->
                state.copy(
                    currentScrambledWord = pickARandomWordAndShuffleIt(),
                    score = score,
                    currentWordCount = state.currentWordCount.inc(),
                    isGuessedWordWrong = false
                )
            }
        }
    }

    fun updatePlayerGuess(userWordGuess: String) {
        userGuess = userWordGuess
    }

    fun checkIfUserWins() {

            if (userGuess.equals(currentWord, ignoreCase = true)) {
                val updateScore = _gameUiState.value.score.plus(SCORE_INC)
                updateGame(updateScore)
            } else {
                _gameUiState.value.copy(
                    isGuessedWordWrong = true
                )
            }


        updatePlayerGuess("")
    }

    fun startGame() {
        usedWords.clear()
        _gameUiState.value = GameUIState(currentScrambledWord = pickARandomWordAndShuffleIt())
    }

    fun skipWord() {
        updateGame(_gameUiState.value.score)
        updatePlayerGuess("")
    }

    private fun pickARandomWordAndShuffleIt(): String {

        currentWord = words.random()
        return if (usedWords.contains(currentWord)) {
            pickARandomWordAndShuffleIt()
        } else {
            usedWords.add(currentWord)
            shuffleWord(currentWord)
        }
    }

    private fun shuffleWord(currentWord: String): String {
        val tempWord = currentWord.toCharArray()

        tempWord.shuffle()

        if (String(tempWord).equals(currentWord)) {
            tempWord.shuffle()
        }

        return String(tempWord)
    }

}