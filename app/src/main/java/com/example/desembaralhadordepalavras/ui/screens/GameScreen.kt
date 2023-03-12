package com.example.desembaralhadordepalavras.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.desembaralhadordepalavras.ui.viewmodel.GameViewModel
import com.example.desembaralhadordepalavras.ui.words

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel()
) {

    val gameUIState = gameViewModel.gameUiState.collectAsState()

    GameScore(score = gameUIState.value.score)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        GameCore(
            currentWordToScramble = gameUIState.value.currentScrambledWord,
            currentLevel = gameUIState.value.currentWordCount,
            userGuess = gameViewModel.userGuess,
            whenUserWrite = { userGuess ->
                gameViewModel.updatePlayerGuess(userGuess)
            },
            isWrongWord = gameUIState.value.isGuessedWordWrong,
            onFinished = {
                gameViewModel.checkIfUserWins()
            }
        )
        GameButtons(
            onUserSkip = { gameViewModel.skipWord() },
            onValidateResponse = { gameViewModel.checkIfUserWins() }
        )


        if (gameUIState.value.isGameOver) {
            FinalDialog(
                score = gameUIState.value.score,
                onPlayAgain = { gameViewModel.startGame() })
        }


    }


}