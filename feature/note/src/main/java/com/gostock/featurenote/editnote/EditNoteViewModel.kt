package com.gostock.featurenote.editnote

import androidx.lifecycle.viewModelScope
import com.gostock.data.usecase.UpdateNoteUseCase
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
class EditNoteViewModel @Inject constructor(
    private val updateNoteUseCase: UpdateNoteUseCase
): BaseViewModel<EditNoteViewModel.State, EditNoteViewModel.Event>() {

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    override fun defaultState() = State(
        isLoading = false
    )

    fun updateNote(
        id: String,
        title: String,
        note: String
    ) = viewModelScope.launch {
        _state.update { state -> state.copy(isLoading = true) }
        val response = updateNoteUseCase(id, title, note)
        response.onSuccess {
            _state.update { state -> state.copy(isLoading = false) }
            _event.emit(Event.UpdateSuccess)
        }.onError { e, code, message, error ->
            _state.update { state -> state.copy(isLoading = false) }
            _event.emit(Event.ShowMessage(message ?: "Terjadi Kesalahan"))
        }
    }

    override fun onEvent(event: Event) {
        viewModelScope.launch {
            when(event) {
                is Event.UpdateSuccess -> {
                    _event.emit(Event.UpdateSuccess)
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
        object UpdateSuccess: Event()
        class ShowMessage(val message: String): Event()
    }

}