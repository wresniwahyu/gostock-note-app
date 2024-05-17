package com.gostock.featurenote.addnote

import androidx.lifecycle.viewModelScope
import com.gostock.data.usecase.AddNoteUseCase
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
class AddNoteViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase
): BaseViewModel<AddNoteViewModel.State, AddNoteViewModel.Event>() {

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    override fun defaultState() = State(
        isLoading = false
    )

    fun saveNote(
        title: String,
        note: String
    ) = viewModelScope.launch {
        _state.update { state -> state.copy(isLoading = true) }
        val response = addNoteUseCase(title, note)
        response.onSuccess {
            _state.update { state -> state.copy(isLoading = false) }
            _event.emit(Event.SaveNoteSucess)
        }.onError { e, code, message, error ->
            _state.update { state -> state.copy(isLoading = false) }
            _event.emit(Event.ShowMessage(message ?: "Terjadi Kesalahan"))
        }
    }

    override fun onEvent(event: Event) {
        viewModelScope.launch {
            when(event) {
                is Event.SaveNoteSucess -> {
                    _event.emit(Event.SaveNoteSucess)
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
        object SaveNoteSucess: Event()
        class ShowMessage(val message: String): Event()
    }

}