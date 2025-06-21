package com.example.studyhubapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BaseViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BaseUiState())
    val uiState: StateFlow<BaseUiState> = _uiState.asStateFlow()

    protected fun updateUiState(block: BaseUiState.() -> BaseUiState) {
        _uiState.update { it.block() }
    }
}

data class BaseUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val message: String? = null
)
