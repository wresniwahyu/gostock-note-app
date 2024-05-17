package com.gostock.featurenote.notedetail

import androidx.lifecycle.viewModelScope
import com.gostock.data.usecase.DeleteNoteUseCase
import com.gostock.data.util.onError
import com.gostock.data.util.onSuccess
import com.gostock.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val deleteNoteUseCase: DeleteNoteUseCase
) : BaseViewModel<NoteDetailViewModel.State, NoteDetailViewModel.Event>() {

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    fun deleteNote(id: String) = viewModelScope.launch {
        _state.update { state -> state.copy(isLoading = true) }
        val response = deleteNoteUseCase(id)
        response.onSuccess {
            _state.update { state -> state.copy(isLoading = false) }
            _event.emit(Event.DeleteSuccess)
        }.onError { e, code, message, error ->
            _state.update { state -> state.copy(isLoading = false) }
            _event.emit(Event.ShowMessage(message ?: "Terjadi Kesalahan"))
        }

    }

    override fun defaultState() = State(
        isLoading = false
    )

    override fun onEvent(event: Event) {
        viewModelScope.launch {
            when (event) {
                is Event.DeleteSuccess -> {
                    _event.emit(Event.DeleteSuccess)
                }

                is Event.ShowMessage -> {
                    _event.emit(Event.ShowMessage(event.message))
                }
            }
        }
    }

    data class State(
        val isLoading: Boolean
    )

    sealed class Event {
        object DeleteSuccess : Event()
        class ShowMessage(val message: String) : Event()
    }
}