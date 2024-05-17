package com.gostock.auth.login

import androidx.lifecycle.viewModelScope
import com.gostock.data.usecase.LoginUseCase
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
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginViewModel.State, LoginViewModel.Event>() {

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    override fun defaultState() = State(
        isLoading = false,
        data = emptyList()
    )

    fun login(
        username: String,
        password: String
    ) = viewModelScope.launch {
        _state.update { state -> state.copy(isLoading = true) }
        val response = loginUseCase(username, password)
        response.onSuccess {
            _state.update { state -> state.copy(isLoading = false) }
            if (it.success) {
                _event.emit(Event.LoginSuccess)
            } else {
                _event.emit(Event.ShowMessage(it.message))
            }
        }.onError { e, code, message, error ->
            _state.update { state -> state.copy(isLoading = false) }
            _event.emit(Event.ShowMessage(message ?: "Terjadi kesalahan"))
        }
    }

    override fun onEvent(event: Event) {
        viewModelScope.launch {
            when (event) {
                is Event.LoginSuccess -> {
                    _event.emit(Event.LoginSuccess)
                }

                is Event.ShowMessage -> {
                    _event.emit(Event.ShowMessage(event.message))
                }
            }
        }
    }

    data class State(
        val isLoading: Boolean,
        val data: List<Unit>
    )

    sealed class Event {
        object LoginSuccess : Event()
        class ShowMessage(val message: String) : Event()
    }
}