package com.gostock.featurenote.home

import androidx.lifecycle.viewModelScope
import com.gostock.data.model.NoteDataUiModel
import com.gostock.data.usecase.GetNotesUseCase
import com.gostock.data.usecase.LogoutUseCase
import com.gostock.data.util.onError
import com.gostock.data.util.onSuccess
import com.gostock.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val logoutUseCase: LogoutUseCase
): BaseViewModel<HomeViewModel.State, HomeViewModel.Event>() {

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    fun getNotes() = viewModelScope.launch {
        _state.update { state -> state.copy(isLoading = true) }
        val response = getNotesUseCase()
        response.onSuccess {
            _state.update { state ->
                state.copy(
                    isLoading = false,
                    notes = it.data
                )
            }
        }.onError { e, code, message, error ->
            _state.update { state -> state.copy(isLoading = false) }
        }
    }

    fun logout() = viewModelScope.launch(Dispatchers.IO) {
        logoutUseCase()
        _event.emit(Event.Logout)
    }

    override fun defaultState() = State(
        isLoading = false,
        notes = emptyList()
    )

    override fun onEvent(event: Event) {
        viewModelScope.launch {
            when(event) {
                is Event.ShowMessage -> {
                    _event.emit(Event.ShowMessage(event.message))
                }

                is Event.Logout -> {
                    _event.emit(Event.Logout)
                }
            }
        }
    }

    data class State(
        val isLoading: Boolean,
        val notes: List<NoteDataUiModel>
    )

    sealed class Event {
        object Logout: Event()
        class ShowMessage(val message: String): Event()
    }

}