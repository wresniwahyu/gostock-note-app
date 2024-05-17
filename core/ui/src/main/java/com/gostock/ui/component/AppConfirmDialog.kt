package com.gostock.ui.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AppConfirmDialog(
    title: String,
    description: String,
    positiveText: String,
    negativeText: String,
    onPositiveButtonClick: () -> Unit = {},
    onNegativeButtonClick: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = {
            onDismiss.invoke()
        },
        title = {
            Text(text = title)
        },
        text = {
            Text(text = description)
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDismiss.invoke()
                    onPositiveButtonClick.invoke()
                }
            ) {
                Text(positiveText)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss.invoke()
                    onNegativeButtonClick.invoke()
                }
            ) {
                Text(negativeText)
            }
        }
    )
}

@Preview
@Composable
fun ConfirmDialogPrev() {
    AppConfirmDialog("Title", "This is description", "Yes", "No")
}