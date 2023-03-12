package com.example.desembaralhadordepalavras.ui.screens

import android.app.Activity
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun FinalDialog(
    score: Int,
    onPlayAgain: () -> Unit,
    modifer: Modifier = Modifier
) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = {},
        title = { Text("Parabéns, você chegou ao final!") },
        text = { Text("Sua pontuação é: $score") },
        modifier = modifer,
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(text = "Sair")
            }
        },
        confirmButton = {
            TextButton(onClick = onPlayAgain) {
                Text(text = "Jogar novamente")
            }
        }
    )




}