package com.example.desembaralhadordepalavras.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameCore(
    modifier: Modifier = Modifier,
    currentWordToScramble: String,
    currentLevel: Int,
    userGuess: String,
    whenUserWrite: (String) -> Unit,
    isWrongWord: Boolean,
    onFinished: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = currentWordToScramble,
            fontSize = 42.sp,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )

        Text(
            text = "$currentLevel de 10 palavras",
            fontSize = 16.sp,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = userGuess,
            onValueChange = { userWrite ->
                whenUserWrite(userWrite)
            },
            singleLine = true,
            label = {
                if (isWrongWord) {
                    Text(text = "Palavra correta!")
                } else {
                   Text(text =  "Insira uma palavra!")
                }

            },
            isError = isWrongWord,
            keyboardActions = KeyboardActions(
                onDone = { onFinished() }
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            )

        )
    }

}