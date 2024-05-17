package com.gostock.featurenote.addnote

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gostock.featurenote.R
import com.gostock.ui.component.AppButton
import com.gostock.ui.theme.LightGrey
import com.gostock.util.extension.showToast

@Composable
fun AddNoteScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AddNoteViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when(event) {
                is AddNoteViewModel.Event.SaveNoteSucess -> {
                    navController.popBackStack()
                }
                is AddNoteViewModel.Event.ShowMessage -> {
                    context.showToast(event.message)
                }
            }
        }
    }

    var title by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

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
            value = title,
            onValueChange = {
                title = it
            })

        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(200.dp),
            label = { Text(text = stringResource(R.string.label_note)) },
            value = note,
            onValueChange = {
                note = it
            })

        Spacer(modifier = Modifier.weight(1f))

        AppButton(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            text = stringResource(R.string.label_save),
            isLoading = state.isLoading
        ) {
            viewModel.saveNote(title, note)
        }

    }
}