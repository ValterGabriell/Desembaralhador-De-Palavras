package com.example.desembaralhadordepalavras.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GameButtons(
    modifier: Modifier = Modifier,
    onUserSkip: () -> Unit,
    onValidateResponse: () -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        OutlinedButton(modifier = Modifier
            .weight(1f)
            .padding(end = 8.dp), onClick = { onUserSkip() }) {
            Text(
                text = "Pular",
            )

        }

        Button(modifier = Modifier
            .weight(1f)
            .padding(start = 8.dp), onClick = {
            onValidateResponse()
        }) {
            Text(
                text = "Validar",
            )
        }

    }
}