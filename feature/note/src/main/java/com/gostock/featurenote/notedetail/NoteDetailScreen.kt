package com.gostock.featurenote.notedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gostock.ui.component.AppButton
import com.gostock.ui.component.AppConfirmDialog
import com.gostock.ui.theme.LightGrey
import com.gostock.util.constant.Screens
import com.gostock.util.extension.showToast

@Composable
fun NoteDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: NoteDetailViewModel = hiltViewModel(),
    navController: NavController,
    id: String,
    title: String,
    note: String,
    date: String
) {

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when(event) {
                is NoteDetailViewModel.Event.DeleteSuccess -> {
                    navController.popBackStack()
                }
                is NoteDetailViewModel.Event.ShowMessage -> {
                    context.showToast(event.message)
                }
            }
        }
    }

    var isConfirmDialogShow by remember { mutableStateOf(false) }

    if (isConfirmDialogShow) {
        AppConfirmDialog(
            title = "Delete Note",
            description = "Note will be permanently removed from your account",
            positiveText = "DELETE",
            negativeText = "CANCEL",
            onPositiveButtonClick = {
                viewModel.deleteNote(id)
            },
            onDismiss = {
                isConfirmDialogShow = false
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(LightGrey)
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "MY NOTE",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(12.dp),
                imageVector = Icons.Outlined.CheckCircle,
                contentDescription = null,
                tint = Color.Gray
            )
            Text(
                text = date,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Light,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = note,
            style = MaterialTheme.typography.bodyMedium,
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
        ) {
            AppButton(
                modifier = Modifier.weight(1f),
                text = "EDIT",
                isFullButton = false
            ) {
                navController.navigate(Screens.EditNote.createRoute(id, title, note))
            }

            AppButton(
                modifier = Modifier.weight(1f),
                text = "DELETE",
                isFullButton = false
            ) {
                isConfirmDialogShow = true
            }
        }
    }
}