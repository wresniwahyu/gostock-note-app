package com.gostock.featurenote.editnote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gostock.featurenote.R
import com.gostock.ui.component.AppButton
import com.gostock.util.constant.Screens
import com.gostock.util.extension.showToast

@Composable
fun EditNoteScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: EditNoteViewModel = hiltViewModel(),
    id: String,
    title: String,
    note: String
) {

    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when(event) {
                is EditNoteViewModel.Event.UpdateSuccess -> {
                    navController.navigate(Screens.Home.route) {
                        popUpTo(0)
                    }
                }
                is EditNoteViewModel.Event.ShowMessage -> {
                    context.showToast(event.message)
                }
            }
        }
    }

    var inputTitle by remember { mutableStateOf(title) }
    var inputNote by remember { mutableStateOf(note) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "MY NOTE",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
            label = { Text(text = stringResource(R.string.label_title)) },
            value = inputTitle,
            onValueChange = {
                inputTitle = it
            })

        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(200.dp),
            label = { Text(text = stringResource(R.string.label_note)) },
            value = inputNote,
            onValueChange = {
                inputNote = it
            })

        Spacer(modifier = Modifier.weight(1f))

        AppButton(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            text = stringResource(R.string.label_save),
            isLoading = state.isLoading
        ) {
            viewModel.updateNote(id, inputTitle, inputNote)
        }

    }
}